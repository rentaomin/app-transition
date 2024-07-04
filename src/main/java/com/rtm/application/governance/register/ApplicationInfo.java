package com.rtm.application.governance.register;

import java.util.Properties;

/**
 *  应用实体类信息
 */
public class ApplicationInfo implements Application {

    private String id;

    private String name;

    private String region;

    private String ip;

    private int port;

    private boolean enable;

    private ApplicationState state = ApplicationState.UN_REGISTER;

    private Properties properties;

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getRegion() {
        return this.region;
    }

    @Override
    public String getIp() {
        return this.ip;
    }

    @Override
    public int getPort() {
        return this.port;
    }

    @Override
    public Boolean enable() {
        return this.enable;
    }

    @Override
    public ApplicationState getState() {
        return this.state;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setState(ApplicationState state) {
        this.state = state;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
