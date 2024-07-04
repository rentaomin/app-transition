package com.rtm.application.governance.register;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 *  负责提供应用的注册、注销、查询接口，子类若需要在应用注册、注销、查询接口中扩展
 *  其它业务逻辑处理，可扩展该接口进行业务扩展
 */
public interface ApplicationRegister<T extends Application> {

    /**
     *  注册应用
     * @param application
     */
     default Boolean register(T application){
         return register(Collections.singletonList(application));
     }

    /**
     *  批量注册应用信息
     * @param applications
     */
    Boolean register(List<T> applications);

    /**
     *  注销指定的应用
     * @param application
     */
    default Boolean unRegister(T application) {
        return unRegister(Collections.singletonList(application));
    }

    /**
     *  批量注销指定的应用
     * @param applications
     */
    Boolean unRegister(List<T> applications);

    /**
     *  获取注册的应用信息
     * @return
     */
    List<T> getApplication();

    /**
     *  获取注册的应用信息
     * @return
     */
    T getApplicationById(String applicationId);

    /**
     * 获取指定条件的应用信息
     * @param filter 过滤条件
     * @return 返回满足条件的应用信息
     */
    List<T> getApplication(Predicate<T> filter);
}
