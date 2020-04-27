package com.bdfint.backend.modules.gis.bean;

import com.bdfint.backend.framework.common.BasePgEntity;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "gis_enroute_manager")
public class GisEnrouteManager extends BasePgEntity<GisEnrouteManager>{
    @Id
    private Integer id;

    private String routeid;

    private Object geom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRouteid() {
        return routeid;
    }

    public void setRouteid(String routeid) {
        this.routeid = routeid == null ? null : routeid.trim();
    }

    public Object getGeom() {
        return geom;
    }

    public void setGeom(Object geom) {
        this.geom = geom;
    }
}