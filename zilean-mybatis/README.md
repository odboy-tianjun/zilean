# zilean-mybatis

简化单表重复的CRUD操作，有了它，无需手动建表，简单查询无需重复书写XML。封装隔离后，可轻松嵌入到任何系统里，目前仅针对MySQL有效

### 特别说明
```txt
封装隔离后，可轻松嵌入到任何系统里，目前仅针对MySQL有效
```

### 引用Jar
```txt
Lombok ---------------- 简化get/set/toString等方法, https://www.projectlombok.org/
Spring Data JPA ------- Java持久层API
MySQL Driver ---------- MySQL数据库驱动
MyBatis Framework ----- Mybatis框架
HuTool ---------------- 贼好用的Java工具类库
Druid ----------------- 阿里数据库连接池组件
Fastjson -------------- 阿里Json工具类
```

### 主要功能
```txt
简化单表crud操作, 基础方法无需书写xml也无需引用mybatis的crud注解
运用JPA实现自动建表, 你再也不用烦恼, 表字段究竟怎么取名才好
```

### 注意
```txt
1、BaseEntity子类必须有javax.persistence.Entity, javax.persistence.Table两个注解
2、BaseEntity子类中的@Table必须含有name属性, 并指定表名, 不然会导致建表失败, 并且BaseMapper也取不到表名
3、BaseDao子接口必须有org.springframework.stereotype.Repository注解
4、根据阿里巴巴Java编码规范, BaseEntity子类中不能出现不属于数据库表的字段, 如需关联查询, 请新建BaseVo的子类
5、建议简单的crud使用BaseDao中的方法, 复杂的关联查询使用xml
6、如果不需要, 或者不怎么会用Lombok的话, 只需要从pom中移除Lombok依赖,  在对应Lombok注解处加上get/set方法即可
```