package cn.odboy.zilean.dao;

import cn.odboy.zilean.entity.User;
import cn.odboy.zilean.mybatis.core.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author: Odboy
 * @version: Jdk 1.8
 * @desc: TODO 用户DAO
 * @time: 2019/8/8 10:38
 * @blog: www.odboy.cn
 */
@Repository
public interface IUserDao extends BaseDao<User> {
}