package com.zhouzhi.wangyue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QrcodeController {

    @RequestMapping("/qr_code")
    public String qrCode() {
        return "qrcode";
    }

}
