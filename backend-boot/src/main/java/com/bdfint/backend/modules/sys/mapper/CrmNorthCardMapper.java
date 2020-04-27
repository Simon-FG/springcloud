package com.bdfint.backend.modules.sys.mapper;

import com.bdfint.backend.framework.common.CommonMapper;
import com.bdfint.backend.modules.sys.bean.CrmNorthCard;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CrmNorthCardMapper extends CommonMapper<CrmNorthCard> {
	
	boolean updateByCardIdNorthCard(CrmNorthCard record);
	
	List<CrmNorthCard> queryNorthCard(CrmNorthCard record);

    /**
     * 查询北斗卡及终端
     * @param record
     * @return
     */
	List<CrmNorthCard> queryNorthCardAndTerminal(CrmNorthCard record);
}