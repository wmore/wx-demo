package com.zhouzhi.wangyue.dao;

import com.zhouzhi.wangyue.model.AccessToken;
import com.zhouzhi.wangyue.model.WechatUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface AccessTokenRepository extends CrudRepository<AccessToken, Long> {

    @Query(value = "select * from t_access_token where limit 0,1", nativeQuery = true)
    AccessToken queryToken();

}
