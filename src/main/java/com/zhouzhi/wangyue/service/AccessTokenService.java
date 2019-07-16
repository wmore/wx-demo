package com.zhouzhi.wangyue.service;

import com.zhouzhi.wangyue.dao.AccessTokenRepository;
import com.zhouzhi.wangyue.model.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AccessTokenService {
    @Autowired
    private AccessTokenRepository accessTokenRepository;

    @Transactional
    public void save(AccessToken accessToken) {
        AccessToken tokenExist = accessTokenRepository.queryToken();
        if (tokenExist != null) {
            Date nowDate = new Date();
            long diff = nowDate.getTime() - tokenExist.getAccess_time().getTime();
            if (diff < 0 || diff > tokenExist.getExpires_in()) {
                tokenExist.setAccess_time(nowDate);
                tokenExist.setAccess_token(accessToken.getAccess_token());
                accessTokenRepository.save(tokenExist);
            }
        } else {
            accessToken.setAccess_time(new Date());
            accessTokenRepository.save(accessToken);
        }
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
