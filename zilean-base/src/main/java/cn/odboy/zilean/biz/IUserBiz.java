package cn.odboy.zilean.biz;

import cn.odboy.zilean.bo.UserBo;
import cn.odboy.zilean.common.HttpResult;

/**
 * @author: Odboy
 * @version: Jdk 1.8
 * @desc: TODO 用户相关业务
 * @time: 2019/8/8 10:56
 * @blog: www.odboy.cn
 */
public interface IUserBiz {
    /**
     * 用户登录
     *
     * @param username 账号
     * @param password 密码
     * @return HttpResult
     */
    HttpResult login(String username, String password);

    /**
     * 用户退登
     *
     * @param username 账号
     * @param token    登录成功后返回的token值
     * @return HttpResult
     */
    HttpResult logout(String username, String token);

    /**
     * 获取用户菜单权限
     *
     * @param userId 用户UD
     * @return HttpResult
     */
    HttpResult getUserPermission(Long userId);

    /**
     * 保存user登录信息，返回token
     *
     * @param username 用户名
     */
    String generateJwtToken(String username);

    /**
     * 获取上次token生成时的salt值和登录用户信息
     *
     * @param username 用户名
     * @return UserBo
     */
    UserBo getJwtTokenInfo(String username);

    /**
     * 清除token信息
     *
     * @param username 用户名
     */
    void cleanLoginInfo(String username);

    /**
     * 获取数据库中保存的用户信息，主要是加密后的密码
     *
     * @param userName 用户名
     * @return UserBo
     */
    UserBo getUserInfo(String userName);

    /**
     * 获取用户角色列表，强烈建议从缓存中获取
     *
     * @param userId 用户ID
     * @return List<String>
     */
    String getUserRole(Long userId);
}
