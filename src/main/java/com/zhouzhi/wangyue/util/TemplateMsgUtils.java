package com.zhouzhi.wangyue.util;

import com.alibaba.fastjson.JSONObject;
import com.zhouzhi.wangyue.model.db.WechatUser;
import com.zhouzhi.wangyue.model.weixin.WxAccessToken;
import com.zhouzhi.wangyue.service.WechatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TemplateMsgUtils extends BaseUtil {
    @Autowired
    private WechatUserService wechatUserService;

    @Value("${com.constant.weixin.appId}")
    public String appId;//自己在微信测试平台设置的appId
    @Value("${com.constant.weixin.secret}")
    public String secret;//自己在微信测试平台设置的secret
    @Value("${com.constant.weixin.templateId}")
    public String templateId;//

    String sendMsgUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

    public void sendMsgToAllUsers() {

        WxAccessToken accessToken = getAccessToken(appId, secret);

        String url = sendMsgUrl.replaceAll("ACCESS_TOKEN", accessToken.getAccessToken());

        List<WechatUser> userList = wechatUserService.getAll();

        for (WechatUser toUser : userList) {
            String openId = toUser.getOpenId();
            sendMsg(openId, url);
        }

    }


    public void sendMsgToUser(String openId) {
        WxAccessToken accessToken = getAccessToken(appId, secret);

        String url = sendMsgUrl.replaceAll("ACCESS_TOKEN", accessToken.getAccessToken());

        sendMsg(openId, url);
    }

    /**
     * {
     * "touser":"oVggq58HE3y9NwB7NCmd9rwjdKFk",
     * "template_id":"koqM7lm5iF0akjjb_o01I98v4r-hu1laPau75_2i67E",
     * "url":"http://weixin.qq.com/download",
     * "data":{
     * "first": {
     * "value":"今天（2019年7月17日）14:30有课程安排",
     * "color":"#173177"
     * },
     * "keyword1":{
     * "value":"音乐鉴赏初级课程",
     * "color":"#173177"
     * },
     * "keyword2": {
     * "value":"第5课时/共15课时",
     * "color":"#173177"
     * },
     * "keyword3": {
     * "value":"李洛冰",
     * "color":"#173177"
     * },
     * "keyword4": {
     * "value":"卡罗拉教育培训福州校区",
     * "color":"#173177"
     * },
     * "remark":{
     * "value":"点击进入课程学习，谢谢！",
     * "color":"#173177"
     * }
     * }
     * }
     *
     * @param openId
     */
    public void sendMsg(String openId, String url) {
        HashMap<String, String> first = new HashMap<String, String>() {{
            put("value", "今天（2019年7月17日）14:30有课程安排");
            put("color", "#173177");
        }};
        HashMap<String, String> keyword1 = new HashMap<String, String>() {{
            put("value", "音乐鉴赏初级课程");
            put("color", "#173177");
        }};
        HashMap<String, String> keyword2 = new HashMap<String, String>() {{
            put("value", "第5课时/共15课时");
            put("color", "#173177");
        }};
        HashMap<String, String> keyword3 = new HashMap<String, String>() {{
            put("value", "李洛冰");
            put("color", "#173177");
        }};
        HashMap<String, String> keyword4 = new HashMap<String, String>() {{
            put("value", "卡罗拉教育培训福州校区");
            put("color", "#173177");
        }};
        HashMap<String, String> remark = new HashMap<String, String>() {{
            put("value", "点击进入课程学习，谢谢!");
            put("color", "#173177");
        }};
        Map<String, Map> data = new HashMap<>();
        data.put("first", first);
        data.put("keyword1", keyword1);
        data.put("keyword2", keyword2);
        data.put("keyword3", keyword3);
        data.put("keyword4", keyword4);
        data.put("remark", remark);
        Map<String, Object> sendMap = new HashMap<>();
        sendMap.put("data", data);
        sendMap.put("touser", openId);
        sendMap.put("template_id", templateId);
        sendMap.put("url", "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140183");

        JSONObject dataJson = new JSONObject(sendMap);

        String resStr = HttpClientUtils.getInstance().jsonPost(url, dataJson);
        System.out.println(resStr);
    }

}
