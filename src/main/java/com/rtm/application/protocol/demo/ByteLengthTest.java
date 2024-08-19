package com.rtm.application.protocol.demo;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ByteLengthTest {


    public static void main(String[] args) {
        byte[] payload = new byte[]{0x00, 0x00 ,0x00, 0x19};
        ByteBuffer buffer = ByteBuffer.wrap(payload);
        buffer.order(ByteOrder.BIG_ENDIAN);
//        buffer.order(ByteOrder.LITTLE_ENDIAN);
        int anInt = buffer.getInt();
        System.out.println(anInt);
    }
}
