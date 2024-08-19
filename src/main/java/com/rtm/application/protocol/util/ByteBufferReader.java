package com.rtm.application.protocol.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public final class ByteBufferReader {

    /**
     * 包装协议包,采用大端编码
     * @param payload 协议包内容
     * @return 返回包装后的协议包
     */
    public static ByteBuffer wrap(byte[] payload) {
        ByteBuffer buffer = ByteBuffer.wrap(payload);
        buffer.order(ByteOrder.BIG_ENDIAN);
        return buffer;
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

    /**
     *  合并两个字节流
     * @param buffer1 字节流
     * @param buffer2 字节流
     * @return 返回合并后的字节流
     */
    public static ByteBuffer combineBuffers(ByteBuffer buffer1, ByteBuffer buffer2) {
        if (buffer1 == null) {
            buffer1 = ByteBuffer.allocate(0);
        }
        if (buffer2 == null) {
            buffer2 = ByteBuffer.allocate(0);
        }
        ByteBuffer combined = ByteBuffer.allocate(buffer1.remaining() + buffer2.remaining());
        combined.put(buffer1);
        combined.put(buffer2);
        combined.flip();
        return combined;
    }


    /**
     *  获取剩余字节流
     * @param buffer 字节流
     * @return 返回剩余字节流
     */
    public static byte[] getRemainingBytes(ByteBuffer buffer) {
        if (buffer == null) {
            return null;
        }
        int remainingLength = buffer.remaining();
        byte[] remainingData = new byte[remainingLength];
        buffer.get(remainingData);
        return remainingData;
    }

    /**
     *  获取剩余字节流
     * @param buffer 包装的字节流数据
     * @return 返回剩余字节流包装对象
     */
    public static ByteBuffer getRemainingByteBuffer(ByteBuffer buffer) {
        return wrap(getRemainingBytes(buffer));
    }


    // Implementations of helper methods for reading various types
    private int readVarint(ByteBuffer buffer) {
        int value = 0;
        int i = 0;
        while (true) {
            byte b = buffer.get();
            value |= (b & 0x7F) << i;
            if ((b & 0x80) == 0) break;
            i += 7;
        }
        return value;
    }

    private long readVarlong(ByteBuffer buffer) {
        long value = 0;
        int i = 0;
        while (true) {
            byte b = buffer.get();
            value |= (long)(b & 0x7F) << i;
            if ((b & 0x80) == 0) break;
            i += 7;
        }
        return value;
    }

    private String readString(ByteBuffer buffer) {
        int length = buffer.getShort() & 0xFFFF;
        byte[] strBytes = new byte[length];
        buffer.get(strBytes);
        return new String(strBytes, StandardCharsets.UTF_8);
    }

    private String readCompactString(ByteBuffer buffer) {
        int length = buffer.get() & 0xFF;
        byte[] strBytes = new byte[length];
        buffer.get(strBytes);
        return new String(strBytes, StandardCharsets.UTF_8);
    }

    private String readNullableString(ByteBuffer buffer) {
        int length = buffer.getShort();
        if (length < 0) {
            return null;
        }
        byte[] strBytes = new byte[length];
        buffer.get(strBytes);
        return new String(strBytes, StandardCharsets.UTF_8);
    }

    private String readCompactNullableString(ByteBuffer buffer) {
        int length = buffer.get() & 0xFF;
        if (length == 0) {
            return null;
        }
        byte[] strBytes = new byte[length - 1];
        buffer.get(strBytes);
        return new String(strBytes, StandardCharsets.UTF_8);
    }

    private byte[] readBytes(ByteBuffer buffer) {
        int length = buffer.getInt();
        byte[] bytes = new byte[length];
        buffer.get(bytes);
        return bytes;
    }

    private byte[] readCompactBytes(ByteBuffer buffer) {
        int length = buffer.get() & 0xFF;
        byte[] bytes = new byte[length];
        buffer.get(bytes);
        return bytes;
    }

    private byte[] readNullableBytes(ByteBuffer buffer) {
        int length = buffer.getInt();
        if (length < 0) {
            return null;
        }
        byte[] bytes = new byte[length];
        buffer.get(bytes);
        return bytes;
    }

    private byte[] readCompactNullableBytes(ByteBuffer buffer) {
        int length = buffer.get() & 0xFF;
        if (length == 0) {
            return null;
        }
        byte[] bytes = new byte[length - 1];
        buffer.get(bytes);
        return bytes;
    }

    private Object readRecords(ByteBuffer buffer) {
        // Implement record parsing logic
        return null; // Placeholder
    }

//    private Object[] readArray(ByteBuffer buffer, FieldRule rule) {
//        int length = buffer.getInt();
//        Object[] array = new Object[length];
//        for (int i = 0; i < length; i++) {
//            array[i] = parseField(buffer, rule);
//        }
//        return array;
//    }
//
//    private Object[] readCompactArray(ByteBuffer buffer, FieldRule rule) {
//        int length = buffer.get() & 0xFF;
//        Object[] array = new Object[length];
//        for (int i = 0; i < length; i++) {
//            array[i] = parseField(buffer, rule);
//        }
//        return array;
//    }
}
