package com.zhouzhi.wangyue.controller.rest;

import com.zhouzhi.wangyue.model.QrTicket;
import com.zhouzhi.wangyue.util.QrcodeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QrcodeRestController {


    @Value("${com.constant.weixin.token}")
    public String token;//自己在微信测试平台设置的token
    @Value("${com.constant.weixin.appId}")
    public String appId;//自己在微信测试平台设置的appId
    @Value("${com.constant.weixin.secret}")
    public String secret;//自己在微信测试平台设置的secret


    @RequestMapping("/qr_ticket")
    public String qrTicket() {
        QrTicket ticket = QrcodeUtils.getTemporaryQRTicketBySceneId(appId, secret, 604800L, 123L);
        return ticket.toString();
    }

    @RequestMapping("/qr_img_url")
    public String qrImgUrl() {
        QrTicket ticket = QrcodeUtils.getTemporaryQRTicketBySceneId(appId, secret, 604800L, 123L);
        return QrcodeUtils.genQrCodeImg(ticket.getTicket());
    }
}
