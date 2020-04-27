package com.doubleskyline.manage.modules.bd.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 北斗报文实时表
 *
 * @author SIMON
 * @date 2020-04-27 11:38:26
 */
@ApiModel(description="北斗报文实时表")
@TableName("bd_rt_sms")
@Data
//@JsonInclude(value = JsonInclude.Include.NON_EMPTY) //如果想要序列化，请打开这个参数，并在全局配置中设置
@JSONType(ignores = {"type", "sendTime", "userId", "delFlag", "startTime", "endTime"})
public class SmsRtEntity {

	@ApiModelProperty(value = "接收指挥机消息类型 0：用户上报消息（sms->） 1：接收位置消息(新增->location) 2：上报应急消息(sos->) 3：接收通知消息状态（修改->sms） 4：接收指挥机消息（新增->sms）")
	@JSONField(name = "MT")
	@TableField(exist = false)
	private Integer mt = 0;

	/**
	 * 主键
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(value = "主键")
	@TableField("id")
	@JSONField(name = "ID")
	private Integer id;

	/**
	 * 发送方北斗卡
	 */
	@ApiModelProperty(value = "发送方北斗卡")
	@TableField("send_card_id")
	@JSONField(name = "SC")
	private String sendCardId;

	/**
	 * 接收方北斗卡
	 */
	@ApiModelProperty(value = "接收方北斗卡")
	@TableField("to_card_id")
	@JSONField(name = "DC")
	private String toCardId;

	/**
	 * 消息类型，0：接受消息，1：发送消息
	 */
	@ApiModelProperty(value = "消息类型，0：接受消息，1：发送消息")
	@TableField("type")
	private String type;

	/**
	 * 信息内容
	 */
	@ApiModelProperty(value = "信息内容")
	@TableField("content")
	@JSONField(name = "MC")
	private String content;

	/**
	 * 入库时间
	 */
	@ApiModelProperty(value = "入库时间")
	@TableField("create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(name = "ST")
	private Date createTime;

	/**
	 * 0:发送中，1:成功，2:失败
	 */
	@ApiModelProperty(value = "0:发送中，1:成功，2:失败")
	@TableField("status")
	@JSONField(name = "FB")
	private String status;

	/**
	 * MQ返回的发送成功时间
	 */
	@ApiModelProperty(value = "MQ返回的发送成功时间")
	@TableField("send_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date sendTime;

	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id")
	@TableField("user_id")
	private Integer userId;

    /**
     * 通讯类型（0：未知 1：北斗 2：4G）
     */
    @ApiModelProperty(value = "用户id")
    @TableField("network_type")
    private Integer networkType = 1;

	/**
	 * 删除状态（0：正常，1：删除）
	 */
	@ApiModelProperty(value = "删除状态（0：正常，1：删除）")
	@TableField("del_flag")
	private String delFlag;

	/**
	 * 开始时间
	 */
	@ApiModelProperty(value = "开始时间")
	@TableField(exist = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonIgnore
	private Date startTime;

	/**
	 * 结束时间
	 */
	@ApiModelProperty(value = "结束时间")
	@TableField(exist = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonIgnore
	private Date endTime;

}
