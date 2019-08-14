package cn.odboy.zilean.mybatis.cache;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.WeakCache;
import cn.hutool.core.date.DateUnit;
import cn.odboy.zilean.mybatis.constant.SqlConsts;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 田俊
 * @version 1.0.0
 * @name: DefaultMyBatisCache <br>
 * @description: TODO 自定义的Mybatis缓存 <br>
 * @date: 2019/04/2019/4/6 <br>
 * @since JDK 1.8
 */
public final class DefaultMyBatisCache implements Cache {
    private static final Logger log = LoggerFactory.getLogger(DefaultMyBatisCache.class);

    private String id;
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    /**
     * 过期时间：30分钟
     */
    private WeakCache<Object, Object> cache =
            CacheUtil.newWeakCache(DateUnit.MINUTE.getMillis() * 30);

    public DefaultMyBatisCache() {
        log.debug("Mybatis自定义缓存初始化");
    }

    /**
     * 必须有该构造函数
     *
     * @param id
     */
    public DefaultMyBatisCache(String id) {
        log.debug("Mybatis自定义缓存通过ID初始化");
        this.id = id;
    }

    /**
     * 获取缓存编号
     *
     * @return String
     */
    @Override
    public String getId() {
        log.debug("得到ID：" + id);
        return id;
    }

    /**
     * 获取缓存对象的大小
     *
     * @return int
     */
    @Override
    public int getSize() {
        log.debug("获取缓存大小！");
        return cache.size();
    }

    /**
     * 保存key值缓存对象
     *
     * @param key
     * @param value
     */
    @Override
    public void putObject(Object key, Object value) {
        log.debug("往缓存中添加元素：key=" + key + ",value=" + value);
        cache.put(key, value);
    }

    /**
     * 通过KEY获取缓存对象
     *
     * @param key
     * @return
     */
    @Override
    public Object getObject(Object key) {
        if (key != null && (SqlConsts.PH_SQL_FIND.contains(String.valueOf(key)))) {
            // 二级缓存不更新问题
            cache.remove(key);
        }
        log.debug("通过kEY获取值：" + key);
        log.debug("OVER");
        log.debug("=======================================================");
        log.debug("值为：" + cache.get(key));
        log.debug("=====================OVER==============================");
        return cache.get(key);
    }

    /**
     * 通过key删除缓存对象
     *
     * @param key
     * @return
     */
    @Override
    public Object removeObject(Object key) {
        log.debug("移除缓存对象：" + key);
        cache.remove(key);
        return key;
    }

    /**
     * 清空缓存
     */
    @Override
    public void clear() {
        log.debug("清除缓存！");
        cache.clear();
    }

    /**
     * 获取缓存的读写锁
     *
     * @return
     */
    @Override
    public ReadWriteLock getReadWriteLock() {
        log.debug("获取锁对象！！！");
        return lock;
    }
}
