package com.tx.txcall.common.shiro;

import com.tx.txcall.common.components.RedisHandler;
import com.tx.txcall.common.utils.JWTUtil;
import com.tx.txcall.modules.system_base_users.api.entity.UserTbl;
import com.tx.txcall.modules.system_base_users.api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MyRealm extends AuthorizingRealm {


    private UserService userService;


    // 过期时间12小时
    @Value(value = "EXPIRE_TIME")
    private static long EXPIRE_TIME ;

    @Autowired
    private RedisHandler redisHandler;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = JWTUtil.getUsername(principals.toString());

        UserTbl userBean;
        if (redisHandler.exists(username)){
            //redis中未过期
            userBean = (UserTbl) redisHandler.get(username);
        }else {
            //redis过期，进行sql查询
            userBean = userService.loadUserByUsername(username);
            //redis保存
            redisHandler.set(username,userBean,EXPIRE_TIME);
        }

        //角色列表
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(userBean.getRoleTbls());
        //权限列表
        simpleAuthorizationInfo.addStringPermissions(userBean.getPermissions());
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws
                                                                                   AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比


        String username = JWTUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token invalid");
        }

        UserTbl userBean;
        if (redisHandler.exists(username)){
            //redis中未过期
            userBean = (UserTbl) redisHandler.get(username);
        }else {
            //redis过期，进行sql查询
            userBean = userService.loadUserByUsername(username);
            //redis保存
            redisHandler.set(username,userBean,EXPIRE_TIME);
        }

        if (userBean == null) {
            throw new AuthenticationException("User didn't existed!");
        }

        if (! JWTUtil.verify(token, username, userBean.getPassword())) {
            throw new AuthenticationException("Username or password error");
        }

        return new SimpleAuthenticationInfo(token, token, username);
    }
}
