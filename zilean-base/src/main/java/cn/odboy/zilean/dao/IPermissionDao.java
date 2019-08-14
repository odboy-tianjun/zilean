package cn.odboy.zilean.dao;


import cn.odboy.zilean.entity.Permission;
import cn.odboy.zilean.mybatis.core.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author: Odboy
 * @version: Jdk 1.8
 * @desc: TODO 菜单权限DAO
 * @time: 2019/8/8 10:37
 * @blog: www.odboy.cn
 */
@Repository
public interface IPermissionDao extends BaseDao<Permission> {
}