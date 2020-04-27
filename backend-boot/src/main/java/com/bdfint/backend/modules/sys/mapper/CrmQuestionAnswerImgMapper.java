package com.bdfint.backend.modules.sys.mapper;

import com.bdfint.backend.framework.common.CommonMapper;
import com.bdfint.backend.modules.sys.bean.CrmQuestionAnswer;
import com.bdfint.backend.modules.sys.bean.CrmQuestionAnswerImg;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CrmQuestionAnswerImgMapper extends CommonMapper<CrmQuestionAnswerImg> {

    public Boolean insertBatch(List<CrmQuestionAnswerImg> list);

}