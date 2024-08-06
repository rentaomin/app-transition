package com.rtm.application.mybatisFlex.demo.mapper;

import com.rtm.application.mybatisFlex.demo.entity.ServiceStatusInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ServiceStatusInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ServiceStatusInfo record);

    int insertSelective(ServiceStatusInfo record);

    ServiceStatusInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ServiceStatusInfo record);

    int updateByPrimaryKey(ServiceStatusInfo record);

    ServiceStatusInfo selectByServiceName(String serviceName);

    int queryTotal(Map<String, Object> param);

    List<ServiceStatusInfo> queryServiceInfoList(Map<String, Object> param);

    ServiceStatusInfo queryByServiceNameAndEndpoint(@Param("serviceName") String serviceName,@Param("endpointId")Integer endpointId);

    int deleteExpireInfoByDate(String expireTime);

    List<ServiceStatusInfo> queryNeedToBeAlarmInfoInTimeScope(@Param("alarmCondition") String alarmCondition,@Param("serviceName") String serviceName);

    int deletaAll();

    List<ServiceStatusInfo> list(Map<String,Object> param);
}