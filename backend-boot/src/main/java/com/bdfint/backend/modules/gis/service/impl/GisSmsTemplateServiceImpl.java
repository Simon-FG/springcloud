package com.bdfint.backend.modules.gis.service.impl;

import com.bdfint.backend.framework.common.BasePgServiceImpl;
import com.bdfint.backend.framework.config.TargetDataSource;
import com.bdfint.backend.framework.util.Collections3;
import com.bdfint.backend.framework.util.StringUtils;
import com.bdfint.backend.modules.gis.bean.GisSmsTemplate;
import com.bdfint.backend.modules.gis.mapper.GisSmsTemplateMapper;
import com.bdfint.backend.modules.gis.service.GisSmsTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * Created by lsl on 2018/4/3.
 */
@Service
public class GisSmsTemplateServiceImpl extends BasePgServiceImpl<GisSmsTemplate> implements GisSmsTemplateService {
    @Autowired
    private GisSmsTemplateMapper gisSmsTemplateMapper;

    /**
     * 根据父id查询模板列表
     * @param pId
     * @return
     */
    @Override
    @TargetDataSource("pg")
    public List<GisSmsTemplate> getTemplateList(int pId){
        Example example = new Example(GisSmsTemplate.class);
        example.or().andEqualTo("pId",pId).andNotEqualTo("delFlag","1");
        return gisSmsTemplateMapper.selectByExample(example);
    }

    @Override
    @TargetDataSource("pg")
    public List<GisSmsTemplate> getTemplateTree(int pId){
        ArrayList<GisSmsTemplate> arrayList = new ArrayList<>();
        List<GisSmsTemplate> list = getTemplateList(pId);
        if(!Collections3.isEmpty(list)){
            arrayList.addAll(list);
            for(GisSmsTemplate template: list){
                Short id = template.getId();
                List<GisSmsTemplate> tree = getTemplateTree(id);
                template.setList(tree);
            }
        }
        return arrayList;
    }

    /**
     * 添加模板
     * @param template
     * @return
     */
    @Override
    @TargetDataSource("pg")
    @Transactional
    public Boolean addTemplate(GisSmsTemplate template){
        String s = template.getpTitle();
        int id = 0;
        if(StringUtils.isNotBlank(s)){
            GisSmsTemplate smsTemplate = new GisSmsTemplate();
            smsTemplate.setTitle(s);
            smsTemplate.setCreateTime(new Date());
            gisSmsTemplateMapper.addTemplate(smsTemplate);
            id = smsTemplate.getId();
        }
        if(StringUtils.isNotBlank(template.getTitle())){
            if(id != 0){
                template.setpId((short) id);
            }
            template.setCreateTime(new Date());
            gisSmsTemplateMapper.insertSelective(template);
        }
        return true;
    }

    /**
     * 根据id假删除模板
     * @param id
     * @return
     */
    @Override
    @TargetDataSource("pg")
    public Boolean delTemplate(String id){
        String[] split = id.split(",");


        ArrayList<Integer> list = new ArrayList<>();
        for(String s: split){
            list.add(Integer.parseInt(s));
        }
        if(split.length > 0){
            Integer[] ints = {};
            Integer[] integers = list.toArray(ints);
            gisSmsTemplateMapper.delBatch(integers);
            return true;
        }
        return false;
    }
}
