package com.bdfint.backend.modules.sys.action;

import com.bdfint.backend.framework.common.BaseAction;
import com.bdfint.backend.framework.util.StringUtils;
import com.bdfint.backend.modules.sys.bean.CrmQuestionAnswer;
import com.bdfint.backend.modules.sys.bean.CrmQuestionAnswerImg;
import com.bdfint.backend.modules.sys.bean.Dict;
import com.bdfint.backend.modules.sys.service.CrmQuestionAnswerService;
import com.bdfint.backend.modules.sys.utils.DictUtils;
import com.bdfint.backend.modules.sys.utils.UserUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lsl on 2018/1/11.
 *
 * --------接口--------
 *      根据id查找CrmQuestionAnswer记录（id）
 *      http://localhost:8082/admin/questionAnswer/getOneById
 *      根据参数查找CrmQuestionAnswer记录列表（userId、type、status、startTime、endTime）..
 *      http://localhost:8082/admin/questionAnswer/findListByVo
 *      根据参数查找CrmQuestionAnswer记录列表（id）
 *      http://localhost:8082/admin/questionAnswer/findImgsByQaId
 *      添加一条CrmQuestionAnswer记录
 *      http://localhost:8082/admin/questionAnswer/addOne
 *      更新新一条CrmQuestionAnswer记录
 *      http://localhost:8082/admin/questionAnswer/editOne
 *      管理员反馈用户问题
 *      http://localhost:8082/admin/questionAnswer/feedBack
 *      根据id删除TcrmProblem记录(Integer ... id)
 *      http://localhost:8082/admin/questionAnswer/delById
 *
 * -----字典----
 *      获取列表
 *      http://localhost:8082/useradmin/questionAnswer/getTypeList
 *      获取列表
 *      http://localhost:8082/useradmin/questionAnswer/getStatusList
 */
@RestController
@RequestMapping("{adminPath}/questionAnswer")
public class CrmQuestionAnswerAction extends BaseAction {

    @Autowired
    private CrmQuestionAnswerService crmQuestionAnswerService;

    /**
     * 根据id查找CrmQuestionAnswer记录
     * @param id
     * @return
     */
    @RequestMapping("/getOneById")
    public CrmQuestionAnswer getOneById(Integer id) throws Exception {
        return crmQuestionAnswerService.get(id);
    }

    /**
     * 根据参数查找CrmQuestionAnswer记录列表
     * @param crmQuestionAnswer （userId、type、status、startAt、endAt）..
     * @return
     */
    @RequestMapping("/findListByVo")
    public PageInfo<CrmQuestionAnswer> findListByVo(CrmQuestionAnswer crmQuestionAnswer) throws Exception {
        Example example = new Example(CrmQuestionAnswer.class);
        Example.Criteria or = example.or();
        if(StringUtils.isNotBlank(crmQuestionAnswer.getUserId())){
            or.andEqualTo("userId", UserUtils.getUserId());
        }
        if(StringUtils.isNotBlank(crmQuestionAnswer.getType())){
            or.andEqualTo("type", crmQuestionAnswer.getType());
        }
        if(StringUtils.isNotBlank(crmQuestionAnswer.getStatus())){
            or.andEqualTo("status", crmQuestionAnswer.getStatus());
        }
        if(StringUtils.isNotBlank(crmQuestionAnswer.getStartAt())){
            or.andGreaterThanOrEqualTo("startTime", crmQuestionAnswer.getStartAt());
        }
        if(StringUtils.isNotBlank(crmQuestionAnswer.getEndAt())){
            or.andLessThanOrEqualTo("endTime", crmQuestionAnswer.getEndAt());
        }
        example.setOrderByClause("start_time desc");
        return crmQuestionAnswerService.getPage(crmQuestionAnswer,example);
    }

    @RequestMapping("/findImgsByQaId")
    public List<CrmQuestionAnswerImg> findImgsByQaId(@RequestParam String id){
        return crmQuestionAnswerService.findImgsByQaId(id);
    }

    /**
     * 添加一条CrmQuestionAnswer记录
     * @param crmQuestionAnswer
     * @return
     */
    @RequestMapping("/addOne")
    public Boolean addOne(HttpServletRequest request, CrmQuestionAnswer crmQuestionAnswer) throws Exception {
        System.out.println(request instanceof MultipartHttpServletRequest);
        return crmQuestionAnswerService.addOne(request,crmQuestionAnswer);
    }

    /**
     * 更新新一条CrmQuestionAnswer记录
     * @param crmQuestionAnswer
     * @return
     */
    @RequestMapping("/editOne")
    public Boolean editOne(CrmQuestionAnswer crmQuestionAnswer) throws Exception {
        try {
            crmQuestionAnswerService.update(crmQuestionAnswer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 管理员反馈用户问题
     * @param crmQuestionAnswer
     * @return
     */
    @RequestMapping("/feedBack")
    public Boolean feedBack(CrmQuestionAnswer crmQuestionAnswer) throws Exception {
        crmQuestionAnswerService.feedBack(crmQuestionAnswer);
        return true;
    }

    /**
     * 根据id删除TcrmProblem记录
     * @param id
     * @return
     */
    @RequestMapping("/delById")
    public Boolean delById(Integer ... id) throws Exception {
        return crmQuestionAnswerService.delById(id);
    }

    @RequestMapping("/getTypeList")
    public List<Dict> getTypeList(){
        return DictUtils.getDictList("question_answer_type");
    }

    @RequestMapping("/getStatusList")
    public List<Dict> getStatusList(){
        return DictUtils.getDictList("question_answer_status");
    }
}
