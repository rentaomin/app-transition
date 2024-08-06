package com.rtm.application.mybatisFlex.demo.mapper;


import com.rtm.application.mybatisFlex.demo.entity.EndpointInfo;
import com.rtm.application.mybatisFlex.demo.entity.SystemParamConfigEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


/**
 * 系统参数配置持久层
 *
 * @author zhangyi
 * @version 2018/6/11
 * @see SystemParamConfigMapper
 */
@Mapper
public interface SystemParamConfigMapper {


    List<SystemParamConfigEntity> getAllInfo();

    List<SystemParamConfigEntity> getAllInfo(Map<String, Object> queryMap);

    EndpointInfo selectByPrimaryKey(String record);


}
