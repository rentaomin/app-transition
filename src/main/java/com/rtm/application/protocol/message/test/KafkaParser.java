package com.rtm.application.protocol.message.test;

import com.rtm.application.protocol.message.KafkaProtocolParserManager;
import com.rtm.application.protocol.message.entity.ProtocolMessage;
import com.rtm.application.protocol.message.exception.ProtocolParseException;
import org.pcap4j.core.*;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;


/**
 *
 *   RequestOrResponse => Size (RequestMessage | ResponseMessage)
 *   Size => int32
 *  1、区分是什么协议，通过 ip:port 端口进行区分
 *  2、区分是请求还是响应；观察流量请求包和响应包，发现区分请求中是否包含 clientId：NULLABLE_STRING (INT16) < 0 ? -1 : N
 *  3、解析公共数据包，跳过空包
 */
public class KafkaParser {

   static short LISTEN_PORT = 9094;

    private KafkaProtocolParserManager kafkaProtocolParserManager = new KafkaProtocolParserManager();

    public static void main(String[] args) throws PcapNativeException, NotOpenException, ProtocolParseException {
        // 获取所有网络接口
        List<PcapNetworkInterface> allDevs = Pcaps.findAllDevs();
        if (allDevs.isEmpty()) {
            System.out.println("没有找到网络接口.");
            return;
        }

        // 选择第一个网络接口
        PcapNetworkInterface nif = allDevs.get(3);

        KafkaParser kafkaParser = new KafkaParser();

        // 打开网络接口
        int snapLen = 65536;           // 捕获的最大数据包大小
        int timeout = 10 * 1000;       // 超时时间（毫秒）
        PcapHandle handle = nif.openLive(snapLen, PcapNetworkInterface.PromiscuousMode.PROMISCUOUS, timeout);
        // 设置过滤器，只捕获发往MySQL默认端口（3306）的TCP流量
        handle.setFilter("tcp port "+String.valueOf(LISTEN_PORT), BpfProgram.BpfCompileMode.OPTIMIZE);
        // 捕获数据包
            while (true) {
                Packet packet = null;
                try {
                    packet = handle.getNextPacketEx();
                } catch (Exception e) {
                    System.out.println("异常");
                }
                if (packet != null) {
                    kafkaParser.parsePacket(packet);
                }
            }
//        } catch (EOFException | TimeoutException e) {
//            System.out.println("捕获结束.");
//        } finally {
//            handle.close();
//        }
    }

    // 解析 MySQL 数据包
    private  void parsePacket(Packet packet) throws ProtocolParseException {
        // 检查是否是TCP数据包
        if (packet.contains(TcpPacket.class)) {
            TcpPacket tcpPacket = packet.get(TcpPacket.class);

            TcpPacket.TcpHeader header = tcpPacket.getHeader();
            int srcPort = header.getSrcPort().valueAsInt();
            int destPort = header.getDstPort().valueAsInt();


            IpV4Packet ipV4Packet = (IpV4Packet)packet.getPayload();
            String srcIp = ipV4Packet.getHeader().getSrcAddr().getHostAddress();
            String destIp = ipV4Packet.getHeader().getDstAddr().getHostAddress();

            if (!isTargetPacket(tcpPacket)) {
                return;
            }
            Packet payloadPacket = tcpPacket.getPayload();
            if (payloadPacket == null) {
                return;
            }
            // 获取TCP载荷（即 Kafka 协议数据）
            byte[] payload = payloadPacket.getRawData();
            if (payload.length < 4) {
                System.out.println("数据包长度不足4字节");
                return;
            }

            ProtocolMessage message = new ProtocolMessage(srcIp,srcPort,destIp,destPort,payload);
            kafkaProtocolParserManager.parse(message);
        }
    }



    public static boolean isTargetPacket(TcpPacket tcpPacket) {
        return isRequestPacket(tcpPacket) || isResponsePacket(tcpPacket);
    }

    private static boolean isRequestPacket(TcpPacket tcpPacket) {
        return tcpPacket.getHeader()
                .getDstPort()
                .value()
                .equals(LISTEN_PORT);
    }
    private static boolean isResponsePacket(TcpPacket tcpPacket) {
        return tcpPacket.getHeader()
                .getSrcPort()
                .value()
                .equals(LISTEN_PORT);
    }


    /**
     *  读取指定长度的字符串
     * @param buffer 字节流
     * @param length 读取的字节长度
     * @return 返回该长度读取的字符串内容
     */
    public static String getString(ByteBuffer buffer, short length) {
        if (length < 0 ) {
            return "";
        }
        byte[] stringBytes = new byte[length];
        buffer.get(stringBytes);
        return new String(stringBytes, StandardCharsets.UTF_8);
    }
}
