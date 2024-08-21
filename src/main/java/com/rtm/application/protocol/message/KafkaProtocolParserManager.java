package com.rtm.application.protocol.message;

import com.rtm.application.protocol.KafkaParserFactory;
import com.rtm.application.protocol.KafkaProtocolParser;
import com.rtm.application.protocol.ProtocolParser;
import com.rtm.application.protocol.api.parser.DefaultRequestHeaderParser;
import com.rtm.application.protocol.api.parser.DefaultResponseHeaderParser;
import com.rtm.application.protocol.message.entity.*;
import com.rtm.application.protocol.message.entity.api.Field;
import com.rtm.application.protocol.message.exception.NotFoundProtocolParseException;
import com.rtm.application.protocol.message.exception.ProtocolParseException;
import com.rtm.application.protocol.message.exception.RequestProtocolParseException;
import com.rtm.application.protocol.util.ByteUtils;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;


/**
 * @description:  对外提供解析入口
 * @author rtm
 * @date 2024/8/21
 */
public class KafkaProtocolParserManager implements ProtocolParser<Message> {

    private static final int LISTEN_PORT = 9094;


    private KafkaProtocolParser<RequestHeader> requestHeaderParser = new DefaultRequestHeaderParser();

    private KafkaProtocolParser<ResponseHeader> responseHeaderParser = new DefaultResponseHeaderParser();

    /**
     *  存储请求数据包信息，用于解析对应的响应包内容
     */
    private final Map<Integer,RequestMessage> messages = new ConcurrentHashMap<>();

    /**
     *  存储请求分片数据包，读取到完整包后移除
     */
    private final Map<String,ByteBuffer> segmentPacket = new ConcurrentHashMap<>();

    /**
     *  存储数据包信息，用于解析对应的请求包内容
     */
    private AtomicReference<ProtocolMessage> packetMessage = new AtomicReference<>();


    @Override
    public Message parse(ProtocolMessage data) throws ProtocolParseException {

        init(data);

        if (!supportParse(data)) {
            throw new ProtocolParseException("非 Kafka 协议包，跳过解析！");
        }
        String segmentKey = getSegmentKey(data);
        ByteBuffer preSegmentPacket = segmentPacket.get(segmentKey);
        ByteBuffer combinedPacket = ByteUtils.combineBuffers(preSegmentPacket, ByteUtils.wrap(data.getRawData()));

        Message message = null;
        if (transmitFinished(combinedPacket)) {

            clearSegmentPacket();

            message = startParse(combinedPacket);
        } else {
            segmentPacket.put(segmentKey, combinedPacket);
        }
        return message;
    }


    /**
     *  解析初始化
     * @param data
     */
    public void init(ProtocolMessage data) {
        packetMessage.set(data);
    }


    private String getSegmentKey(ProtocolMessage data) {
        return data.getSrcIp() + ":" + data.getSrcPort() + "=>" + data.getDestIp() + ":" + data.getDstPort();
    }

    /**
     * 清除分片数据包
     */
    private void clearSegmentPacket() {
        String segmentKey = getSegmentKey(packetMessage.get());
        segmentPacket.remove(segmentKey);
    }


    /**
     * 是否开始解析数据包
     * @param byteBuffer 抓取的数据包内容
     * @return 返回 true 则开始解析，返回 false 则不进行解析
     */
    public Message startParse(ByteBuffer byteBuffer) throws ProtocolParseException {

        RequestMessage requestMessage = null;
        ResponseMessage responseMessage = null;

        int messageSize = byteBuffer.remaining();

        if (isRequestPacket(packetMessage.get())) {
            requestMessage = parseRequestPacket(byteBuffer);
        } else if (isResponsePacket(packetMessage.get())) {
            responseMessage = parseResponsePacket(byteBuffer);
        } else {
            throw new ProtocolParseException("无法识别的数据包类型!");
        }

        Message message = new Message();
        if (( requestMessage != null && requestMessage.isParseComplete()) ||
                (responseMessage != null && responseMessage.isParseComplete()) ) {
            message.setMessageSize(messageSize);
            message.setRequestMessage(requestMessage);
            message.setResponseMessage(responseMessage);
            message.setParseComplete(true);
        }

        return message;
    }


    public RequestMessage parseRequestPacket(ByteBuffer payload) throws ProtocolParseException {

        KafkaProtocolParser<RequestHeader> requestHeaderParser = getRequestHeaderParser();
        if (requestHeaderParser == null) {
            throw new NotFoundProtocolParseException("未找到请求头协议解析器!");
        }
        RequestHeader requestHeader = requestHeaderParser.parsePacket(payload);
        if (requestHeader == null) {
            throw new RequestProtocolParseException("请求头协议解析内容为空!");
        }
        System.out.println("请求头解析完成，coleretionId："+requestHeader.getCorrelationId());

        ByteBuffer remainingPayload = ByteUtils.getRemainingByteBuffer(payload);

        RequestBody requestBody = null;
        if (containsPayload(requestHeader.getHeaderLength(),requestHeader.getLength())) {
            requestBody = parseRequestPayload(requestHeader.getApiVersionKey(), remainingPayload);
        }

        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setHeader(requestHeader);
        requestMessage.setPayload(requestBody);
        requestMessage.setParseComplete(true);

        messages.put(requestHeader.getCorrelationId(), requestMessage);

        return requestMessage;
    }


    /**
     *  判断数据包是否为完整的数据包，读取数据包前4个字节获取总数据包长度，并与当前数据包的长度进行比较，
     *  若相等则返回 true，反之则返回 false；若为分片数据包，则总长度为0，则返回 false，反之则返回 true
     * @param payload
     * @return
     */
    private boolean transmitFinished(ByteBuffer payload) {
        // 标记当前位置
        int currentPos = payload.position();

        int totalPacketLength = payload.getInt();

        // 恢复到之前的位置
        payload.position(currentPos);

        int remainingLength = payload.remaining();

        System.out.println("总包长度："+totalPacketLength + "<===当前包长度：===> "+remainingLength);
        return totalPacketLength !=0 && remainingLength >= totalPacketLength;
    }


    /**
     *  判断是否包含 payload 数据包内容,若包的总长度等于请求头的长度则不未携带请求体信息
     * @param headerLength 解析后的请求头长度
     * @param totalLength 读取请求头后的协议包总长度
     * @return 返回 true 则包含，返回 false 则不包含
     */
    public boolean containsPayload(int headerLength, int totalLength) {
        return headerLength < totalLength;
    }


    public RequestBody parseRequestPayload(Field apiVersionKey, ByteBuffer payload) throws ProtocolParseException {
        KafkaProtocolParser<RequestBody> requestBodyParser = KafkaParserFactory.getRequestParser(apiVersionKey);
        if (requestBodyParser == null) {
            System.out.println("未找到跳过！");
            return null;
        }
        return requestBodyParser.parsePacket(payload);
    }


    public ResponseMessage parseResponsePacket(ByteBuffer byteBuffer) throws ProtocolParseException {

        KafkaProtocolParser<ResponseHeader> responseHeaderParser = getResponseHeaderParser();
        if (responseHeaderParser == null) {
            throw new NotFoundProtocolParseException("未找到响应数据包协议解析器!");
        }
        ResponseHeader responseHeader = responseHeaderParser.parsePacket(byteBuffer);
        if (responseHeader == null) {
            throw new RequestProtocolParseException("响应数据包请求头协议解析内容为空!");
        }

        ByteBuffer remainingPayload = ByteUtils.getRemainingByteBuffer(byteBuffer);

        ResponseBody responseBody = null;
        if (containsPayload(responseHeader.getHeaderLength(), responseHeader.getLength())) {
            responseBody = parseResponsePayload(responseHeader.getCorrelationId(), remainingPayload);
        } else {
            System.out.println("响应 id : "+responseHeader.getCorrelationId());
        }

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setHeader(responseHeader);
        responseMessage.setPayload(responseBody);
        responseMessage.setParseComplete(true);

        return responseMessage;
    }


    public ResponseBody parseResponsePayload(int correlationId, ByteBuffer payload) throws ProtocolParseException {
        RequestMessage requestMessage = messages.get(correlationId);
        if (requestMessage == null) {
            String msg = "未找到响应数据包对应的请求数据包消息，" +
                    "CorrelationId: " + correlationId;
            System.out.println(msg);
            return null;
        }

        RequestHeader responseHeader = requestMessage.getHeader();
        if (responseHeader == null) {
//            throw new ResponseProtocolParseException("未找到响应数据包对应的请求数据包消息，" +
//                    "CorrelationId: " + correlationId +
//                    "apiKey: " + ApiKeys.getApiDescription(responseHeader.getApiKey()) +
//                    "apiVersion: " + responseHeader.getApiVersion()
//                    );
            return null;
        }
        messages.remove(correlationId);
        KafkaProtocolParser<ResponseBody> responseParser = KafkaParserFactory.getResponseParser(responseHeader.getApiVersionKey());
        if (responseParser == null) {
            System.out.println("未找到跳过！");
            return null;
        }
        return responseParser.parsePacket(payload);
    }


    public boolean supportParse(ProtocolMessage data) {
        return ( isNotEmptyPacket(data.getRawData()) || isRequestPacket(data)
                || isResponsePacket(data) );
    }


    public boolean isNotEmptyPacket(byte[] payload) {
        return payload.length > 0;
    }


    private static boolean isRequestPacket(ProtocolMessage data) {
        return LISTEN_PORT == data.getDstPort();
    }

    private static boolean isResponsePacket(ProtocolMessage data) {
        return LISTEN_PORT == data.getSrcPort();
    }

    public KafkaProtocolParser<RequestHeader> getRequestHeaderParser() {
        return requestHeaderParser;
    }

    public void setRequestHeaderParser(KafkaProtocolParser<RequestHeader> requestHeaderParser) {
        this.requestHeaderParser = requestHeaderParser;
    }

    public KafkaProtocolParser<ResponseHeader> getResponseHeaderParser() {
        return responseHeaderParser;
    }

    public void setResponseHeaderParser(KafkaProtocolParser<ResponseHeader> responseHeaderParser) {
        this.responseHeaderParser = responseHeaderParser;
    }
}
