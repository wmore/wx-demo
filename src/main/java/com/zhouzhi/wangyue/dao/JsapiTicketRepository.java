package com.zhouzhi.wangyue.dao;

import com.zhouzhi.wangyue.model.db.JsapiTicket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface JsapiTicketRepository extends CrudRepository<JsapiTicket, Long> {

    @Query(value = "select * from t_jsapi_ticket order by id DESC limit 1", nativeQuery = true)
    JsapiTicket queryTicket();

}
