package cn.odboy.zilean.dao;


import cn.odboy.zilean.entity.OperationLog;
import cn.odboy.zilean.mybatis.core.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author: Odboy
 * @version: Jdk 1.8
 * @desc: TODO 操作日志DAO
 * @time: 2019/8/8 10:37
 * @blog: www.odboy.cn
 */
@Repository
public interface IOperationLogDao extends BaseDao<OperationLog> {
}