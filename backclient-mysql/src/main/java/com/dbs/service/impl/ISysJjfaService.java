package com.dbs.service.impl;

import com.dbs.model.SysJjfa;
import com.dbs.model.SysJjfaSon;

import java.util.List;

/**
 * Created by lsl on 2017/12/22.
 */
public interface ISysJjfaService {

    public List<SysJjfa> getOneWithSonByMenuId(String menuId);

    public List<SysJjfa> findListInHome();

    public List<SysJjfaSon> findSonListByParentId(Integer parentId);



}
