package com.bdfint.backend.modules.sys.mapper;

import com.bdfint.backend.framework.common.CommonMapper;
import com.bdfint.backend.modules.sys.bean.CrmAddress;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CrmAddressMapper extends CommonMapper<CrmAddress>{

    public int setNormalAddress(CrmAddress crmAddress);

    public int setDefaultAddress(CrmAddress crmAddress);

    public int delBatch(Integer ... id);
}