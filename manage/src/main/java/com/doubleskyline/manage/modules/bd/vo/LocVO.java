package com.doubleskyline.manage.modules.bd.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 数据管理位置统计
 * @auther SIMON
 * @date 2020/4/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocVO {
    private String createTime;
    private int count;
    private int type;
    private String name;
}
