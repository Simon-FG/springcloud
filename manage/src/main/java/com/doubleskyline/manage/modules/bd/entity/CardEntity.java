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
 * 北斗卡表
 *
 * @author SIMON
 * @date 2020-04-07 11:38:26
 */
@ApiModel(description="北斗卡表")
@TableName("bd_card")
@Data
//@JsonInclude(value = JsonInclude.Include.NON_EMPTY) //如果想要序列化，请打开这个参数，并在全局配置中设置
@JSONType(ignores = {"id", "delFlag"})
public class CardEntity {

//	@ApiModelProperty(value = "接收指挥机消息类型 0：用户上报消息（sms->） 1：接收位置消息(新增->location) 2：上报应急消息(sos->) 3：接收通知消息状态（修改->sms） 4：接收指挥机消息（新增->sms）")
//	@JSONField(name = "MT")
//	@TableField(exist = false)
//	private Integer mt = 2;

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
//    @JSONField(name = "")
    private String cardId;

    /**
     * 北斗卡类型
     */
    @ApiModelProperty(value = "北斗卡类型")
    @TableField("card_type")
//    @JSONField(name = "")
    private String cardType;

//    /**
//     * 定位方式
//     */
//    @ApiModelProperty(value = "定位方式")
//    @TableField("location_type")
////    @JSONField(name = "")
//    private String locationType;

//    /**
//     * 所属智指挥卡id
//     */
//    @ApiModelProperty(value = "所属智指挥卡id")
//    @TableField("parent_card_id")
////    @JSONField(name = "")
//    private String parentCardId;

//    /**
//     * 通拨号
//     */
//    @ApiModelProperty(value = "通拨号")
//    @TableField("broadcast_code")
////    @JSONField(name = "")
//    private String broadcastCode;

    /**
     * 北斗卡状态(1、正常、2已挂失、3停用)
     */
    @ApiModelProperty(value = "北斗卡状态(1、正常、2已挂失、3停用)")
    @TableField("status")
//    @JSONField(name = "")
    private String status;

//	/**
//	 * 用户id
//	 */
//	@ApiModelProperty(value = "用户id")
//	@TableField("user_id")
//	@JSONField(name = "UID")
//	private Integer userId;

	/**
	 * 删除状态（0：正常，1：删除）
	 */
	@ApiModelProperty(value = "删除状态（0：正常，1：删除）")
	@TableField("del_flag")
	private String delFlag;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

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
     * 高度
     */
    @ApiModelProperty(value = "高度")
    @TableField("height")
    @JSONField(name = "HE")
    private Double height;
}
