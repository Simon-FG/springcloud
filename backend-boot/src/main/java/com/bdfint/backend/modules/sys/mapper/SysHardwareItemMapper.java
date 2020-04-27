package com.bdfint.backend.modules.sys.mapper;

import com.bdfint.backend.framework.common.CommonMapper;
import com.bdfint.backend.modules.sys.bean.SysHardwareItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysHardwareItemMapper extends CommonMapper<SysHardwareItem> {

    @Select("select item_id,item_type,name,byname,`describe`,price,hardware_img,update_time,update_by,menu_id,status from sys_hardware_item where item_id = #{id}")
    SysHardwareItem getOneById(Integer id);
}