package com.rtm.application.app;

import com.rtm.application.governance.approve.Approval;
import com.rtm.application.governance.register.ApplicationInfo;
import com.rtm.application.governance.register.Authorize;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;

@Slf4j
@Component
public class DefaultApplicationAuthorize implements Authorize<ApplicationInfo> {


    @Override
    public Approval authentication(List<ApplicationInfo> applications) {
        log.error("app:{} 审批完成！", applications.size());
        return null;
    }
}
