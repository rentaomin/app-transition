package com.rtm.application.governance.approve.callback;

import com.rtm.application.governance.AbstractApplicationEvent;
import com.rtm.application.governance.EventCallBack;
import com.rtm.application.util.Response;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
public class DefaultAppCallBack implements EventCallBack<AbstractApplicationEvent,Response> {

    @Override
    public void accept(AbstractApplicationEvent event, Response response) {
        List dataInfo = event.getDataInfo();
        log.error("应用事件数量为：{}", dataInfo.size());
    }
}
