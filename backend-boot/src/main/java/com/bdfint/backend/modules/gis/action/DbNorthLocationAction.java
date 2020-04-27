package com.bdfint.backend.modules.gis.action;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.bdfint.backend.framework.util.DateUtils;
import com.bdfint.backend.framework.util.excel.ExportExcel;
import com.bdfint.backend.modules.gis.bean.OnLineSumOut;
import com.bdfint.backend.modules.sys.bean.User;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.bdfint.backend.framework.common.BaseAction;
import com.bdfint.backend.framework.util.ListToArrayUtils;
import com.bdfint.backend.modules.gis.bean.DbNorthLocation;
import com.bdfint.backend.modules.gis.bean.HistoricalTrack;
import com.bdfint.backend.modules.gis.service.DbNorthLocationService;
import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;

/**
 * <p>
 * Title: DbNorthLocationAction
 * </p>
 * <p>
 * Description: 飞行器历史航迹控制层
 * </p>
 * 
 * @author wangchao
 * @date 2018年3月22日
 *
 * --------接口-------
 *  根据条件分页查询飞行器航迹(HX、startTime、endTime)
 *  admin/northLocation/findListByPage
 *  在线时长、位置上报成功率统计（HX、startTime、endTime、offLineTime）
 *  admin/northLocation/getOnLineTimeAndSuccessRate
 *  普通用户在线时长统计数据批量导出（startTime、endTime、offLineTime）
 *  admin/northLocation/successRateBatchOut
 */
@RestController
@RequestMapping("{adminPath}/northLocation")
public class DbNorthLocationAction extends BaseAction {

	@Autowired
	private DbNorthLocationService dbNorthLocationService;

	/**
	 * <p>
	 * Title: findListByPage
	 * </p>
	 * <p>
	 * Description: 根据条件分页查询飞行器航迹
	 * </p>
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findListByPage", method = RequestMethod.GET)
	public PageInfo<DbNorthLocation> findListByPage(DbNorthLocation record) throws Exception {
		return dbNorthLocationService.findListByPage(record);
	}


	@RequestMapping(value = "/getListByPage")
	public PageInfo<DbNorthLocation> getListByPage(DbNorthLocation record) throws Exception {
		return dbNorthLocationService.getList1(record);
	}

    /**
     * 在线时长、位置上报成功率统计
     * @param location （HX、FT、startTime、endTime）
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getOnLineTimeAndSuccessRate")
	public Map getOnLineTimeAndSuccessRate(DbNorthLocation location) throws Exception {
        return dbNorthLocationService.getOnLineTimeAndSuccessRate(location);
    }

    /**
     * 普通用户名下北斗卡在线时长统计数据批量导出
     * @param location （startTime、endTime、offLineTime）
     * @param response
     * @throws Exception
     */
    @RequestMapping("/successRateBatchOut")
	public void successRateBatchOut(DbNorthLocation location ,HttpServletResponse response) throws Exception {
        String fileName = "北斗在线时长统计数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
        List<OnLineSumOut> list = dbNorthLocationService.successRateBatchOut(location);
        new ExportExcel("北斗在线时长统计数据", User.class).setDataList(list).write(response, fileName).dispose();
	}

	/**
	 * <p>
	 * Title: queryByParamNorthLocation
	 * </p>
	 * <p>
	 * Description: 根据条件查询飞行器航迹数据
	 * </p>
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryByParamNorthLocation", method = RequestMethod.GET)
	public List<DbNorthLocation> queryByParamNorthLocation(DbNorthLocation record) throws Exception {
		return dbNorthLocationService.queryByParamNorthLocation(record);
	}

	/**
	 * <p>
	 * Title: exportWithResponse
	 * </p>
	 * <p>
	 * Description: 根据条件查询导出飞行器历史航迹数据
	 * </p>
	 * 
	 * @param record
	 * @throws Exception
	 */
	@RequestMapping(value = "/exportWithResponse", method = RequestMethod.GET)
	public void exportWithResponse(DbNorthLocation record, HttpServletResponse response) throws Exception {
		List<DbNorthLocation> list = dbNorthLocationService.exportWithResponse(record);
		List<HistoricalTrack> tracklist = new ArrayList<HistoricalTrack>();
		for (int i = 0; i < list.size(); i++) {
			String str = list.get(i).getData().toString();
			Map mapTypes = JSON.parseObject(str);
			HistoricalTrack track = new HistoricalTrack();
			if (mapTypes.get("CO") != null) {
				track.setCO(mapTypes.get("CO").toString());
			} else {
				track.setCO("");
			}
			if (mapTypes.get("LO") != null) {
				track.setLO(mapTypes.get("LO").toString());
			} else {
				track.setLO("");
			}
			if (mapTypes.get("LA") != null) {
				track.setLA(mapTypes.get("LA").toString());
			} else {
				track.setLA("");
			}
			if (mapTypes.get("HE") != null) {
				track.setHE(mapTypes.get("HE").toString());
			} else {
				track.setHE("");
			}
			if (mapTypes.get("SP") != null) {
				track.setSP(mapTypes.get("SP").toString());
			} else {
				track.setSP("");
			}
			if (mapTypes.get("FT") != null) {
				if (mapTypes.get("FT").toString().equals("0")) {
					track.setFT("不明");
				}
				if (mapTypes.get("FT").toString().equals("1")) {
					track.setFT("北斗");
				}
				if (mapTypes.get("FT").toString().equals("2")) {
					track.setFT("GPRS");
				}
				if (mapTypes.get("FT").toString().equals("3")) {
					track.setFT("ADSB");
				}
			} else {
				track.setFT("");
			}
			if (mapTypes.get("TE") != null) {
				track.setTE(mapTypes.get("TE").toString());
			} else {
				track.setTE("");
			}
			if (mapTypes.get("RT") != null) {
				track.setRT(mapTypes.get("RT").toString());
			} else {
				track.setRT("");
			}
			if (mapTypes.get("HX") != null) {
				track.setHX(mapTypes.get("HX").toString());
			} else {
				track.setHX("");
			}
			tracklist.add(track);
		}
		String[][] dataList = ListToArrayUtils.listToArrayWay(tracklist);
		String sheetName = "北斗卡号" + record.getHX() + "历史轨迹";
		String titleName = "北斗卡号" + record.getHX() + "历史轨迹("+record.getStartTime()+"至"+record.getEndTime()+")";
		String fileName = "北斗卡号" + record.getHX() + "历史轨迹";
		int columnNumber = 9;
		int[] columnWidth = { 10, 20, 20, 20, 20, 20, 20, 30, 30 };
		String[] columnName = { "北斗卡号", "经度", "纬度", "高度", "航向", "速度", "类型", "位置报时间", "接收时间" };

		if (columnNumber == columnWidth.length && columnWidth.length == columnName.length) {
			// 第一步，创建一个webbook，对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet sheet = wb.createSheet(sheetName);
			// sheet.setDefaultColumnWidth(15); //统一设置列宽
			for (int i = 0; i < columnNumber; i++) {
				for (int j = 0; j <= i; j++) {
					if (i == j) {
						sheet.setColumnWidth(i, columnWidth[j] * 256); // 单独设置每列的宽
					}
				}
			}
			// 创建第0行 也就是标题
			HSSFRow row1 = sheet.createRow((int) 0);
			row1.setHeightInPoints(50);// 设备标题的高度
			// 第三步创建标题的单元格样式style2以及字体样式headerFont1
			HSSFCellStyle style2 = wb.createCellStyle();
			style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			style2.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
			style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			HSSFFont headerFont1 = (HSSFFont) wb.createFont(); // 创建字体样式
			headerFont1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗
			headerFont1.setFontName("黑体"); // 设置字体类型
			headerFont1.setFontHeightInPoints((short) 15); // 设置字体大小
			style2.setFont(headerFont1); // 为标题样式设置字体样式

			HSSFCell cell1 = row1.createCell(0);// 创建标题第一列
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columnNumber - 1)); // 合并列标题
			cell1.setCellValue(titleName); // 设置值标题
			cell1.setCellStyle(style2); // 设置标题样式

			// 创建第1行 也就是表头
			HSSFRow row = sheet.createRow((int) 1);
			row.setHeightInPoints(37);// 设置表头高度

			// 第四步，创建表头单元格样式 以及表头的字体样式
			HSSFCellStyle style = wb.createCellStyle();
			style.setWrapText(true);// 设置自动换行
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 创建一个居中格式

			style.setBottomBorderColor(HSSFColor.BLACK.index);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);

			HSSFFont headerFont = (HSSFFont) wb.createFont(); // 创建字体样式
			headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗
			headerFont.setFontName("黑体"); // 设置字体类型
			headerFont.setFontHeightInPoints((short) 10); // 设置字体大小
			style.setFont(headerFont); // 为标题样式设置字体样式

			// 第四.一步，创建表头的列
			for (int i = 0; i < columnNumber; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellValue(columnName[i]);
				cell.setCellStyle(style);
			}

			// 为数据内容设置特点新单元格样式1 自动换行 上下居中
			HSSFCellStyle zidonghuanhang = wb.createCellStyle();
			zidonghuanhang.setWrapText(true);// 设置自动换行
			zidonghuanhang.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 创建一个居中格式

			// 设置边框
			zidonghuanhang.setBottomBorderColor(HSSFColor.BLACK.index);
			zidonghuanhang.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			zidonghuanhang.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			zidonghuanhang.setBorderRight(HSSFCellStyle.BORDER_THIN);
			zidonghuanhang.setBorderTop(HSSFCellStyle.BORDER_THIN);

			// 为数据内容设置特点新单元格样式2 自动换行 上下居中左右也居中
			HSSFCellStyle zidonghuanhang2 = wb.createCellStyle();
			zidonghuanhang2.setWrapText(true);// 设置自动换行
			zidonghuanhang2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 创建一个上下居中格式
			zidonghuanhang2.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中

			// 设置边框
			zidonghuanhang2.setBottomBorderColor(HSSFColor.BLACK.index);
			zidonghuanhang2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			zidonghuanhang2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			zidonghuanhang2.setBorderRight(HSSFCellStyle.BORDER_THIN);
			zidonghuanhang2.setBorderTop(HSSFCellStyle.BORDER_THIN);
			// 第五步，创建单元格，并设置值
			for (int i = 0; i < dataList.length; i++) {
				row = sheet.createRow((int) i + 2);

				HSSFCell datacell = null;
				for (int j = 0; j < columnNumber; j++) {
					datacell = row.createCell(j);
					datacell.setCellValue(dataList[i][j]);
					datacell.setCellStyle(zidonghuanhang2);
				}
			}

			// 第六步，将文件存到浏览器设置的下载位置
			String filename = fileName + ".xls";	

			// 清空response
			response.reset();
			// 设置response的Header
			response.setHeader("Content-Disposition",
					"attachment;filename=".concat(String.valueOf(URLEncoder.encode(filename, "UTF-8"))));
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");

			OutputStream out = response.getOutputStream();
			try {
				wb.write(out);// 将数据写出去
				String str = "导出" + fileName + "成功！";
				System.out.println(str);
			} catch (Exception e) {
				e.printStackTrace();
				String str1 = "导出" + fileName + "失败！";
				System.out.println(str1);
			} finally {
				out.close();
			}

		} else {
			System.out.println("列数目长度名称三个数组长度要一致");
		}
	}

}
