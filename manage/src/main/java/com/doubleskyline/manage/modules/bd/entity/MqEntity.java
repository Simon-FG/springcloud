package com.doubleskyline.manage.modules.bd.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 接收指挥机消息
 *
 * @author ZZY
 * @date 2020-02-21 11:38:26
 */
@Data
public class MqEntity {

	/**
	 * 接收指挥机消息类型
	 * 0：用户上报消息（sms->）
	 * 1：接收位置消息(新增->location)
	 * 2：上报应急消息(sos->)
	 * 3：接收通知消息状态（修改->sms）
	 * 4：接收指挥机消息（新增->sms）
	 */
	@JSONField(name = "MT")
	private Integer mt;

    /**
     * 消息反馈类型
     *
     */
    @JSONField(name = "BT")
    private Integer bt;

	/**
	 * 主键
	 */
	@JSONField(name = "ID")
	private Integer id;

	/**
	 * 0:发送中，1:成功，2:失败
	 */
	@JSONField(name = "FB")
	private String status;

	/**
	 * 北斗卡号
	 */
	@JSONField(name = "SC")
	private String cardId;

	/**
	 * 终端ID
	 */
	@JSONField(name = "HX")
	private String terminalId;

	/**
	 * 经度
	 */
	@JSONField(name = "LO")
	private Double lon;

	/**
	 * 纬度
	 */
	@JSONField(name = "LA")
	private Double lat;

	/**
	 * 方位角
	 */
	@JSONField(name = "CO")
	private Double angle;

	/**
	 * 定位时间
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(name = "TE")
	private Date locationTime;

	/**
	 * 通讯网络类型 0：未知 1：北斗 2：4G
	 */
	@JSONField(name = "FT")
	private Integer networkType;

	/**
	 * 速度
	 */
	@JSONField(name = "SP")
	private Double speed;

	/**
	 * 高度
	 */
	@JSONField(name = "HE")
	private Double height;

	/**
	 * 收到消息的时间
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(name = "RT")
	private Date receiveTime;

	/**
	 * 终端注册号
	 */
	@JSONField(name = "RE")
	private String terminalReg;



//	/**
//	 * 发送方北斗卡
//	 */
//	@JSONField(name = "SC")
//	private String sendCardId;

	/**
	 * 接收方北斗卡
	 */
	@JSONField(name = "DC")
	private String toCardId;

	/**
	 * 信息内容
	 */
	@JSONField(name = "MC")
	private String content;

	/**
	 * 入库时间
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(name = "ST")
	private Date createTime;


}
