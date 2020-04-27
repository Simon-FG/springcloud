package com.bdfint.backend.framework.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 被继承的Mapper，一般业务Mapper继承它
 * 注意，该接口不能被扫描到，否则会出错
 */

public interface CommonMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
