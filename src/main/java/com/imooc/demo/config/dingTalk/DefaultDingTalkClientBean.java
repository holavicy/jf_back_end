package com.imooc.demo.config.dingTalk;

import com.dingtalk.api.DefaultDingTalkClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultDingTalkClientBean {

    @Bean
    public DefaultDingTalkClient getDefaultDingTalkClient() {
        DefaultDingTalkClient defaultDingTalkClient = new DefaultDingTalkClient(null);
        return defaultDingTalkClient;
    }
}
