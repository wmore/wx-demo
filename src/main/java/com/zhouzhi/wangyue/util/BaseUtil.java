package com.zhouzhi.wangyue.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhouzhi.wangyue.model.db.AccessToken;
import com.zhouzhi.wangyue.model.db.JsapiTicket;
import com.zhouzhi.wangyue.model.weixin.WxAccessToken;
import com.zhouzhi.wangyue.service.AccessTokenService;
import com.zhouzhi.wangyue.service.JsapiTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class BaseUtil {

    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    private JsapiTicketService jsapiTicketService;

    /**
     * 获取token
     *
     * @param appId
     * @param appSecert
     * @return
     */
    public WxAccessToken getAccessToken(String appId, String appSecert) {

        AccessToken tokenExist = accessTokenService.getToken();

        if (tokenExist != null) {
            Date nowDate = new Date();
            long diff = (nowDate.getTime() - tokenExist.getAccessTime().getTime()) / 1000;// 秒
            if (diff > 0 && diff < tokenExist.getExpiresIn() - 200) {
                return new WxAccessToken(tokenExist);
            }
        }

        String url = "https://api.weixin.qq.com/cgi-bin/token";

        Map<String, String> data = new HashMap<>();
        data.put("grant_type", "client_credential");
        data.put("appid", appId);
        data.put("secret", appSecert);
        String response = HttpClientUtils.getInstance().getByMap(url, data);

        WxAccessToken newToken = WxAccessToken.fromJson(response);
        accessTokenService.save(new AccessToken(newToken));
        return newToken;
    }

    /**
     *  
     *      * 获取jsapi_ticket
     *      *  
     *      * @param appid 凭证 
     *      * @param appsecret 密钥 
     *      * @return 
     *      
     */
    public String getJsapiTicket(String accessToken) {

        JsapiTicket jsapiTicket = jsapiTicketService.getTicket();

        if (jsapiTicket != null) {
            Date nowDate = new Date();
            long diff = (nowDate.getTime() - jsapiTicket.getAccessTime().getTime()) / 1000;// 秒
            if (diff > 0 && diff < jsapiTicket.getExpiresIn() - 200) {
                return jsapiTicket.getTicket();
            }
        }

        //获取公众号jsapi_ticket的链接
        String jsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
        if (accessToken != null) {
            String requestUrl = jsapi_ticket_url.replace("ACCESS_TOKEN", accessToken);
            String res = HttpClientUtils.getInstance().get(requestUrl);
            System.out.println("getticket:" + res);
            JSONObject jsonObject = JSON.parseObject(res);
            // 如果请求成功 
            if (null != jsonObject) {
                return jsonObject.getString("ticket");
            }
        } else {
            System.out.println("*****token为空 获取ticket失败******");
        }

        return "";
    }
}
