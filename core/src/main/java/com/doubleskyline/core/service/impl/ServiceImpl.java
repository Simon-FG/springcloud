package com.doubleskyline.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doubleskyline.core.mapper.BaseMapper;
import com.doubleskyline.core.model.PageParam;
import com.doubleskyline.core.model.PageResult;
import com.doubleskyline.core.service.IService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 基于mybatis-plus扩展的服务基础接口实现
 * @author zzy
 * @date 2019-06-13
 */
public class ServiceImpl<M extends BaseMapper<T>, T> extends com.baomidou.mybatisplus.extension.service.impl.ServiceImpl<M,T> implements IService<T> {

    /**
     * 根据条件自动构建单个对象查询方法
     * @param entity
     * @return
     */
    @Override
    public T getOne(T entity) {
        return this.getOne(Wrappers.query(entity));
    }

    /**
     * 根据条件构建分页查询
     * @param entity
     * @return
     */
    @Override
    public PageResult<T> page(PageParam pageParam, T entity) {
        QueryWrapper<T> qw = this.buildWrapper(entity);
        if(null != pageParam){
            if(ArrayUtils.isNotEmpty(pageParam.getSelects())){
                qw.select(pageParam.getSelects());
            }
            qw.groupBy(ArrayUtils.isNotEmpty(pageParam.getGroupBy()), pageParam.getGroupBy());
        }
        IPage<T> ipage = this.page(this.buildPage(pageParam), qw);
        return new PageResult(ipage);
    }

    /**
     * 根据条件构建列表查询
     * @param entity
     * @return
     */
    @Override
    public List<T> list(T entity){
        return this.list(this.buildWrapper(entity));
    }

    /**
     * 删除全部
     * @return
     */
    @Override
    public int deleteAll() {
        return baseMapper.deleteAll();
    }

    @Override
    public QueryWrapper<T> buildWrapper(T entity){
        return null == entity ? Wrappers.emptyWrapper() : Wrappers.query(entity);
    }

    @Override
    public Page<T> buildPage(PageParam pageParam){
        if(null == pageParam){
            return null;
        }

        Page<T> page = new Page<>(pageParam.getPageNum(), pageParam.getPageSize());
        if (StringUtils.isNotEmpty(pageParam.getOrderBy()) && StringUtils.isNotEmpty(pageParam.getOrderType())) {
            if ("desc".equalsIgnoreCase(pageParam.getOrderType())) {
                page.setDesc(pageParam.getOrderBy());
            } else {
                page.setAsc(pageParam.getOrderBy());
            }
        }
        return page;
    }
}
