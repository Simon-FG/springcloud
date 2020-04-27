package com.doubleskyline.manage.modules.bd.condition;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class SmsCondition {

    /**
     * 发送方北斗卡
     */
    @ApiModelProperty(value = "发送方北斗卡的ID")
    private String sendBdId;

    /**
     * 接收方北斗卡
     */
    @ApiModelProperty(value = "接收方的北斗卡ID")
    private String destBdId;

    /**
     * 网络通讯方式
     */
    @ApiModelProperty(value = "网络通讯方式")
    private Integer networkType;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    //离线间隔时间
    @ApiModelProperty(value = "离线间隔时间")
    private int offLineTime;

}
