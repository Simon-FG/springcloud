package com.doubleskyline.core.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @Auther: NPF
 * @Date: 2019/3/20 21:12
 * 文件操作类
 */

@Slf4j
public class FileUtils extends org.apache.commons.io.FileUtils {

    /**
     * 下载文件
     * 通过IOUtils 对接输入输出流，实现文件下载
     *
     * @param file
     * @param response
     */
    public static void downloaFile(String fileName, File file, HttpServletRequest request, HttpServletResponse response) {

        fileName = getFileName(request, fileName);

        // 清空response
        response.reset();
        // 设置response的Header
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        response.addHeader("Content-Length", "" + file.length());
        response.setContentType("application/octet-stream");

        //打开文件输入流 和 servlet输出流
        try(InputStream fis = new BufferedInputStream(new FileInputStream(file))) {
            //通过ioutil 对接输入输出流，实现文件下载
            IOUtils.copy(fis, response.getOutputStream());
        } catch (Exception e) {
            throw new RuntimeException("文件下载失败");
        }
    }

    /**
     * 以springmvc的方式下载文件
     *
     * @param fileName
     * @param file
     * @param request
     */
    public static ResponseEntity<byte[]> downloaFile(String fileName, File file, HttpServletRequest request) throws IOException {
        //防止中文乱码
        fileName = getFileName(request, fileName);
        //文件字节码
        byte[] body = FileUtils.readFileToByteArray(file);
        //设置响应头
        HttpHeaders headers=new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename="+fileName);
        //设置响应吗
        HttpStatus statusCode = HttpStatus.OK;
        return new ResponseEntity<>(body, headers, statusCode);
    }


    /**
     * 根据浏览器参数，判断浏览器类型，重新获取文件名
     *
     * @param request
     * @param fileName
     * @return
     */
    public static String getFileName(HttpServletRequest request, String fileName) {
        //设置文件名，获得浏览器信息并转换为大写
        String agent = request.getHeader("User-Agent").toUpperCase();
        try {
            //IE浏览器和Edge浏览器
            if (agent.indexOf("MSIE") > 0 || (agent.indexOf("GECKO") > 0 && agent.indexOf("RV:11") > 0)) {
                fileName = URLEncoder.encode(fileName, "UTF-8");
            } else {  //其他浏览器
                fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return fileName;
    }



}
