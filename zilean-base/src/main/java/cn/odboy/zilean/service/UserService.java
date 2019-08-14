package cn.odboy.zilean.service;

import cn.odboy.zilean.dao.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Odboy
 * @version: Jdk 1.8
 * @desc: TODO 用户Service
 * @time: 2019/8/8 10:48
 * @blog: www.odboy.cn
 */
@Service
public class UserService {
    /**
     * 自动注入: 用户DAO
     */
    @Autowired
    private IUserDao userDao;
}
