package cn.odboy.zilean.biz.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.odboy.zilean.biz.IUserBiz;
import cn.odboy.zilean.bo.UserBo;
import cn.odboy.zilean.common.HttpResult;
import cn.odboy.zilean.common.constant.RedisKeyConsts;
import cn.odboy.zilean.dao.IRoleDao;
import cn.odboy.zilean.dao.IUserDao;
import cn.odboy.zilean.entity.Role;
import cn.odboy.zilean.entity.User;
import cn.odboy.zilean.map.UserBoMapper;
import cn.odboy.zilean.mybatis.core.EntityPropNav;
import cn.odboy.zilean.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: Odboy
 * @version: Jdk 1.8
 * @desc: TODO 用户相关业务实现类
 * @time: 2019/8/8 11:05
 * @blog: www.odboy.cn
 */
@Service("userBiz")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class UserBizImpl implements IUserBiz {
    /**
     * 自动注入: 用户DAO
     */
    @Autowired
    private IUserDao userDao;
    /**
     * 自动注入: 权限DAO
     */
    @Autowired
    private IRoleDao roleDao;
    /**
     * 自动注入: 用户Service
     */
    @Autowired
    private UserService userService;
    /**
     * 自动注入: Redis缓存
     */
    @Resource
    private RedisTemplate<Object, Object> redisTemplate;
    /**
     * 自动注入: UserDto转换器
     */
    @Resource
    private UserBoMapper userBoMapper;

    /**
     * 用户登录
     *
     * @param username 账号
     * @param password 密码
     * @return HttpResult
     */
    @Override
    public HttpResult login(String username, String password) {
        return null;
    }

    /**
     * 用户退登
     *
     * @param username 账号
     * @param token    登录成功后返回的token值
     * @return HttpResult
     */
    @Override
    public HttpResult logout(String username, String token) {
        return null;
    }

    /**
     * 获取用户菜单权限
     *
     * @param userId 用户UD
     * @return HttpResult
     */
    @Override
    public HttpResult getUserPermission(Long userId) {
        return null;
    }

    /**
     * 保存user登录信息，返回token
     *
     * @param username 用户名
     */
    @Override
    public String generateJwtToken(String username) {
        String salt = "123456";
        // 将salt保存到缓存中
        redisTemplate.opsForValue().set(StrUtil.format(RedisKeyConsts.TOKEN, username), salt, 3600, TimeUnit.SECONDS);
        return "";
    }

    /**
     * 获取上次token生成时的salt值和登录用户信息
     *
     * @param username 用户名
     * @return UserBo
     */
    @Override
    public UserBo getJwtTokenInfo(String username) {
        // 从缓存中取出token生成时用的salt
        String salt = String.valueOf(redisTemplate.opsForValue().get(StrUtil.format(RedisKeyConsts.TOKEN, username)));
        UserBo user = getUserInfo(username);
        user.setSalt(salt);
        return user;
    }

    /**
     * 清除token信息
     *
     * @param username 用户名
     */
    @Override
    public void cleanLoginInfo(String username) {
        // 删除缓存中保存的salt
        redisTemplate.delete(StrUtil.format(RedisKeyConsts.TOKEN, username));
    }

    /**
     * 获取数据库中保存的用户信息，主要是加密后的密码
     *
     * @param userName 用户名
     * @return UserBo
     */
    @Override
    public UserBo getUserInfo(String userName) {
        List<User> users = userDao.selectByNav(EntityPropNav.build("username", userName));
        if (CollUtil.isNotEmpty(users)) {
            return userBoMapper.userToBo(users.get(0));
        }
        return null;
    }

    /**
     * 获取用户角色，强烈建议从缓存中获取
     *
     * @param userId 用户ID
     * @return String
     */
    @Override
    public String getUserRole(Long userId) {
        List<User> users = userDao.selectByNav(EntityPropNav.build("id", userId));
        if (CollUtil.isNotEmpty(users)) {
            Long roleId = users.get(0).getRoleId();
            Role role = roleDao.selectOneById(roleId);
            return role.getValue();
        }
        return null;
    }
}
