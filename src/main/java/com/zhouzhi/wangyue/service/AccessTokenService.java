package com.zhouzhi.wangyue.service;

import com.zhouzhi.wangyue.dao.AccessTokenRepository;
import com.zhouzhi.wangyue.model.db.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccessTokenService {
    @Autowired
    private AccessTokenRepository accessTokenRepository;

    @Transactional
    public void save(AccessToken accessToken) {
        accessTokenRepository.save(accessToken);
    }

    @Transactional
    public void saveAll(List<AccessToken> accessTokens) {
        accessTokenRepository.save(accessTokens);
    }

    @Transactional
    public void removeAll() {
        accessTokenRepository.deleteAll();
    }

    public AccessToken getToken() {
        return accessTokenRepository.queryToken();
    }

}
