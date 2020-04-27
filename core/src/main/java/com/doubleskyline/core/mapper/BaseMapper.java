package com.doubleskyline.core.mapper;

/**
 * 自定义基础 Mapper
 * @author zzy
 */
public interface BaseMapper<T> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T>
//        ,com.zlyx.easy.database.defaults.BaseDao<T>
{
    /**
     * 删除全部
     * @return
     */
    int deleteAll();

}
