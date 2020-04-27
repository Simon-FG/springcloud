package com.doubleskyline.manage.modules.bd.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 北斗位置
 *
 * @author ZZY
 * @date 2020-02-21 11:38:26
 */
@ApiModel(description="北斗位置")
@TableName("bd_location")
@Data
//@JsonInclude(value = JsonInclude.Include.NON_EMPTY) //如果想要序列化，请打开这个参数，并在全局配置中设置
@JSONType(ignores = {"id","terminalType", "startTime", "endTime"})
public class LocationEntity {

	@ApiModelProperty(value = "接收指挥机消息类型 0：用户上报消息（sms->） 1：接收位置消息(新增->location) 2：上报应急消息(sos->) 3：接收通知消息状态（修改->sms） 4：接收指挥机消息（新增->sms）")
	@JSONField(name = "MT")
	@TableField(exist = false)
	private Integer mt = 1;
	/**
	 * 主键
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(value = "主键")
	@TableField("id")
	private Integer id;

	/**
	 * 北斗卡号
	 */
	@ApiModelProperty(value = "北斗卡号")
	@TableField("card_id")
	@JSONField(name = "SC")
	private String cardId;

	/**
	 * 终端ID
	 */
	@ApiModelProperty(value = "终端ID")
	@TableField("terminal_id")
	@JSONField(name = "HX")
	private String terminalId;

	/**
	 * 经度
	 */
	@ApiModelProperty(value = "经度")
	@TableField("lon")
	@JSONField(name = "LO")
	private Double lon;

	/**
	 * 纬度
	 */
	@ApiModelProperty(value = "纬度")
	@TableField("lat")
	@JSONField(name = "LA")
	private Double lat;

	/**
	 * 方位角
	 */
	@ApiModelProperty(value = "方位角")
	@TableField("angle")
	@JSONField(name = "CO")
	private Double angle;

	/**
	 * 定位时间
	 */
	@ApiModelProperty(value = "定位时间")
	@TableField("location_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(name = "TE")
	private Date locationTime;

	/**
	 * 通讯网络类型 0：未知 1：北斗 2：4G
	 */
	@ApiModelProperty(value = "通讯网络类型 0：未知 1：北斗 2：4G")
	@TableField("network_type")
	@JSONField(name = "FT")
	private Integer networkType;

	/**
	 * 速度
	 */
	@ApiModelProperty(value = "速度")
	@TableField("speed")
	@JSONField(name = "SP")
	private Double speed;

	/**
	 * 高度
	 */
	@ApiModelProperty(value = "高度")
	@TableField("height")
	@JSONField(name = "HE")
	private Double height;

	/**
	 * 收到消息的时间
	 */
	@ApiModelProperty(value = "收到消息的时间")
	@TableField("receive_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(name = "RT")
	private Date receiveTime;

    /**
     * 终端注册号
     */
    @ApiModelProperty(value = "终端编号")
    @TableField("terminal_num")
    private String terminalNum;

	/**
	 * 终端类型：0:未知 1:车载 2:手持 3:根据需求扩展
	 */
	@ApiModelProperty(value = "终端类型：0:未知 1:车载 2:手持 3:根据需求扩展")
	@TableField("terminal_type")
	private String terminalType;

	/**
	 * 终端注册号
	 */
	@ApiModelProperty(value = "终端注册号")
	@TableField("terminal_reg")
	@JSONField(name = "RE")
	private String terminalReg;

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

    //离线间隔时间
    @ApiModelProperty(value = "离线间隔时间")
    @TableField(exist = false)
    private int offLineTime;

}
