package com.zhouzhi.wangyue.dao;

import com.zhouzhi.wangyue.model.WechatUser;
import org.springframework.data.repository.CrudRepository;

public interface WechatUserRepository extends CrudRepository<WechatUser, Long> {
}
