package com.tx.txcall.common.components;
/**
 * Created by wyh in 2019/5/5 14:34
 **/


import com.tx.txcall.common.utils.JWTUtil;
import com.tx.txcall.modules.system_base_users.api.entity.UserTbl;
import lombok.Data;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @program:
 * @description: 存储shiro-session-redis中的用户信息
 * @author: forever-wang
 * @create: 2019-05-05 14:34
 **/
@Data
@Component
public class UserDetailHelper {

    @Autowired
    private RedisHandler redisHandler;

    Logger logger = LoggerFactory.getLogger(UserDetailHelper.class);
    public UserTbl getUserTbl(){
        logger.info("从redis取出 用户");
        String principal = (String) SecurityUtils.getSubject().getPrincipal();

        if (StringUtils.isEmpty(principal)){
            throw new RuntimeException("token 识别错误");
        }
        String username = JWTUtil.getUsername(principal);


        logger.info("成功从redis取出 用户 {}",username);
        UserTbl userTbl = (UserTbl) redisHandler.get(username);
        if (userTbl==null){
            throw new RuntimeException("认证已过期，请重新登录！");
        }
        return userTbl;


//        UserTbl userTbl=new UserTbl();
//        userTbl.setUserId(1).setCompanyId(1).setEnterpriseId(1).setDepartmentId(1);
//        return userTbl;
    }

}
