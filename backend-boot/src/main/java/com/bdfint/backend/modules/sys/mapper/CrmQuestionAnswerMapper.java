package com.bdfint.backend.modules.sys.mapper;

import com.bdfint.backend.framework.common.CommonMapper;
import com.bdfint.backend.modules.sys.bean.CrmQuestionAnswer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CrmQuestionAnswerMapper extends CommonMapper<CrmQuestionAnswer>{

    public int delBatch(Integer ... id);

    public List<CrmQuestionAnswer> findListByPage(CrmQuestionAnswer crmQuestionAnswer);

    public int addOne(CrmQuestionAnswer crmQuestionAnswer);
}