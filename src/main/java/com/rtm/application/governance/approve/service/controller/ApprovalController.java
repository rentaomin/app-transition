package com.rtm.application.governance.approve.service.controller;

import com.rtm.application.governance.approve.Approval;
import com.rtm.application.governance.approve.RegisterAuthorize;
import com.rtm.application.governance.register.ApplicationInfo;
import com.rtm.application.util.Response;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;


@RequestMapping("/approval")
@RestController
public class ApprovalController {

    @Resource
    private RegisterAuthorize registerAuthorize;


    /**
     *  应用审批信息审批
     * @param approval
     * @return
     */
    @PostMapping("/info")
    public Response<Boolean> approve(@RequestBody Approval approval) {
        boolean approve = registerAuthorize.approve(approval);
        return Response.builder().success(approve);
    }

    /**
     *  应用审批信息待审批信息查询
     * @return
     */
    @GetMapping("/info")
    public Response<List<ApplicationInfo>> approve() {
        List<ApplicationInfo> unApprovedApplicationInfo = registerAuthorize.getUnApprovedApplicationInfo();
        return Response.builder().success(unApprovedApplicationInfo);
    }
}
