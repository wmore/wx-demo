package com.zhouzhi.wangyue.controller.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhouzhi.wangyue.util.JsSignUtil;
import com.zhouzhi.wangyue.util.WeiXinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

@RestController
@EnableAutoConfiguration
public class WechatRestController {

    @Autowired
    private WeiXinUtil WeiXinUtil;

    @Autowired
    private JsSignUtil jsSignUtil;


    @Value("${com.constant.weixin.token}")
    public String token;//自己在微信测试平台设置的token
    @Value("${com.constant.weixin.appId}")
    public String appId;//自己在微信测试平台设置的appId
    @Value("${com.constant.weixin.secret}")
    public String secret;//自己在微信测试平台设置的secret

    /**
     * 微信接入校验接口
     *
     * @param request
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/wx", method = RequestMethod.GET)
    public String handlePublicMsg(HttpServletRequest request) throws UnsupportedEncodingException {
        //设置编码，不然接收到的消息乱码
        request.setCharacterEncoding("UTF-8");
        String signature = request.getParameter("signature");//微信加密签名
        String timestamp = request.getParameter("timestamp");//时间戳
        String nonce = request.getParameter("nonce");//随机数
        String echostr = request.getParameter("echostr");//随机字符串
        PrintWriter out = null;
        //接入验证
        if (WeiXinUtil.checkSignature(signature, timestamp, nonce)) {
            return echostr;
        }
        System.out.println("不是微信服务器发过来的请求，请小心！");
        return null;
    }

    @RequestMapping(value = "/wx", method = RequestMethod.POST)
    public String handlePublicMsg2(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String respXml = WeiXinUtil.weixinMessageHandelCoreService(request, response);
        if (StringUtils.isEmpty(respXml)) {
            System.out.println("-------------处理微信消息失败-----------------------");
            return null;
        } else {
            System.out.println("----------返回微信消息处理结果-----------------------:" + respXml);
            return respXml;
        }

    }

    @RequestMapping(value = "/get_sign", method = RequestMethod.GET)
    public String getSign(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String domainUrl = request.getRequestURL().toString();
        System.out.println(domainUrl);
        String qrcodeUrl = domainUrl.replace("get_sign", "scan");
        System.out.println(qrcodeUrl);
        return JSON.toJSONString(jsSignUtil.sign(qrcodeUrl, appId, secret));
    }


}
