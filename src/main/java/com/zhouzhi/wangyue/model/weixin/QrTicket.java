package com.zhouzhi.wangyue.model.weixin;

public class QrTicket {


   private int expireSeconds;
   private String ticket;
   private String url;

    public int getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(int expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public QrTicket() {
    }

    public QrTicket(int expireSeconds, String ticket, String url) {
        this.expireSeconds = expireSeconds;
        this.ticket = ticket;
        this.url = url;
    }


    @Override
    public String toString() {
        return "QrTicket{" +
                "expireSeconds=" + expireSeconds +
                ", ticket='" + ticket + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
