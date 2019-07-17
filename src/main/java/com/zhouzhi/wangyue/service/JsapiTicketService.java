package com.zhouzhi.wangyue.service;

import com.zhouzhi.wangyue.dao.JsapiTicketRepository;
import com.zhouzhi.wangyue.model.db.JsapiTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JsapiTicketService {
    @Autowired
    private JsapiTicketRepository jsapiTicketRepository;

    @Transactional
    public void save(JsapiTicket jsapiTicket) {
        jsapiTicketRepository.save(jsapiTicket);
    }

    public JsapiTicket getTicket() {
        return jsapiTicketRepository.queryTicket();
    }

}
