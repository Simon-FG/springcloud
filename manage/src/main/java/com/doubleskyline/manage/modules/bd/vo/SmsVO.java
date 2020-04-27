package com.doubleskyline.manage.modules.bd.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 数据管理短报文统计
 * @auther SIMON
 * @date 2020/4/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsVO {
    private String createTime;
    private Integer count;
    private Integer type;
    private String Name;
}
