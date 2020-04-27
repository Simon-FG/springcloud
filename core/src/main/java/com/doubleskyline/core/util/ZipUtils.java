package com.doubleskyline.core.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import java.io.*;

@Slf4j
public class ZipUtils {

    /**
     * 压缩文件列表到某个zip包
     * @param zipFileName zip包文件名
     * @param paths 文件列表路径
     * @throws IOException
     */
    public static void compress(String zipFileName,String... paths) throws IOException {
        compress(new FileOutputStream(zipFileName),paths);
    }


    /**
     * 压缩文件列表到某个zip包
     * @param stream 流
     * @param paths 文件列表路径
     * @throws IOException
     */
    public static void compress(OutputStream stream, String... paths) throws IOException {
        ZipOutputStream zos = new ZipOutputStream(stream);
        zos.setEncoding(System.getProperty("sun.jnu.encoding"));
        for (String path : paths){
            if (StringUtils.equals(path,"") || !new File(path).exists()){
                log.error("附件 {} 不存在", path);
                continue;
            }
            File file = new File(path);
            if (file.exists()){
                if (file.isDirectory()){
                    zipDirectory(zos,file.getPath(),file.getName() + File.separator);
                } else {
                    zipFile(zos,file.getPath(),"");
                }
            }
        }
        zos.close();
    }


    /**
     * 解析多文件夹
     * @param zos zip流
     * @param dirName 目录名称
     * @param basePath
     * @throws IOException
     */
    private static void zipDirectory(ZipOutputStream zos, String dirName, String basePath) throws IOException {
        File dir = new File(dirName);
        if (dir.exists()){
            File files[] = dir.listFiles();
            if (files.length > 0){
                for (File file : files){
                    if (file.isDirectory()){
                        zipDirectory(zos,file.getPath(),file.getName() + File.separator);
                    } else {
                        zipFile(zos,file.getName(),basePath);
                    }
                }
            } else {
                ZipEntry zipEntry = new ZipEntry(basePath);
                zos.putNextEntry(zipEntry);
            }
        }
    }


    private static void zipFile(ZipOutputStream zos,String fileName,String basePath) throws IOException {
        File file = new File(fileName);
        if (file.exists()){
            FileInputStream fis = new FileInputStream(fileName);
            try{
                ZipEntry ze = new ZipEntry(basePath + file.getName());
                zos.putNextEntry(ze);
                IOUtils.copy(fis,zos);
            } finally {
                fis.close();
            }
        }
    }

}
