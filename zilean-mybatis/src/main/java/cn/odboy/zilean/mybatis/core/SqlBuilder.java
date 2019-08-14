package cn.odboy.zilean.mybatis.core;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.PageUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.odboy.zilean.common.constant.GlobalConsts;
import cn.odboy.zilean.mybatis.constant.SqlConsts;
import cn.odboy.zilean.mybatis.entity.BaseEntity;
import cn.odboy.zilean.mybatis.page.Page;
import cn.odboy.zilean.mybatis.util.BeanColumnUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderContext;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.core.annotation.AnnotationUtils;

import javax.persistence.Table;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Odboy
 * @version: Jdk 1.8
 * @desc: TODO SQL构造器
 * @time: 2019/8/8 10:35
 * @blog: www.odboy.cn
 */
@Log4j2
public class SqlBuilder<T extends BaseEntity> {
    /**
     * 表名
     */
    private String tableName = null;
    /**
     * 表字段映射
     */
    private String baseColumnList = null;
    /**
     * Bean属性名称
     */
    private List<String> beanNames = new ArrayList<>(GlobalConsts.DEFAULT_COLLECTIONS_SIZE);
    /**
     * Bean属性名称-->数据库表名 映射关系
     */
    private Map<String, String> beanTableMap = new HashMap<>(GlobalConsts.DEFAULT_COLLECTIONS_SIZE);
    /**
     * 数据库表名-->get方法名称
     */
    private Map<String, String> tableGetNameMap = new HashMap<>(GlobalConsts.DEFAULT_COLLECTIONS_SIZE);

    /**
     * 初始化
     *
     * @param context ProviderContext
     * @throws Exception
     */
    private void init(ProviderContext context) throws Exception {
        if (StrUtil.isBlank(tableName)) {
            if (!initTableName(context)) {
                throw new RuntimeException(SqlConsts.TX_TABLE_NONE);
            }
        }
    }

    /**
     * 获取并设置表名
     *
     * @param context ProviderContext
     * @return boolean
     */
    private boolean initTableName(ProviderContext context) throws Exception {
        Class mapperType = context.getMapperType();
        // 强制转换为“参数化类型” [BaseDao<T extends BaseEntity>]
        ParameterizedType genericSuperclass = (ParameterizedType) mapperType.getGenericInterfaces()[0];
        // Class类是Type接口的实现类
        // 获取参数化类型中，实际类型的定义 [new Type[]{T.class}]
        // 获取数据的第一个元素：T.class
        // 当前运行类的参数化类型中的实际的类型, 比如BaseMapper<User>, 此处的Class为User.class
        Class clazz = (Class) genericSuperclass.getActualTypeArguments()[0];
        // 获取表名
        // 当entity类有注解时
        // @javax.persistence.Table(name=sso_user, ...), 自动建表的表名称不得以"t_"或其他前缀开头，只能是类名下换线命名
        // 没有注解, 返回null
        Table table = AnnotationUtils.getAnnotation(clazz, Table.class);
        if (table != null) {
            this.tableName = table.name();
            // 获取bean属性名称
            // 根据jpa内定策略转换为表字段名称
            Field[] fields = ReflectUtil.getFields(clazz);
            String sqlColumnName, fieldName;
            for (Field field : fields) {
                fieldName = field.getName();
                sqlColumnName = BeanColumnUtils.getDataBaseColumnName(fieldName);
                // 建立映射关系
                beanNames.add(fieldName);
                beanTableMap.put(fieldName, sqlColumnName);
                tableGetNameMap.put(sqlColumnName, SqlConsts.PH_SQL_GET + BeanColumnUtils.toUpperCaseFirstOne(fieldName));
                // 设置查询字段
                // 定义临时变量
                StringBuilder column = new StringBuilder();
                // fix bug: 20190703 由于驼峰命名的原因导致查出来的数据不能设置到实体类中
                for (Map.Entry<String, String> entry : this.beanTableMap.entrySet()) {
                    // value, 即实体类对应的表字段名称
                    column.append(entry.getValue()).append(" as ");
                    // value, 即实体类本身的属性名称
                    column.append(entry.getKey()).append(",");
                }
                // 去除末尾的逗号
                this.baseColumnList = column.substring(0, column.length() - 1);
            }
            return true;
        }
        return false;
    }

    /**
     * 选择性插入
     *
     * @param context 上下文
     * @param entity  <T extends BaseEntity>
     * @return SQL
     */
    public String insertSelective(ProviderContext context, T entity) throws Exception {
        this.init(context);
        if (entity == null) {
            throw new RuntimeException("entity不能为null");
        }
        // 非空属性数量
        int nonNullPropSize = 0;
        // 参数字段 有序合集
        List<String> columns = new ArrayList<>(GlobalConsts.DEFAULT_COLLECTIONS_SIZE);
        // 参数字段值 有序合集
        List<String> values = new ArrayList<>(GlobalConsts.DEFAULT_COLLECTIONS_SIZE);
        List<String> nonNullList = getNonNullList(entity, beanNames, tableGetNameMap, beanTableMap);
        for (String beanName : nonNullList) {
            // 此处不能用nonNullPropSize++, ++操作符是线程不安全的
            nonNullPropSize = nonNullPropSize + 1;
            // 获取属性名称
            columns.add(beanTableMap.get(beanName));
            values.add("#{" + beanName + "}");
        }
        // 判断是不是什么都没做
        if (nonNullPropSize == 0) {
            throw new RuntimeException("至少有一个字段有值!");
        }
        // 组装SQL
        SQL sql = new SQL();
        sql.INSERT_INTO(SqlBuilder.this.tableName);
        sql.INTO_COLUMNS(columns.toArray(new String[0]));
        sql.INTO_VALUES(values.toArray(new String[0]));
        log.info("SQL -> " + sql.toString());
        log.info("Entity -> " + JSON.toJSONString(entity));
        return sql.toString();
    }

    /**
     * 通过ID(逻辑)删除
     *
     * @param context 上下文
     * @param id      主键值
     * @return SQL
     */
    public String deleteById(ProviderContext context, Long id) throws Exception {
        this.init(context);
        if (id == null) {
            throw new RuntimeException("id不能为null");
        }
        // 组装SQL
        SQL sql = new SQL();
        sql.UPDATE(this.tableName);
        sql.SET(SqlConsts.EX_SQL_UNEFFECTIVE_DATA);
        sql.WHERE(SqlConsts.PRIMARY_KEY_NAME + "=#{" + SqlConsts.PRIMARY_KEY_NAME + "}");
        log.info("SQL -> " + sql.toString());
        log.info("ID -> " + id);
        return sql.toString();
    }

    /**
     * 通过ID更新
     *
     * @param context 上下文
     * @param entity  <T extends BaseEntity>
     * @return SQL
     */
    public String updateSelectiveById(ProviderContext context, T entity) throws Exception {
        this.init(context);
        log.info(JSON.toJSONString(context));
        // 如果ID为空
        if (ReflectUtil.invoke(entity, SqlConsts.GET_ID) == null) {
            throw new RuntimeException("id不能为null");
        }
        // 设置更新时间
        ReflectUtil.invoke(entity, SqlConsts.SET_UPDATE_TIME, DateTime.now());
        // 组装SQL
        SQL sql = new SQL();
        sql.UPDATE(this.tableName);
        List<String> nonNullList = getNonNullList(entity, beanNames, tableGetNameMap, beanTableMap);
        for (String beanName : nonNullList) {
            // 不更新id字段
            if (SqlConsts.PRIMARY_KEY_NAME.equalsIgnoreCase(beanName)) {
                continue;
            }
            // 配置映射关系
            sql.SET(this.beanTableMap.get(beanName) + "=#{" + beanName + "}");
        }
        sql.WHERE(SqlConsts.PRIMARY_KEY_NAME + "=#{" + SqlConsts.PRIMARY_KEY_NAME + "}");
        sql.AND();
        sql.WHERE(SqlConsts.EX_SQL_EFFECTIVE_DATA);
        log.info("SQL -> " + sql.toString());
        log.info("Entity -> " + JSON.toJSONString(entity));
        return sql.toString();
    }

    /**
     * 通过ID查询
     *
     * @param context 上下文
     * @param id      主键
     * @return SQL
     */
    public String selectOneById(ProviderContext context, Long id) throws Exception {
        this.init(context);
        if (id == null) {
            throw new RuntimeException("id不能为null");
        }
        // 组装SQL
        SQL sql = new SQL();
        sql.SELECT(this.baseColumnList);
        sql.FROM(this.tableName);
        sql.WHERE(SqlConsts.PRIMARY_KEY_NAME + "=" + id);
        sql.AND();
        sql.WHERE(SqlConsts.EX_SQL_EFFECTIVE_DATA);
        log.info("SQL -> " + sql.toString());
        log.info("ID -> " + id);
        return sql.toString();
    }

    /**
     * 通过类属性和值查询，并返回实体对象集合
     *
     * @param context 上下文
     * @param nav     类属性名称和值的容器
     * @return SQL
     */
    public String selectByNav(ProviderContext context, @Param("nav") EntityPropNav nav) throws Exception {
        this.init(context);
        if (nav == null) {
            throw new RuntimeException("nav不能为null");
        }
        if (nav.getName() == null) {
            throw new RuntimeException("nav.name不能为null");
        }
        // 组装SQL
        SQL sql = new SQL();
        sql.SELECT(this.baseColumnList);
        sql.FROM(this.tableName);
        sql.WHERE(beanTableMap.get(String.valueOf(nav.name)) + "=#{nav.value}");
        sql.WHERE(SqlConsts.EX_SQL_EFFECTIVE_DATA);
        sql.ORDER_BY(SqlConsts.PR_SQL_ORDER_ID);
        log.info("SQL -> " + sql.toString());
        log.info("Nav -> " + JSON.toJSONString(nav));
        return sql.toString();
    }

    /**
     * 通过类属性和值模糊查询，并返回实体对象集合
     *
     * @param context 上下文
     * @param nav     类属性名称和值的容器
     * @return SQL
     */
    public String selectByLikeNav(ProviderContext context, @Param("nav") EntityPropNav nav) throws Exception {
        this.init(context);
        if (nav == null) {
            throw new RuntimeException("nav不能为null");
        }
        if (nav.getName() == null) {
            throw new RuntimeException("nav.name不能为null");
        }
        if (nav.getValue() == null) {
            throw new RuntimeException("nav.value不能为null");
        }
        // 组装SQL
        SQL sql = new SQL();
        sql.SELECT(this.baseColumnList);
        sql.FROM(this.tableName);
        sql.WHERE(beanTableMap.get(String.valueOf(nav.name)) + " like CONCAT('%',#{nav.value},'%')");
        sql.WHERE(SqlConsts.EX_SQL_EFFECTIVE_DATA);
        sql.ORDER_BY(SqlConsts.PR_SQL_ORDER_ID);
        log.info("SQL -> " + sql.toString());
        log.info("Nav -> " + JSON.toJSONString(nav));
        return sql.toString();
    }

    /**
     * 分页获取对象, 并返回实体对象集合
     *
     * @param context 上下文
     * @param entity  查询对象
     * @param page    分页对象
     * @return SQL
     */
    public String selectByPage(ProviderContext context, @Param("entity") T entity, @Param("page") Page page)
            throws Exception {
        this.init(context);
        if (page == null) {
            throw new RuntimeException(SqlConsts.TX_PAGE_NULL);
        }
        // 组装SQL
        SQL sql = new SQL();
        sql.SELECT(this.baseColumnList);
        sql.FROM(this.tableName);
        List<String> nonNullList = getNonNullList(entity, beanNames, tableGetNameMap, beanTableMap);
        for (String beanName : nonNullList) {
            sql.WHERE(this.beanTableMap.get(beanName) + "=#{entity." + beanName + "}");
        }
        sql.WHERE(SqlConsts.EX_SQL_EFFECTIVE_DATA);
        int[] startEndIndex = PageUtil.transToStartEnd(page.getPage(), page.getPageSize());
        sql.ORDER_BY(SqlConsts.PR_SQL_ORDER_ID + StrUtil.format(SqlConsts.PR_SQL_PAGE, startEndIndex[0], startEndIndex[1]));
        log.info("SQL -> " + sql.toString());
        log.info("entity -> " + JSON.toJSONString(entity));
        log.info("Page -> " + JSON.toJSONString(page));
        return sql.toString();
    }

    /**
     * 不分页, 查询所有(存在全表查询风险，不建议大表与超大表使用)
     *
     * @param context 上下文
     * @param entity  非空, 查询对象entity
     * @return SQL
     */
    public String selectAll(ProviderContext context, @Param("entity") T entity) throws Exception {
        this.init(context);
        // 组装SQL
        SQL sql = new SQL();
        sql.SELECT(this.baseColumnList);
        sql.FROM(this.tableName);
        List<String> nonNullList = getNonNullList(entity, beanNames, tableGetNameMap, beanTableMap);
        for (String beanName : nonNullList) {
            sql.WHERE(this.beanTableMap.get(beanName) + "=#{entity." + beanName + "}");
        }
        sql.WHERE(SqlConsts.EX_SQL_EFFECTIVE_DATA);
        sql.ORDER_BY(SqlConsts.PR_SQL_ORDER_ID);
        log.info("SQL -> " + sql.toString());
        log.info("entity -> " + JSON.toJSONString(entity));
        return sql.toString();
    }

    /**
     * 不分页获取范围内对象, 并返回实体对象集合(这个估计会有点bug，如果查询字符串得加单引号不然mybatis会以为它是数据库字段名称)
     *
     * @param context    上下文
     * @param propName   对象属性名称
     * @param propValues 对象属性值集合
     * @return SQL
     */
    public String selectAllInValues(ProviderContext context, @Param("propName") String propName,
                                    @Param("propValues") List<Object> propValues) throws Exception {
        this.init(context);
        if (propName == null) {
            throw new RuntimeException("propName不能为null");
        }
        if (CollectionUtil.isEmpty(propValues)) {
            throw new RuntimeException("propValues不能为null、或存在为null的对象");
        }
        // 组合ID
        StringBuilder idsArrays = new StringBuilder();
        for (Object propValue : propValues) {
            idsArrays.append(propValue);
            idsArrays.append(",");
        }
        SQL sql = new SQL();
        sql.SELECT(this.baseColumnList);
        sql.FROM(this.tableName);
        sql.WHERE(beanTableMap.get(propName) + " in (" + idsArrays.substring(0, idsArrays.length() - 1) + ")");
        sql.AND();
        sql.WHERE(SqlConsts.EX_SQL_EFFECTIVE_DATA);
        log.info("SQL -> " + sql.toString());
        log.info("propName -> " + propName);
        log.info("propValues -> " + JSON.toJSONString(propValues));
        return sql.toString();
    }


    /**
     * 条件统计数量
     *
     * @param context 上下文
     * @param entity  非空, 查询对象entity
     * @return SQL
     */
    public String countAll(ProviderContext context, @Param("entity") T entity) throws Exception {
        this.init(context);
        SQL sql = new SQL();
        sql.SELECT(SqlConsts.EX_SQL_CUSTOM_COUNT);
        sql.FROM(this.tableName);
        List<String> nonNullList = getNonNullList(entity, beanNames, tableGetNameMap, beanTableMap);
        for (String beanName : nonNullList) {
            sql.WHERE(this.beanTableMap.get(beanName) + "=#{entity." + beanName + "}");
        }
        sql.WHERE(SqlConsts.EX_SQL_EFFECTIVE_DATA);
        log.info("SQL -> " + sql.toString());
        log.info("entity -> " + JSON.toJSONString(entity));
        return sql.toString();
    }

    /**
     * 获取非空属性合集
     *
     * @param assetObj        被校验对象
     * @param assetObjProps   被校验对象所有属性名称
     * @param tableGetNameMap 表列名与对象GET属性方法名称的映射关系
     * @param beanTableMap    对象属性名称与表列名的映射关系
     * @return List<String>
     */
    private List<String> getNonNullList(Object assetObj, List<String> assetObjProps, Map<String, String> tableGetNameMap,
                                        Map<String, String> beanTableMap) {
        List<String> result = new ArrayList<>(GlobalConsts.DEFAULT_COLLECTIONS_SIZE);
        //assetObj不为空，即有查询条件的分页
        if (assetObj != null) {
            // 类属性值
            Object beanPropValue;
            // 遍历类属性
            for (String assetObjProp : assetObjProps) {
                // 1、通过beanName获取表字段名称
                // 2、通过表字段名称获取get方法名称
                // 3、通过反射获取属性值
                beanPropValue = ReflectUtil.invoke(assetObj, tableGetNameMap.get(beanTableMap.get(assetObjProp)));
                // 如果属性值不为null
                if (beanPropValue != null) {
                    result.add(assetObjProp);
                }
            }
        }
        return result;
    }
}
