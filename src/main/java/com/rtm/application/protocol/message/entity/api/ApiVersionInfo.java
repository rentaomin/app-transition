package com.rtm.application.protocol.message.entity.api;

public class ApiVersionInfo {

    private static final String slash = "-";

    private short key;

    private short minVersion;

    private short maxVersion;

    private short version;

    public ApiVersionInfo(short key, short minVersion, short maxVersion) {
        this.key = key;
        this.minVersion = minVersion;
        this.maxVersion = maxVersion;
    }

    public ApiVersionInfo(short key, short version) {
        this.key = key;
        this.version = version;
    }

    public short getKey() {
        return key;
    }

    public short getMinVersion() {
        return minVersion;
    }

    public short getMaxVersion() {
        return maxVersion;
    }

    public short getVersion() {
        return version;
    }

    public String getSupportedVersionDescription() {
        return " 支持版本号范围：[ V"+ getMinVersion() + slash +getMaxVersion() + "]";
    }
}
