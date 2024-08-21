package com.rtm.application.protocol;

import com.rtm.application.protocol.message.entity.RequestBody;
import com.rtm.application.protocol.message.entity.ResponseBody;
import com.rtm.application.protocol.message.entity.api.Field;
import com.rtm.application.protocol.message.enums.ApiKeys;
import com.rtm.application.protocol.message.exception.RegisterProtocolParseException;
import com.rtm.application.protocol.message.exception.UnSupportApiKeyParseException;
import com.rtm.application.protocol.util.ClassLoaderUtil;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  Kafka 协议解析器工厂类
 */
public class KafkaParserFactory {

    // 存储不同版本的请求解析器
    private static final Map<Short, KafkaProtocolParser<RequestBody>> requestParsers = new ConcurrentHashMap<>();

    // 存储不同版本的响应解析器
    private static final Map<Short, KafkaProtocolParser<ResponseBody>> responseParsers = new ConcurrentHashMap<>();


    static {
        try {
            registerCandidateParsers();
        } catch (RegisterProtocolParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void registerCandidateParsers() throws RegisterProtocolParseException {
        try {
            // 扫描并注册所有实现 KafkaProtocolParser 的类
            String packageName = "com.rtm.application.protocol.api.parser";
            List<Class<?>> classes = ClassLoaderUtil.getClasses(packageName);
            for (Class<?> clazz : classes) {
                if (KafkaProtocolParser.class.isAssignableFrom(clazz) && !clazz.isInterface()) {
                    KafkaProtocolParser<?> parser = (KafkaProtocolParser<?>) clazz.getDeclaredConstructor().newInstance();
                    registerParser(parser);
                }
            }
        } catch (Exception e) {
            throw new RegisterProtocolParseException("Failed to register parsers", e);
        }
    }


    // 注册解析器的方法
    public static void registerParser(KafkaProtocolParser parser) throws RegisterProtocolParseException {
        if (parser == null) {
            throw new RegisterProtocolParseException("Parser cannot be null");
        }
        Field key = parser.getApiVersionKey();
        if (parser.isRequestParser()) {
            requestParsers.put(Short.valueOf(key.getKey()), parser);
        } else {
            responseParsers.put(Short.valueOf(key.getKey()), parser);
        }
    }


    public static KafkaProtocolParser<RequestBody> getRequestParser(Field apiVersionKey) throws UnSupportApiKeyParseException {
        KafkaProtocolParser<RequestBody> requestBodyKafkaProtocolParser = requestParsers.get(Short.valueOf(apiVersionKey.getKey()));
        if (requestBodyKafkaProtocolParser == null) {
            String msg = "未找到 apiKey "+ ApiKeys.getName(Short.valueOf(apiVersionKey.getKey())) +"["+apiVersionKey.getKey()+"],版本：["
                    +apiVersionKey.getValue() +"]请求协议数据包解析器!";
            System.out.println(msg);
//            throw new UnSupportApiKeyParseException("未找到 apiKey "+ ApiKeys.getName(Short.valueOf(apiVersionKey.getKey())) +"["+apiVersionKey.getKey()+"],版本：["
//                    +apiVersionKey.getValue() +"]请求协议数据包解析器!");
        }
        return requestBodyKafkaProtocolParser;
    }


    public static KafkaProtocolParser<ResponseBody> getResponseParser(Field apiVersionKey) throws UnSupportApiKeyParseException {
        KafkaProtocolParser<ResponseBody> responseBodyKafkaProtocolParser = responseParsers.get((Short.valueOf(apiVersionKey.getKey())));
        if (responseBodyKafkaProtocolParser == null) {
            String msg = "未找到 apiKey "+ ApiKeys.getName(Short.valueOf(apiVersionKey.getKey()) ) +"["+apiVersionKey.getKey()+"],版本：["
                    +apiVersionKey.getValue() +"]响应协议数据包解析器!";
            System.out.println(msg);
//            throw new UnSupportApiKeyParseException("未找到 apiKey "+ ApiKeys.getName(Short.valueOf(apiVersionKey.getKey()) ) +"["+apiVersionKey.getKey()+"],版本：["
//                    +apiVersionKey.getValue() +"]响应协议数据包解析器!");
        }
        return responseBodyKafkaProtocolParser;
    }

}
