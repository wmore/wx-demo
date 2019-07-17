package com.zhouzhi.wangyue.util;

import com.zhouzhi.wangyue.model.db.WechatUser;
import com.zhouzhi.wangyue.model.weixin.WxAccessToken;
import com.zhouzhi.wangyue.service.WechatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserUtils extends  BaseUtil{
    @Autowired
    private WechatUserService wechatUserService;

    @Value("${com.constant.weixin.appId}")
    public String appId;//自己在微信测试平台设置的appId
    @Value("${com.constant.weixin.secret}")
    public String secret;//自己在微信测试平台设置的secret

    public WechatUser getWechatUserInfo(String userOpenId) {

        WxAccessToken accessToken = getAccessToken(appId, secret);

        String url = "https://api.weixin.qq.com/cgi-bin/user/info";
        Map<String, String> data = new HashMap<>();
        data.put("access_token", accessToken.getAccessToken());
        data.put("openid", userOpenId);
        data.put("lang", "zh_CN");
        String resJson = HttpClientUtils.getInstance().getByMap(url, data);
        System.out.println(resJson);
        return WechatUser.fromJson(resJson);
    }


}
