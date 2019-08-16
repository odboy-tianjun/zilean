package cn.odboy.zilean.test.demo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.odboy.zilean.dao.IUserDao;
import cn.odboy.zilean.entity.User;
import cn.odboy.zilean.mybatis.core.EntityPropNav;
import cn.odboy.zilean.mybatis.page.Page;
import cn.odboy.zilean.test.ZileanTestApplicationTests;
import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.List;

/**
 * @author: Odboy
 * @version: Jdk 1.8
 * @desc: TODO SqlHandle功能单元测试
 * @time: 2019/8/9 7:22
 * @blog: www.odboy.cn
 */
@Log4j2
public class SqlBuilderTest extends ZileanTestApplicationTests {
    @Autowired
    private IUserDao userDao;
	
	/**
	 * 单元测试默认是回滚的，所以得加@Rollback(false)才能看到效果
	 */
    @Test
	@Rollback(false)
    public void testInsert() {
        log.info("新增");
        User user;
        for (int i = 0; i < 100000; i++) {
            user = new User();
            user.setUsername("tianjun" + i);
            user.setUserpwd("123456");
            user.setBgmUrl("背景音乐Url");
            user.setAutograph("如果你的人生没有那么多的喝彩，就请默默努力吧");
            user.setBirth(DateTime.now());
            user.setHometown("江西省赣州市上犹县");
            user.setJoinWorkDate(DateTime.now());
            user.setLastLoginTime(DateTime.now());
            user.setMaritalStatus("全栈工程师");
            user.setPhoneNum("13621988781");
            user.setRealName("田俊");
            user.setLogoUrl("头像Url");
            user.setRemark("呵呵哒~");
            user.setSex(Byte.valueOf("0"));
            user.setCreateTime(DateTime.now());
            log.info(JSON.toJSONString(user));
            Assert.assertSame("受影响的行数", 1, userDao.insertSelective(user));
        }
    }

    @Test
    @Rollback(false)
    public void testUpdate() {
        User user = new User();
        log.info("修改");
        user.setId(2L);
        user.setBgmUrl("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        Assert.assertSame("受影响的行数", 1, userDao.updateSelectiveById(user));
    }

    @Test
    @Rollback(false)
    public void testDelete() {
        log.info("删除");
        Assert.assertSame("受影响的行数", 1, userDao.deleteById(201L));
    }

    @Test
    public void testQuery() {
        List<User> users;
        User user;
        long total;

        log.info("通过主键ID查询");
        user = userDao.selectOneById(2L);
        log.info("user=" + JSON.toJSONString(user));

        log.info("通过对象属性名称和值查询");
        users = userDao.selectByNav(EntityPropNav.build("username", "tianjun"));
        log.info("users=" + JSON.toJSONString(users));
        users = userDao.selectByNav(EntityPropNav.build("id", 2));
        log.info("users=" + JSON.toJSONString(users));

        log.info("通过对象属性名称和值模糊查询");
        users = userDao.selectByLikeNav(EntityPropNav.build("username", "tian"));
        log.info("users=" + JSON.toJSONString(users));

        log.info("无条件分页查询");
        users = userDao.selectByPage(null, new Page());
        log.info("users=" + JSON.toJSONString(users));

        log.info("条件分页查询");
        user = new User();
        user.setId(2L);
        users = userDao.selectByPage(user, new Page());
        log.info("users=" + JSON.toJSONString(users));


        log.info("不分页查询所有");
        users = userDao.selectAll(null);
        log.info("users=" + JSON.toJSONString(users));

        log.info("含条件不分页查询所有");
        user = new User();
        user.setUsername("tianjun1");
        users = userDao.selectAll(user);
        log.info("users=" + JSON.toJSONString(users));

        log.info("简易 in 查询");
        users = userDao.selectAllInValues("id", CollUtil.newArrayList(1, 2, 4, 5));
        log.info("users=" + JSON.toJSONString(users));
        users = userDao.selectAllInValues("username", CollUtil.newArrayList("'tianjun1'", "'tianjun3'"));
        log.info("users=" + JSON.toJSONString(users));

        log.info("无条件统计数量");
        total = userDao.countAll(null);
        log.info("total=" + total);

        log.info("含条件统计数量");
        user = new User();
        user.setUsername("tianjun1");
        total = userDao.countAll(user);
        log.info("total=" + total);
    }
}
