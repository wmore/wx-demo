package com.zhouzhi.wangyue.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhouzhi.wangyue.model.weixin.QrTicket;
import com.zhouzhi.wangyue.model.weixin.WxAccessToken;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URL;

@Component
public class QrcodeUtils extends BaseUtil{
    //生成临时二维码ticket scene_id json
    private static final String QRCODE_TEMPORARY_TICKET_SCENE_ID = "{\"expire_seconds\": SECONDS, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": SCENEID}}}";

    //生成临时二维码ticket scene_str json
    private static final String QRCODE_TEMPORARY_TICKET_SCENE_STR = "{\"expire_seconds\": SECONDS, \"action_name\": \"QR_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": SCENESTR}}}";

    //生成临时二维码ticket 的url
    private static final String QR_TEMPORARY_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";

    //使用scene_id生成临时二维码ticket
    public QrTicket getTemporaryQRTicketBySceneId(String appId, String appSecert,
                                                         Long expireSeconds, Long sceneId) {
        WxAccessToken accessToken = getAccessToken(appId, appSecert);
        String url = QR_TEMPORARY_TICKET_URL.replace("TOKEN", accessToken.getAccessToken());
        String postJson = QRCODE_TEMPORARY_TICKET_SCENE_ID.replace("SECONDS", String.valueOf(expireSeconds))
                .replace("SCENEID", String.valueOf(sceneId));
        String resStr = HttpClientUtils.getInstance().jsonPost(url, JSON.parseObject(postJson));
        resStr = resStr.replace("\\", "");
        if (resStr.startsWith("\"") && resStr.endsWith("\"")) {
            resStr = resStr.substring(1, resStr.length() - 1);
        }
        JSONObject res = JSON.parseObject(resStr);
        QrTicket qrTicket = new QrTicket();
        qrTicket.setExpireSeconds((Integer) res.get("expire_seconds"));
        qrTicket.setTicket(res.getString("ticket"));
        qrTicket.setUrl(res.getString("url"));

        return qrTicket;
    }


    public String genQrCodeImg(String ticket) {
        String url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
        url = url.replace("TICKET", ticket);
        return url;
    }

    /**
     * 将二维码和背景图片拼装成新的分享图片
     *
     * @param shareImageUrl 分享背景图片的地址
     * @param qrCodeUrl     二维码图片的地址
     * @param content       文字内容
     * @return base64 String 的图片
     */
    public String getQrImgUrl(String shareImageUrl, String qrCodeUrl, String content) {
        try {
            // 读取背景图片
            BufferedImage bgImage = ImageIO.read(new URL(shareImageUrl));
            // 读取二维码图片
            BufferedImage qrCodeImage = ImageIO.read(new URL(qrCodeUrl));

            // 创建一张原始图片
            BufferedImage bgPicture = new BufferedImage(bgImage.getWidth(null), bgImage.getHeight(null), BufferedImage.TYPE_INT_RGB);

            // 开启画图
            Graphics2D g = bgPicture.createGraphics();
            // 绘制背景图片
            g.drawImage(bgImage, 0, 0, null);
            // 设置目标重叠的混合处理方式和透明度
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1));
            // 580*790
            int starX = bgImage.getWidth(null) * 45 / 580; // 二维码距背景图片X轴距离
            int startY = bgImage.getHeight(null) * 480 / 790; // 二维码距背景图片Y轴距离
            // 绘制二维码图片
            g.drawImage(qrCodeImage, starX, startY, 124, 124, null);
            // 绘制文字
//            g.setColor(new Color(139,142,147));
//            g.setFont(new Font("微软雅黑", Font.PLAIN, 18));
//            g.drawString(content, starX, startY + 124 + 30);
            g.dispose();

            // 将合成的图片转为base64String
            ByteArrayOutputStream compositeImageByte = new ByteArrayOutputStream();
            ImageIO.write(bgPicture, "png", compositeImageByte);
            //转换成base64串
            String pngBase64 = new BASE64Encoder().encodeBuffer(compositeImageByte.toByteArray()).trim();
            pngBase64 = "data:image/png;base64," + pngBase64.replaceAll("\n", "").replaceAll("\r", "");

            System.out.println("create base64 image success");
            return pngBase64;
        } catch (Exception e) {
            System.out.println("create base64 image error");
            return null;
        }
    }


}
