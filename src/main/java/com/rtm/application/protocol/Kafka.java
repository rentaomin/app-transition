package com.rtm.application.protocol;

import org.pcap4j.core.*;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;

import java.io.EOFException;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class Kafka {

   static int LISTEN_PORT = 3306;

    public static void main(String[] args) throws PcapNativeException, NotOpenException {
        // 获取所有网络接口
        List<PcapNetworkInterface> allDevs = Pcaps.findAllDevs();
        if (allDevs.isEmpty()) {
            System.out.println("没有找到网络接口.");
            return;
        }

        // 选择第一个网络接口
        PcapNetworkInterface nif = allDevs.get(3);


        // 打开网络接口
        int snapLen = 65536;           // 捕获的最大数据包大小
        int timeout = 10 * 1000;       // 超时时间（毫秒）
        PcapHandle handle = nif.openLive(snapLen, PcapNetworkInterface.PromiscuousMode.PROMISCUOUS, timeout);
        // 设置过滤器，只捕获发往MySQL默认端口（3306）的TCP流量
        handle.setFilter("tcp port "+String.valueOf(LISTEN_PORT), BpfProgram.BpfCompileMode.OPTIMIZE);
        // 捕获数据包
        try {
            while (true) {
                Packet packet = handle.getNextPacketEx();
                if (packet != null) {
                    parseMysqlPacket(packet);
                }
            }
        } catch (EOFException | TimeoutException e) {
            System.out.println("捕获结束.");
        } finally {
            handle.close();
        }
    }

    // 解析 MySQL 数据包
    private static void parseMysqlPacket(Packet packet) {
        // 检查是否是TCP数据包
        if (packet.contains(TcpPacket.class)) {
            TcpPacket tcpPacket = packet.get(TcpPacket.class);

            // 检查端口号是否是MySQL的默认端口
            if (tcpPacket.getHeader().getDstPort().equals(LISTEN_PORT)
                    || tcpPacket.getHeader().getSrcPort().equals(LISTEN_PORT)) {

                // 获取TCP载荷（即MySQL协议数据）
                byte[] payload = tcpPacket.getPayload().getRawData();

                // 解析MySQL数据包头部信息（前4字节）
                if (payload.length >= 4) {
                    int packetLength = ((payload[0] & 0xFF) | ((payload[1] & 0xFF) << 8) | ((payload[2] & 0xFF) << 16));
                    byte sequenceId = payload[3];

                    System.out.println("捕获到MySQL数据包: ");
                    System.out.println("数据包长度: " + packetLength);
                    System.out.println("序列号: " + sequenceId);

                    // 解析更多的MySQL协议数据（例如查询内容、响应内容等）
                    // 此处可以根据MySQL协议进行进一步解析
                }
            }
        }
    }
}
