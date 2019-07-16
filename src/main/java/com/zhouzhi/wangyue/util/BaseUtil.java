package com.zhouzhi.wangyue.util;

import com.zhouzhi.wangyue.model.AccessToken;
import com.zhouzhi.wangyue.model.WxAccessToken;
import com.zhouzhi.wangyue.service.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BaseUtil {

    @Autowired
    private AccessTokenService accessTokenService;

    /**
     * 获取token
     * @param appId
     * @param appSecert
     * @return
     */
    public WxAccessToken getAccessToken(String appId, String appSecert) {


        AccessToken token = accessTokenService.getToken();

        String url = "https://api.weixin.qq.com/cgi-bin/token";

        Map<String, String> data = new HashMap<>();
        data.put("grant_type", "client_credential");
        data.put("appid", appId);
        data.put("secret", appSecert);
        String response = HttpClientUtils.getInstance().getByMap(url, data);

        return WxAccessToken.fromJson(response);

    }



}
