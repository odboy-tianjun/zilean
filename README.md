# zilean 生态

### 模块说明
```txt
zilean-base     用户、数据字典等常见的实体类，为独立模块  
zilean-cache    redis、ehcache等，为独立模块  
zilean-common   各种常量、工具类等，为独立模块  
zilean-mybatis  jpa自动建表、mybatis二次封装，以简化重复的crud，为独立模块  
zilean-test     框架单元测试，为演示模块
```

### 使用
```txt
该项目使用IDEA进行开发, 以SpringBoot为基础, 下个上SpringMvc版本的.

1、修改zilean-test中"src/main/java/resources"下的application-database.properties中的
数据库连接url "spring.datasource.url" 为你的库

2、先运行，让其自动创建基础表
src\main\java\cn\odboy\zilean\test\ZileanTestApplication.java

3、在者就能够运行测试用例查看效果了
src\test\java\cn\odboy\zilean\test\demo\SqlBuilderTest.java

4、具体如何使用作者基本做到一看就明白我就不多说了.
```

### 联系方式
```txt
QQ：1943815081
QQ学习群: 587306042
WX：mxzf2014
```

### 贡献代码
```txt
最后如果该Demo对你有帮助不妨右上角点点Star支持一下，我更喜欢你
```

### 鸣谢
```txt
第三方jar中的各个作者，大爱.
```
