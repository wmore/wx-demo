package com.zhouzhi.wangyue.controller;

import com.zhouzhi.wangyue.util.WeiXinUtil;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

@RestController
@EnableAutoConfiguration
public class WechatController {

    /**
     * 微信接入校验接口
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/wx", method = {RequestMethod.GET, RequestMethod.POST})
    public void wxLogin(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        //设置编码，不然接收到的消息乱码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String signature = request.getParameter("signature");//微信加密签名
        String timestamp = request.getParameter("timestamp");//时间戳
        String nonce = request.getParameter("nonce");//随机数
        String echostr = request.getParameter("echostr");//随机字符串
        PrintWriter out = null;
        //接入验证
        if (WeiXinUtil.checkSignature(signature, timestamp, nonce)) {
            if (echostr != null) {
                System.out.println(echostr);
                try {
                    out = response.getWriter();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                out.write(echostr);//验证成功返回的值
                return;
            }
        }

    }
}
