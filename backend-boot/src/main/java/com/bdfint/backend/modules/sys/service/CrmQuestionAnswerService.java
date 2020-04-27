package com.bdfint.backend.modules.sys.service;

import com.bdfint.backend.framework.common.BaseIntService;
import com.bdfint.backend.modules.sys.bean.CrmQuestionAnswer;
import com.bdfint.backend.modules.sys.bean.CrmQuestionAnswerImg;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by lsl on 2018/1/11.
 */
public interface CrmQuestionAnswerService extends BaseIntService<CrmQuestionAnswer>{

    public Boolean addOne(HttpServletRequest request, CrmQuestionAnswer crmQuestionAnswer) throws Exception;

    public void feedBack(CrmQuestionAnswer crmQuestionAnswer) throws Exception;

    public Boolean delById(Integer ... id) throws Exception;

//    public PageInfo<CrmQuestionAnswer> getPage(CrmQuestionAnswer crmQuestionAnswer) throws Exception;

    public List<CrmQuestionAnswerImg> findImgsByQaId(String id);
}
