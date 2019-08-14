package cn.odboy.zilean.mybatis.core;

import cn.odboy.zilean.mybatis.entity.BaseEntity;
import cn.odboy.zilean.mybatis.page.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @author: Odboy
 * @version: Jdk 1.8
 * @desc: TODO 通用Dao接口
 * <p>MyBatis提供了2个注解标记：@Select(只能处理字符串常量) 和 @SelectProvider(可以使用一个SQL提供程序来生成相应的SQL语句)
 * <p>这几个注解声明在Mapper对应的interface的方法上的，注解用于生成查询用的sql语句
 * 如果对应的Mapper中已使用@Param来注解参数，则在对应的Prodiver的方法中无需写参数
 * <p>注解中的参数： 　type参数指定的Class类，必须要能够通过无参的构造函数来初始化
 * method参数指定的方法，必须是public的，返回值必须为String，可以为static
 * <p>重点：在Mapper接口和@SelectProvide方法类中，不要使用重载，也就是说，不要使用方法名相同参数不同的方法
 * @time: 2019/8/8 10:41
 * @blog: www.odboy.cn
 */
public interface BaseDao<T extends BaseEntity> {
    /**
     * 选择性插入(非空属性值), 并返回受影响的行数
     * <p>
     * 插入后，可获取到id的值
     *
     * @param entity 实体对象
     * @return 受影响的行数
     */
    @InsertProvider(type = SqlBuilder.class, method = "insertSelective")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertSelective(T entity);

    /**
     * 通过ID删除, 并返回受影响的行数
     *
     * @param id 主键ID
     * @return 受影响的行数
     */
    @UpdateProvider(type = SqlBuilder.class, method = "deleteById")
    int deleteById(Long id);

    /**
     * 通过ID更新, 并返回受影响的行数
     *
     * @param entity 实体对象
     * @return 受影响的行数
     */
    @UpdateProvider(type = SqlBuilder.class, method = "updateSelectiveById")
    int updateSelectiveById(T entity);

    /**
     * 根据ID查询, 并返回单个实体对象
     * 注解ResultMap的意思是从sql的执行结果集中取数据，拼装成Enitity的实体类
     * 当mapper中传入的参数是使用@param
     * 注解修饰，在xxxProvider类中必须使用Map对象接收参数
     *
     * @param id 主键ID
     * @return 实体对象
     */
    @SelectProvider(type = SqlBuilder.class, method = "selectOneById")
    T selectOneById(Long id);

    /**
     * 通过类属性和值查询，并返回实体对象集合
     *
     * @param nav 类属性名称和值的容器
     * @return 实体对象集合
     */
    @SelectProvider(type = SqlBuilder.class, method = "selectByNav")
    List<T> selectByNav(@Param("nav") EntityPropNav nav);

    /**
     * 通过类属性和值模糊查询，并返回实体对象集合
     *
     * @param nav 类属性名称和值的容器
     * @return 实体对象集合
     */
    @SelectProvider(type = SqlBuilder.class, method = "selectByLikeNav")
    List<T> selectByLikeNav(@Param("nav") EntityPropNav nav);

    /**
     * 分页获取对象, 并返回实体对象集合
     *
     * @param entity 查询对象
     * @param page   分页对象
     * @return 实体对象集合
     */
    @SelectProvider(type = SqlBuilder.class, method = "selectByPage")
    List<T> selectByPage(@Param("entity") T entity, @Param("page") Page page);

    /**
     * 不分页, 查询所有(存在全表查询风险，不建议大表与超大表使用)
     *
     * @param entity 查询对象entity
     * @return 实体对象集合
     */
    @SelectProvider(type = SqlBuilder.class, method = "selectAll")
    List<T> selectAll(@Param("entity") T entity);

    /**
     * 不分页获取范围内对象, 并返回实体对象集合(这个估计会有点bug，如果查询字符串得加单引号不然mybatis会以为它是数据库字段名称)
     *
     * @param propName   对象属性名称
     * @param propValues 对象属性值集合
     * @return 实体对象集合
     */
    @SelectProvider(type = SqlBuilder.class, method = "selectAllInValues")
    List<T> selectAllInValues(@Param("propName") String propName, @Param("propValues") List<Object> propValues);

    /**
     * 条件统计数量
     *
     * @param entity 查询对象entity
     * @return 总数量
     */
    @SelectProvider(type = SqlBuilder.class, method = "countAll")
    long countAll(@Param("entity") T entity);
}
