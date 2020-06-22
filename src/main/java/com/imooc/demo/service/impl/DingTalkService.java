package com.imooc.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkConstants;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiUserGetRequest;
import com.dingtalk.api.request.OapiUserGetuserinfoRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.dingtalk.api.response.OapiUserGetuserinfoResponse;
import com.imooc.demo.dao.DingTalkDao;
import com.imooc.demo.util.constants.DingTalkConstant;
import com.taobao.api.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DingTalkService {
    @Autowired
    private DingTalkDao dingTalkDao;

    @Autowired
    DefaultDingTalkClient defaultDingTalkClient;

    public String getAccessToken(String corpId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("corpId", corpId);

        try {
            String accessToken = this.getAccessToken(jsonObject);
            System.out.println("accessToken========="+accessToken);
            return accessToken;

        } catch (ApiException e) {
            e.printStackTrace();
        }

        return null;

    }

    public JSONObject getUserInfoDetail(String code, String corpId){
        try {

            String accessToken = updateAccessToken(corpId);
            JSONObject userJson = this.getUserInfo(code, accessToken);

            defaultDingTalkClient.resetServerUrl(DingTalkConstant.OAPI_GETINFODETAIL_HOST);
            OapiUserGetRequest request = new OapiUserGetRequest();
            request.setUserid(userJson.getString("userId"));
            request.setHttpMethod("GET");
            OapiUserGetResponse response = defaultDingTalkClient.execute(request, accessToken);

            JSONObject returnJson = new JSONObject();
            returnJson.put("userId", userJson.getString("userId"));
            returnJson.put("name", response.getName());
            returnJson.put("mobile", response.getMobile());
            returnJson.put("token", accessToken);
            return returnJson;
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject getUserInfo(String code, String accessToken) throws ApiException {
        defaultDingTalkClient.resetServerUrl(DingTalkConstant.OAPI_GETINFO_HOST);
        OapiUserGetuserinfoRequest request = new OapiUserGetuserinfoRequest();
        request.setCode(code);
        request.setHttpMethod("GET");
        OapiUserGetuserinfoResponse response = defaultDingTalkClient.execute(request, accessToken);
        String userId = response.getUserid();
        System.out.println("userId========="+userId);
        JSONObject returnJson = new JSONObject();

        returnJson.put("userId", userId);
        return returnJson;
    }

    public String getAccessToken(JSONObject jsonObject) throws ApiException {
        JSONObject returnObject = dingTalkDao.getDingTalkCache(jsonObject);

        if(returnObject == null ){
            return updateAccessToken(jsonObject.getString("corpId"));
        } else {
            return returnObject.getString("value");
        }

    }

    public String updateAccessToken(String corpId) throws ApiException {
        defaultDingTalkClient.resetServerUrl(DingTalkConstant.OAPI_GETTOKEN_HOST);
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(DingTalkConstant.APP_KEY);
        request.setAppsecret(DingTalkConstant.APP_SECRET);
        request.setHttpMethod("GET");
        OapiGettokenResponse response = defaultDingTalkClient.execute(request);
        System.out.println("accessToken========="+response.getAccessToken());
        return  response.getAccessToken();
    }
}
