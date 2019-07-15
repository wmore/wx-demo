package com.zhouzhi.wangyue.controller.jsp;

import com.zhouzhi.wangyue.model.QrTicket;
import com.zhouzhi.wangyue.util.QrcodeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class QrcodeController {
    @Value("${com.constant.weixin.token}")
    public String token;//自己在微信测试平台设置的token
    @Value("${com.constant.weixin.appId}")
    public String appId;//自己在微信测试平台设置的appId
    @Value("${com.constant.weixin.secret}")
    public String secret;//自己在微信测试平台设置的secret


    @RequestMapping("/qr_code")
    public String qrCode(Model model) {
        ModelAndView mv = new ModelAndView();
        QrTicket ticket1 = QrcodeUtils.getTemporaryQRTicketBySceneId(appId, secret, 604800L, 111111L);
        model.addAttribute("qr_img_url_1", QrcodeUtils.genQrCodeImg(ticket1.getTicket()));
        QrTicket ticket2 = QrcodeUtils.getTemporaryQRTicketBySceneId(appId, secret, 604800L, 222222L);
        model.addAttribute("qr_img_url_2", QrcodeUtils.genQrCodeImg(ticket2.getTicket()));
        return "qrcode";
    }


}
