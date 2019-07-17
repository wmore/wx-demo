package com.zhouzhi.wangyue.service;

import com.zhouzhi.wangyue.dao.WechatUserRepository;
import com.zhouzhi.wangyue.model.db.WechatUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WechatUserService {
    @Autowired
    private WechatUserRepository wechatUserRepository;

    @Transactional
    public void save(WechatUser wechatUser) {
        WechatUser user = wechatUserRepository.queryByOpenId(wechatUser.getOpenId());
        if (user != null){
            System.out.println(wechatUser.getNickName()+" ; " +wechatUser.getOpenId() +" ; 以前已经保存在数据库里啦。" );
            return ;
        }
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


    public List<WechatUser> getAll(){
        return (List<WechatUser>) wechatUserRepository.findAll();
    }


}
