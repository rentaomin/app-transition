package com.rtm.application.governance.policy.exchange;

import com.rtm.application.governance.register.Application;
import com.rtm.application.governance.register.ApplicationInfo;

/**
 *  应用交换策略基础信息类
 */
public class ApplicationExchangePolicyInfo implements ExchangePolicy {

    private String id;

    private String name;

    private int direction;

    private int dataLevel;

    private boolean enable;

    private long createTime;

    private ApplicationInfo sourceApplicationInfo;

    private ApplicationInfo targetApplicationInfo;

    @Override
    public int getDataLevel() {
        return this.dataLevel;
    }


    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Application getSourceApplication() {
        return this.sourceApplicationInfo;
    }

    @Override
    public Application getTargetApplication() {
        return this.targetApplicationInfo;
    }

    @Override
    public Boolean enable() {
        return this.enable;
    }

    @Override
    public long getCreateTime() {
        return this.createTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setDataLevel(int dataLevel) {
        this.dataLevel = dataLevel;
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

    public ApplicationInfo getSourceApplicationInfo() {
        return sourceApplicationInfo;
    }

    public void setSourceApplicationInfo(ApplicationInfo sourceApplicationInfo) {
        this.sourceApplicationInfo = sourceApplicationInfo;
    }

    public ApplicationInfo getTargetApplicationInfo() {
        return targetApplicationInfo;
    }

    public void setTargetApplicationInfo(ApplicationInfo targetApplicationInfo) {
        this.targetApplicationInfo = targetApplicationInfo;
    }
}
