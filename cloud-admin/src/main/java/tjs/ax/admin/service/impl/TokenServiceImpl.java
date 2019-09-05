package tjs.ax.admin.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tjs.ax.admin.dao.TokenDao;
import tjs.ax.admin.domain.Token;
import tjs.ax.admin.service.TokenService;

import java.util.Date;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    TokenDao tokenDao;
    //过期时间，单位s
    private final static int EXPIRE = 60*30;
    @Override
    public String createToken(Long userId) {
        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime()+EXPIRE*1000);
        Token token = new Token();
        token.setUserId(userId);
        token.setUpdateTime(now);
        token.setToken(UUID.randomUUID().toString());
        token.setExpireTime(expireTime);
        if(tokenDao.getTokenByUserId(userId)!=null){
            tokenDao.update(token);
        }else{
            tokenDao.save(token);
        }

        return token.getToken();
    }

    @Override
    public Long getUserIdByToken(String token) {
        Token tokenDO = tokenDao.getToken(token);
        if (tokenDO==null){
            return -1L;
        }else {
            if (tokenDO.getExpireTime().getTime()<System.currentTimeMillis()){
                tokenDao.removeToken(token);
                return -1L;
            }
            return tokenDO.getUserId();
        }
    }

    @Override
    public boolean removeToken(String token) {
        return tokenDao.removeToken(token);
    }
}
