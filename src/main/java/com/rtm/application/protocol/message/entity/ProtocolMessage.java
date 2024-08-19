package com.rtm.application.protocol.message.entity;

public class ProtocolMessage {

    private String srcIp;

    private int srcPort;

    private String destIp;

    private int dstPort;

    /**
     *  kafka 协议原始数据
     */
    private byte[] rawData;

    public ProtocolMessage(String srcIp, int srcPort, String destIp, int dstPort, byte[] rawData) {
        this.srcIp = srcIp;
        this.srcPort = srcPort;
        this.destIp = destIp;
        this.dstPort = dstPort;
        this.rawData = rawData;
    }

    public String getSrcIp() {
        return srcIp;
    }

    public void setSrcIp(String srcIp) {
        this.srcIp = srcIp;
    }

    public int getSrcPort() {
        return srcPort;
    }

    public void setSrcPort(int srcPort) {
        this.srcPort = srcPort;
    }

    public String getDestIp() {
        return destIp;
    }

    public void setDestIp(String destIp) {
        this.destIp = destIp;
    }

    public int getDstPort() {
        return dstPort;
    }

    public void setDstPort(int dstPort) {
        this.dstPort = dstPort;
    }

    public byte[] getRawData() {
        return rawData;
    }

    public void setRawData(byte[] rawData) {
        this.rawData = rawData;
    }
}
