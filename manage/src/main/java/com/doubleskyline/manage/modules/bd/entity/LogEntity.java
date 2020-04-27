package com.doubleskyline.manage.modules.bd.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 日志
 *
 * @author SIMON
 * @date 2020-03-21 11:38:26
 */
@ApiModel(description="日志表")
@TableName("log")
@Data
//@JsonInclude(value = JsonInclude.Include.NON_EMPTY) //如果想要序列化，请打开这个参数，并在全局配置中设置
@JSONType(ignores = {"id"})
public class LogEntity {


	/**
	 * 主键
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(value = "主键")
	@TableField("id")
	private Integer id;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(name = "ST")
    private Date createTime;

    /**
     * 北斗卡类型
     */
    @ApiModelProperty(value = "北斗卡类型")
    @TableField("request_url")
    private String requestUrl;

}
