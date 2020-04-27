package com.bdfint.backend.modules.gis.bean;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.bdfint.backend.framework.common.BasePgEntity;

@Table(name = "gis_airport_manager")
public class GisAirportManager extends BasePgEntity<GisAirportManager>{
	@Id
    private Long gid;

    private Long objectid;

    private String ident;

    private String designator;

    private String ifrcap;

    private String latitude;

    private String longitude;

    private String magvar;

    private String elevation;

    private String recnav;

    private String transalt;

    private String translevel;

    private String pubmilindi;

    private String airporten;

    private String cycledate;

    private String airportcit;

    private String airportnam;

    private String geom;

    @Transient
    private String value;
    
    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public Long getObjectid() {
        return objectid;
    }

    public void setObjectid(Long objectid) {
        this.objectid = objectid;
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident == null ? null : ident.trim();
    }

    public String getDesignator() {
        return designator;
    }

    public void setDesignator(String designator) {
        this.designator = designator == null ? null : designator.trim();
    }

    public String getIfrcap() {
        return ifrcap;
    }

    public void setIfrcap(String ifrcap) {
        this.ifrcap = ifrcap == null ? null : ifrcap.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getMagvar() {
        return magvar;
    }

    public void setMagvar(String magvar) {
        this.magvar = magvar == null ? null : magvar.trim();
    }

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation == null ? null : elevation.trim();
    }

    public String getRecnav() {
        return recnav;
    }

    public void setRecnav(String recnav) {
        this.recnav = recnav == null ? null : recnav.trim();
    }

    public String getTransalt() {
        return transalt;
    }

    public void setTransalt(String transalt) {
        this.transalt = transalt == null ? null : transalt.trim();
    }

    public String getTranslevel() {
        return translevel;
    }

    public void setTranslevel(String translevel) {
        this.translevel = translevel == null ? null : translevel.trim();
    }

    public String getPubmilindi() {
        return pubmilindi;
    }

    public void setPubmilindi(String pubmilindi) {
        this.pubmilindi = pubmilindi == null ? null : pubmilindi.trim();
    }

    public String getAirporten() {
        return airporten;
    }

    public void setAirporten(String airporten) {
        this.airporten = airporten == null ? null : airporten.trim();
    }

    public String getCycledate() {
        return cycledate;
    }

    public void setCycledate(String cycledate) {
        this.cycledate = cycledate == null ? null : cycledate.trim();
    }

    public String getAirportcit() {
        return airportcit;
    }

    public void setAirportcit(String airportcit) {
        this.airportcit = airportcit == null ? null : airportcit.trim();
    }

    public String getAirportnam() {
        return airportnam;
    }

    public void setAirportnam(String airportnam) {
        this.airportnam = airportnam == null ? null : airportnam.trim();
    }

    public String getGeom() {
        return geom;
    }

    public void setGeom(String geom) {
        this.geom = geom == null ? null : geom.trim();
    }

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
    
    
}