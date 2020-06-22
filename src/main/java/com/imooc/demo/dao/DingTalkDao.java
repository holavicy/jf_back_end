package com.imooc.demo.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DingTalkDao {
    JSONObject getDingTalkCache(JSONObject jsonObject);

    int deleteDingTalkCache(JSONObject jsonObject);

    int insertDingTalkCache(JSONObject jsonObject);
}
