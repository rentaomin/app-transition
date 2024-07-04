package com.rtm.application.governance.approve.service.controller;

import com.rtm.application.governance.approve.Approval;
import com.rtm.application.governance.approve.RegisterAuthorize;
import com.rtm.application.governance.register.Application;
import com.rtm.application.governance.register.ApplicationInfo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DefaultRegisterApproval implements RegisterAuthorize {


    @Override
    public Approval authentication(List<ApplicationInfo> applications) {
        return null;
    }

    @Override
    public boolean approve(Approval approval) {
        return false;
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
}
