package com.imooc.demo.web;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.imooc.demo.service.impl.DingTalkService;
import com.imooc.demo.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class DingTalkController {
    @Autowired
    DefaultDingTalkClient defaultDingTalkClient;

    @Autowired
    DingTalkService dingTalkService;

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public JSONObject getUserInfo(HttpServletRequest request){
        JSONObject jsonObject = CommonUtil.request2Json(request);
        String code = jsonObject.getString("code");
        String corpId = jsonObject.getString("corpId");
        return CommonUtil.successJson(dingTalkService.getUserInfoDetail(code, corpId));

    }

}
