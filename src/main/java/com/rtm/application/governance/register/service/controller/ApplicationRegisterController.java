package com.rtm.application.governance.register.service.controller;

import com.rtm.application.governance.register.ApplicationInfo;
import com.rtm.application.util.Response;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/applicationRegister")
@RestController
public class ApplicationRegisterController {

    @Resource
    private DefaultApplicationRegister register;

    @PostMapping("/info")
    public Response<Boolean> register(@RequestBody ApplicationInfo applicationInfo) {
        return Response.builder().success(register.register(applicationInfo));
    }

    @PostMapping("/infos")
    public Response<Boolean> register(@RequestBody List<ApplicationInfo> applicationInfos) {
        return Response.builder().success(register.register(applicationInfos));
    }

    @PutMapping("/info")
    public Response<Boolean> unRegister(@RequestBody ApplicationInfo applicationInfo) {
        return Response.builder().success(register.unRegister(applicationInfo));
    }

    @PutMapping("/info")
    public Response<Boolean> unRegister(@RequestBody List<ApplicationInfo> applicationInfo) {
        return Response.builder().success(register.unRegister(applicationInfo));
    }

    @GetMapping("/info")
    public Response<ApplicationInfo> getApplication(@PathVariable String policyId) {
        return Response.builder().success(register.getApplicationById(policyId));
    }

    @GetMapping("/info")
    public Response<List<ApplicationInfo>> getApplication() {
        return Response.builder().success(register.getApplication());
    }
}
