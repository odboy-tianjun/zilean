package cn.odboy.zilean.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author: Odboy
 * @version: Jdk 1.8
 * @desc: TODO 应用入口
 * @time: 2019/8/11 7:25
 * @blog: www.odboy.cn
 * <p>
 * SpringBootApplication-scanBasePackages-->定义扫描的根节点
 * MapperScan-->定义扫描mapper接口所在包
 * EntityScan-->定义JPA自动建表扫描所在包
 */
@SpringBootApplication(scanBasePackages = "cn.odboy.zilean")
@MapperScan("cn.odboy.zilean.dao")
@EntityScan("cn.odboy.zilean.entity")
public class ZileanTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZileanTestApplication.class, args);
    }
}
