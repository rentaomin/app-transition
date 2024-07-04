package com.rtm.application.governance.policy.exchange.service.controller;

import com.rtm.application.governance.policy.exchange.ExchangePolicyManager;
import com.rtm.application.governance.policy.exchange.service.DefaultExchangePolicy;
import com.rtm.application.util.Response;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;


@RequestMapping("/exchangePolicy")
@RestController
public class ExchangePolicyController {

    @Resource
    private ExchangePolicyManager policyManager;

    @PostMapping("/info")
    public Response<Boolean> add(@RequestBody DefaultExchangePolicy exchangePolicy) {
        return Response.builder().success(policyManager.add(exchangePolicy));
    }

    @PostMapping("/infos")
    public Response<Boolean> add(@RequestBody List<DefaultExchangePolicy> exchangePolicies) {
        return Response.builder().success(policyManager.add(exchangePolicies));
    }

    @PutMapping("/info")
    public Response<Boolean> update(@RequestBody DefaultExchangePolicy exchangePolicy) {
        return Response.builder().success(policyManager.update(exchangePolicy));
    }

    @PutMapping("/info")
    public Response<Boolean> update(@RequestBody List<DefaultExchangePolicy> exchangePolicies) {
        return Response.builder().success(policyManager.update(exchangePolicies));
    }

    @DeleteMapping("/info/{policyId}")
    public Response<Boolean> delete(@PathVariable String policyId) {
        return Response.builder().success(policyManager.delete(policyId));
    }

    @DeleteMapping("/info")
    public Response<Boolean> delete(@PathVariable List<String> policyIds) {
        return Response.builder().success(policyManager.delete(policyIds));
    }

    @GetMapping("/info")
    public Response<DefaultExchangePolicy> getPolicyById(@PathVariable String policyId) {
        return Response.builder().success(policyManager.getPolicyById(policyId));
    }

    @GetMapping("/info")
    public Response<List<DefaultExchangePolicy>> getPolicies() {
        return Response.builder().success(policyManager.getPolicies());
    }
}
