package com.bdfint.backend.modules.sys.action;

import com.bdfint.backend.framework.common.BaseAction;
import com.bdfint.backend.modules.sys.bean.Area;
import com.bdfint.backend.modules.sys.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lsl on 2018/1/26.
 *
 * --------------接口--------------
 *      获取省列表
 *      http://localhost:8082/admin/area/getProvinceList
 *      获取市列表(parentId)
 *      http://localhost:8082/admin/area/getCityList
 *      获取县列表(parentId)
 *      http://localhost:8082/admin/area/getCountyList
 *
 */
@RestController
@RequestMapping("{adminPath}/area")
public class AreaAction extends BaseAction {

    @Autowired
    private AreaService areaService;

    @RequestMapping("/getProvinceList")
    public List<Area> getProvinceList(){
        return areaService.findChildList("1");
    }
    @RequestMapping("/getCityList")
    public List<Area> getCityList(@RequestParam String parentId){
        return areaService.findChildList(parentId);
    }
    @RequestMapping("/getCountyList")
    public List<Area> getCountyList(@RequestParam String parentId){
        return areaService.findChildList(parentId);
    }
}
