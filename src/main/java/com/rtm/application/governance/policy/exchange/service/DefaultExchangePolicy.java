package com.rtm.application.governance.policy.exchange.service;

import com.rtm.application.governance.policy.exchange.ExchangePolicy;
import com.rtm.application.governance.register.Application;
import com.rtm.application.governance.register.ApplicationInfo;

public class DefaultExchangePolicy implements ExchangePolicy {

    private String id;
    private String name;

    private ApplicationInfo sourceApplication;

    private ApplicationInfo targetApplication;

    private boolean enable;

    private long createTime;

    private int dataLevel;

    private int direction;

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Application getSourceApplication() {
        return this.sourceApplication;
    }

    @Override
    public Application getTargetApplication() {
        return null;
    }

    @Override
    public Boolean enable() {
        return null;
    }

    @Override
    public long getCreateTime() {
        return 0;
    }

    @Override
    public int getDataLevel() {
        return 0;
    }

    @Override
    public int getDirection() {
        return 0;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSourceApplication(ApplicationInfo sourceApplication) {
        this.sourceApplication = sourceApplication;
    }

    public void setTargetApplication(ApplicationInfo targetApplication) {
        this.targetApplication = targetApplication;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public void setDataLevel(int dataLevel) {
        this.dataLevel = dataLevel;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
