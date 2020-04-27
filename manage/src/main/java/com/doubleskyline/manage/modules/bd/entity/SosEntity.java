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
 * 北斗应急求救
 *
 * @author ZZY
 * @date 2020-02-21 11:38:26
 */
@ApiModel(description="北斗应急求救")
@TableName("bd_sos")
@Data
//@JsonInclude(value = JsonInclude.Include.NON_EMPTY) //如果想要序列化，请打开这个参数，并在全局配置中设置
@JSONType(ignores = {"id", "destDbId", "delFlag"})
public class SosEntity {

	@ApiModelProperty(value = "接收指挥机消息类型 0：用户上报消息（sms->） 1：接收位置消息(新增->location) 2：上报应急消息(sos->) 3：接收通知消息状态（修改->sms） 4：接收指挥机消息（新增->sms）")
	@JSONField(name = "MT")
	@TableField(exist = false)
	private Integer mt = 2;

	/**
	 * 主键
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(value = "主键")
	@TableField("id")
	private Integer id;

	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id")
	@TableField("user_id")
	@JSONField(name = "UID")
	private Integer userId;

	/**
	 * 报警类型（预留字段）默认1
	 */
	@ApiModelProperty(value = "报警类型（预留字段）默认1")
	@TableField("sos_type")
	@JSONField(name = "AT")
	private String sosType;

	/**
	 * 报警信息
	 */
	@ApiModelProperty(value = "报警信息")
	@TableField("msg")
	@JSONField(name = "MC")
	private String msg;

	/**
	 * 经度
	 */
	@ApiModelProperty(value = "经度")
	@TableField("longitude")
	@JSONField(name = "LO")
	private Double longitude;

	/**
	 * 纬度
	 */
	@ApiModelProperty(value = "纬度")
	@TableField("latitude")
	@JSONField(name = "LA")
	private Double latitude;

	/**
	 * 转发的目标北斗ID
	 */
	@ApiModelProperty(value = "转发的目标北斗ID")
	@TableField("dest_db_id")
	private String destDbId;

	/**
	 * 转发的目标手机号
	 */
	@ApiModelProperty(value = "转发的目标手机号")
	@TableField("dest_phone_id")
	@JSONField(name = "DP")
	private String destPhoneId;

	/**
	 * 报警信息发送时间
	 */
	@ApiModelProperty(value = "报警信息发送时间")
	@TableField("time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(name = "ST")
	private Date time;

	/**
	 * 删除状态（0：正常，1：删除）
	 */
	@ApiModelProperty(value = "删除状态（0：正常，1：删除）")
	@TableField("del_flag")
	private String delFlag;

    /**
     * 消息是否发送成功
     */
    @ApiModelProperty(value = "消息是否发送成功")
    @TableField("status")
    private String status;

}
