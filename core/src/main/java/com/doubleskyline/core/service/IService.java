package com.doubleskyline.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doubleskyline.core.model.PageParam;
import com.doubleskyline.core.model.PageResult;

import java.util.List;

/**
 * 基于mybatis-plus扩展的服务基础接口
 * @author zzy
 * @date 2019-06-13
 */
public interface IService<T> extends com.baomidou.mybatisplus.extension.service.IService<T> {

    /**
     * 根据条件自动构建单个对象查询方法
     * @param entity
     * @return
     */
    T getOne(T entity);

    /**
     * 根据条件自动构建分页查询方法
     * @param entity
     * @return
     */
    PageResult<T> page(PageParam page, T entity);

    /**
     * 根据条件自动构建列表查询方法
     * @param entity
     * @return
     */
    List<T> list(T entity);

    /**
     * 删除全部
     * @return
     */
    int deleteAll();

    /**
     * 构建查询字段和查询条件
     * @param entity
     * @return
     */
    QueryWrapper<T> buildWrapper(T entity);

    /**
     * 构建分页条件
     * @param pageParam
     * @return
     */
    Page<T> buildPage(PageParam pageParam);
}
