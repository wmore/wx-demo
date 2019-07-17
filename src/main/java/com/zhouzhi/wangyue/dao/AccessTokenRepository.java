package com.zhouzhi.wangyue.dao;

import com.zhouzhi.wangyue.model.db.AccessToken;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface AccessTokenRepository extends CrudRepository<AccessToken, Long> {

    @Query(value = "select * from t_access_token order by id DESC limit 1", nativeQuery = true)
    AccessToken queryToken();

}
