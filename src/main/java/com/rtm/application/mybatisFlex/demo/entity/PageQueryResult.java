package com.rtm.application.mybatisFlex.demo.entity;

import java.util.List;

/**
 * 翻页查询结果数据
 * @Auther: xuchao
 * @Date: 2019/8/15 14:28
 */
public class PageQueryResult {
    private long totalCount; //总数
    private List<?> list;   //数据列表

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
