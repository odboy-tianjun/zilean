package cn.odboy.zilean.mybatis.page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author: Odboy
 * @version: Jdk 1.8
 * @desc: TODO 分页对象
 * @time: 2019/8/8 10:43
 * @blog: www.odboy.cn
 */
@Getter
@Setter
@ToString
public class Pagination<T> implements Serializable {
    /**
     * 数据集合
     */
    private List<T> rows;
    /**
     * 数据总数
     */
    private Long total;
    /**
     * 页面参数
     */
    private Page page;
}
