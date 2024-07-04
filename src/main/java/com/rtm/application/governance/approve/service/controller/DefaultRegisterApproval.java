package com.rtm.application.governance.approve.service.controller;

import com.rtm.application.governance.approve.Approval;
import com.rtm.application.governance.approve.RegisterAuthorize;
import com.rtm.application.governance.register.ApplicationInfo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DefaultRegisterApproval implements RegisterAuthorize<ApplicationInfo> {


    @Override
    public Boolean approve(Approval approval) {
        return null;
    }

    @Override
    public List<Approval> getUnApprovedInfo() {
        return null;
    }

    @Override
    public List<Approval> getApprovedInfo() {
        return null;
    }

    @Override
    public List<ApplicationInfo> getUnApprovedApplicationInfo() {
        return null;
    }

    @Override
    public Approval getApprovalInfoBy(String applicationId) {
        return null;
    }

    @Override
    public List<Approval> getApprovalInfoBy(List<String> applicationIds) {
        return null;
    }

    @Override
    public Approval apply(List<ApplicationInfo> applications) {
        return null;
    }
}
