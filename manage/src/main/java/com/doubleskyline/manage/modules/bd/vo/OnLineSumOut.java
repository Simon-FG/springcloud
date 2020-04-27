package com.doubleskyline.manage.modules.bd.vo;

import com.doubleskyline.manage.util.excel.ExcelField;
import lombok.Data;

/**
 * 位置统计导出字段
 */
@Data
public class OnLineSumOut {
    @ExcelField(title = "北斗卡号", align=2, sort=1)
    private String cardId;
    @ExcelField(title = "在线时长(分钟)", align=2, sort=2)
    private String onLineTime;
    @ExcelField(title = "上报成功率", align=2, sort=3)
    private String successRate;
    @ExcelField(title = "北斗上报时长（秒）", align=2, sort=4)
    private String bdTime;
//    @ExcelField(title = "4G上报时长")
    private String g4Time;
    @ExcelField(title = "北斗上报数量", align=2, sort=5)
    private String bdNum;
//    @ExcelField(title = "4G上报数量")
//    private String g4Num;

}
