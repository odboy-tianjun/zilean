package cn.odboy.zilean.mybatis.constant;

/**
 * @author: Odboy
 * @version: Jdk 1.8
 * @desc: TODO SQL常量
 * @time: 2019/8/8 10:41
 * @blog: www.odboy.cn
 */
public class SqlConsts {
    /**
     * 对象属性：主键名称
     */
    public static final String PRIMARY_KEY_NAME = "id";
    /**
     * 方法名称：获取主键, 具体查看BaseEntity中的属性名称
     */
    public static final String GET_ID = "getId";
    /**
     * 方法名称：设置更新时间, 具体查看BaseEntity中的属性名称
     */
    public static final String SET_UPDATE_TIME = "setUpdateTime";
    /**
     * 错误提示：当Entity类没有标注注解@Table(javax.persistence.Table)时, EntityMapper继承了BaseMapper后报错
     */
    public static final String TX_TABLE_NONE =
            "POJO类中没有@Table注解, 不能使用BaseMapper接口, 如需使用BaseMapper接口, 请加上注解javax.persistence.Table";
    /**
     * 错误提示：当分页对象 Page 为null时的提示
     */
    public static final String TX_PAGE_NULL = "分页对象Page不能为空";
    /**
     * SQL条件表达式：有效数据
     */
    public static final String EX_SQL_EFFECTIVE_DATA = "delete_flag=0";
    /**
     * SQL条件表达式：无效数据
     */
    public static final String EX_SQL_UNEFFECTIVE_DATA = "delete_flag=1";
    /**
     * SQL条件表达式：通用统计
     */
    public static final String EX_SQL_CUSTOM_COUNT = "count(0)";
    /**
     * SQL条件表达式：分页
     */
    public static final String PR_SQL_PAGE = " LIMIT {}, {}";
    /**
     * SQL参数字段：创建时间、主键
     */
    public static final String PR_SQL_ORDER_ID = "create_time desc";
    /**
     * SQL查找方法名称头
     */
    public static final String PH_SQL_FIND = "find";
    /**
     * GET
     */
    public static final String PH_SQL_GET = "get";

}
