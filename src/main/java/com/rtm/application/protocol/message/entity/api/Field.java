package com.rtm.application.protocol.message.entity.api;

public class Field {

    private String key;

    private String value;

    public Field(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Field target = (Field) obj;
        if (target == null) {
            return false;
        }
        if (this == null) {
            return false;
        }
        // 暂时忽略 value
        if (this.key == null || target.key == null) {
            return false;
        }
//        if (this.key == null || target.key == null || this.value == null || target.value == null) {
//            return false;
//        }
//        return  (this.key.equals(target.key) && this.value.equals(target.value));
        return  (this.key.equals(target.key));
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("key: ");
        stringBuilder.append(this.key);
        stringBuilder.append("<==>");
        stringBuilder.append("value: ");
        stringBuilder.append(this.value);
        return stringBuilder.toString();
    }
}
