package com.rtm.application.governance.approve;

import com.rtm.application.governance.register.ApplicationInfo;
import com.rtm.application.governance.register.ApplicationState;
import java.util.List;

/**
 *  该类为审批基础信息实体类
 */
public class Approval {

    /**
     *  审批人
     */
    private String approver;

    /**
     *  审批意见
     */
    private String comment;

    /**
     *  审批状态， {@linkplain ApplicationState}
     */
    private int state;

    /**
     *  审批时间
     */
    private long time;

    /**
     *  审批的应用信息
     */
    private List<ApplicationInfo> applications;

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public List<ApplicationInfo> getApplications() {
        return applications;
    }

    public void setApplications(List<ApplicationInfo> applications) {
        this.applications = applications;
    }
}
