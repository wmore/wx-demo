package com.zhouzhi.wangyue.service;

import com.zhouzhi.wangyue.dao.WechatUserRepository;
import com.zhouzhi.wangyue.model.WechatUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WechatUserService {
    @Resource
    private WechatUserRepository wechatUserRepository;

    @Transactional
    public void save(WechatUser wechatUser) {
        wechatUserRepository.save(wechatUser);
    }

    @Transactional
    public void saveAll(List<WechatUser> wechatUsers) {
        wechatUserRepository.save(wechatUsers);
    }

    @Transactional
    public void removeAll() {
        wechatUserRepository.deleteAll();
    }


}
