package com.bdfint.backend.modules.sys.service.impl;

import com.bdfint.backend.framework.common.BaseIntServiceImpl;
import com.bdfint.backend.framework.common.Global;
import com.bdfint.backend.framework.exception.CommonException;
import com.bdfint.backend.framework.util.Collections3;
import com.bdfint.backend.framework.util.FilesUtils;
import com.bdfint.backend.modules.sys.bean.CrmQuestionAnswer;
import com.bdfint.backend.modules.sys.bean.CrmQuestionAnswerImg;
import com.bdfint.backend.modules.sys.mapper.CrmQuestionAnswerImgMapper;
import com.bdfint.backend.modules.sys.mapper.CrmQuestionAnswerMapper;
import com.bdfint.backend.modules.sys.service.CrmQuestionAnswerService;
import com.bdfint.backend.modules.sys.utils.DictUtils;
import com.bdfint.backend.modules.sys.utils.UserUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by lsl on 2018/1/11.
 */
@Service
public class CrmQuestionAnswerServiceImpl extends BaseIntServiceImpl<CrmQuestionAnswer> implements CrmQuestionAnswerService {

    @Autowired
    private CrmQuestionAnswerMapper crmQuestionAnswerMapper;
    @Autowired
    private CrmQuestionAnswerImgMapper crmQuestionAnswerImgMapper;

    /**
     * 添加一条记录（若是有上传图片批量存储图片信息）
     * @param request
     * @param crmQuestionAnswer
     * @return 成功返回true；否则抛出异常
     * @throws Exception
     */
    @Override
    @Transactional
    public Boolean addOne(HttpServletRequest request, CrmQuestionAnswer crmQuestionAnswer) throws Exception {
        crmQuestionAnswer.setStartTime(new Date());
        crmQuestionAnswer.setStatus("1");
        crmQuestionAnswer.setType(crmQuestionAnswer.getType());
        crmQuestionAnswer.setUserId(UserUtils.getUserId());
        boolean b = crmQuestionAnswerMapper.addOne(crmQuestionAnswer) > 0;
        if(b){
            String folder = "WTFK";
            if(request instanceof MultipartHttpServletRequest) {
                List<String> list = FilesUtils.fileUpload(request, folder);
                if (!Collections3.isEmpty(list)) {
                    ArrayList<CrmQuestionAnswerImg> imgs = new ArrayList<>();
                    for (String img : list) {
                        CrmQuestionAnswerImg crmQuestionAnswerImg = new CrmQuestionAnswerImg();
                        crmQuestionAnswerImg.setParentId(crmQuestionAnswer.getId());
                        crmQuestionAnswerImg.setImg(img);
                        // TODO: 2018/1/11  ----缩略图  待完成
                        //crmQuestionAnswerImg.setZipImg();
                        imgs.add(crmQuestionAnswerImg);
                    }
                    crmQuestionAnswerImgMapper.insertBatch(imgs);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 管理员回答用户问题（系统自动修改status、endTime信息）
     * @param crmQuestionAnswer
     * @throws Exception 失败抛出异常
     */
    @Override
    @Transactional
    public void feedBack(CrmQuestionAnswer crmQuestionAnswer) throws Exception {
        if(crmQuestionAnswer.getId() == 0){
            throw new CommonException("问题id不能为空！");
        }
        crmQuestionAnswer.setUserBy(UserUtils.getUserId());
        crmQuestionAnswer.setStatus("0");
        crmQuestionAnswer.setEndTime(new Date());
        update(crmQuestionAnswer);
    }

    /**
     * 批量删除（将“delFlag”字段置为“1”）
     * @param id
     * @return 成功返回true；否则抛出异常
     */
    @Override
    @Transactional
    public Boolean delById(Integer... id) throws Exception {
        return id != null && id.length != 0 && crmQuestionAnswerMapper.delBatch(id) == id.length;
    }

    /**
     * 根据条件进行分页查询
     * @param crmQuestionAnswer
     * @return 成功返回PageInfo；否则抛出异常
     * @throws Exception
     */
//    @Override
//    public PageInfo<CrmQuestionAnswer> getPage(CrmQuestionAnswer crmQuestionAnswer) throws Exception {
//        int pageSize = crmQuestionAnswer.getPageSize() == 0 ? 30 : crmQuestionAnswer.getPageSize();
//        if (pageSize != -1) {
//            PageHelper.startPage(crmQuestionAnswer.getPageNum(), pageSize);
//        }
//        crmQuestionAnswer.setUserId(UserUtils.getUserId());
//        List<CrmQuestionAnswer> byPage = crmQuestionAnswerMapper.findListByPage(crmQuestionAnswer);
//        PageInfo<CrmQuestionAnswer> page = new PageInfo<>(byPage);
//        page.setPageNum(crmQuestionAnswer.getPageNum());
//        page.setPageSize(pageSize);
//        return page;
//    }

    @Override
    public List<CrmQuestionAnswerImg> findImgsByQaId(String id){
        Example example = new Example(CrmQuestionAnswerImg.class);
        Example.Criteria or = example.or();
        or.andEqualTo("parentId",id);
        return crmQuestionAnswerImgMapper.selectByExample(example);
    }


}
