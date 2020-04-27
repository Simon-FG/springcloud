package com.doubleskyline.manage.modules.bd.mapper;

import com.doubleskyline.core.mapper.BaseMapper;
import com.doubleskyline.manage.modules.bd.entity.LogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 日志表
 *
 * @author SIMON
 * @date 2020-03-21 11:38:26
 */
@Mapper
public interface LogMapper extends BaseMapper<LogEntity> {

}
