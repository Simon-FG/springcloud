package com.bdfint.backend.modules.sys.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bdfint.backend.framework.common.Global;
import com.bdfint.backend.framework.exception.CommonException;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class FileUtils {
	private static Logger logger = Logger.getLogger(FileUtils.class);
	
	public static String read_extension = "read";
	public static String video_extension = "video";
	public static String image_extension = "image";
	public static String other_extension = "other";
	
	//abstract文件
	private static Set<String> fileContentSet = new HashSet<String>();
	private static Set<String> readExtensionSet = new HashSet<String>();
	private static Set<String> videoExtensionSet = new HashSet<String>();
	private static Set<String> imageExtensionSet = new HashSet<String>();
	
	//online文件
	private static Set<String> readOnlineExtensionSet = new HashSet<String>();
	private static Set<String> videoOnlineExtensionSet = new HashSet<String>();
	private static Set<String> imageOnlineExtensionSet = new HashSet<String>();
	
	static {
		//上传文件类型——暂时不用
		fileContentSet.add("application/x-shockwave-flash");//swf
		fileContentSet.add("application/octet-stream");//flv
		
		//下载阅读文件类型
		readExtensionSet.add("doc");
		readExtensionSet.add("docx");
		readExtensionSet.add("pdf");
		readExtensionSet.add("ppt");
		//下载视频文件类型
		videoExtensionSet.add("wmv");
		videoExtensionSet.add("avi");
		videoExtensionSet.add("mpg");
		//下载图片文件类型
		imageExtensionSet.add("jpg");
		imageExtensionSet.add("png");
		imageExtensionSet.add("gif");
		imageExtensionSet.add("jpeg");
		imageExtensionSet.add("bmp");
		
		
		//在线阅读文件类型
		readOnlineExtensionSet.add("swf");
		//在线视频文件类型
		videoOnlineExtensionSet.add("flv");
		//在线图片文件类型
		imageOnlineExtensionSet.add("jpg");
		imageOnlineExtensionSet.add("png");
		imageOnlineExtensionSet.add("gif");
		imageOnlineExtensionSet.add("jpeg");
		imageOnlineExtensionSet.add("bmp");
		imageOnlineExtensionSet.add("emf");
	}
	
	//删除目录中的文件：
	public static void deleteFile(String filepath) throws IOException {
		File f = new File(filepath);// 定义文件路径
		if (f.exists() && f.isDirectory()) {// 判断是文件还是目录
			if (f.listFiles().length == 0) {// 若目录下没有文件则直接删除
				//f.delete();
			} else {// 若有则把文件放进数组，并判断是否有下级目录
				File delFile[] = f.listFiles();
				int i = f.listFiles().length;
				for (int j = 0; j < i; j++) {
					if (delFile[j].isDirectory()) {
						deleteFile(delFile[j].getAbsolutePath());// 递归调用del方法并取得子目录路径
						System.out.println(delFile[j].getAbsolutePath());
					}
					delFile[j].delete();// 删除文件
				}
			}
		}
	}
	public static boolean deleteOnlyFile(String fileName) { 
		   File file = new File(fileName); 
		   // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除 
		   if(file.exists() && file.isFile()) { 
		    if(file.delete()) { 
		     System.out.println("删除单个文件" + fileName + "成功！"); 
		       return true; 
		    } else { 
		     System.out.println("删除单个文件" + fileName + "失败！"); 
		       return false; 
		    } 
		   } else { 
		    System.out.println("删除单个文件失败：" + fileName + "不存在！"); 
		      return false; 
		   } 
		}

	/***
	 * 多文件上传方法
	 * @throws UnsupportedEncodingException
	 */
	public static List<String> fileUpload(HttpServletRequest request,char[] fileCharts) throws UnsupportedEncodingException{
		if(!(request instanceof MultipartHttpServletRequest)){
			return null;
		}
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
		if (request.getCharacterEncoding() == null) {
			request.setCharacterEncoding("UTF-8");
		}

		String fileUploadPath = Global.getFileUploadPath();

		ArrayList<String> list = new ArrayList<>();
		for (int i=0; i<files.size(); i++) {
			if (!files.get(i).isEmpty()) {
				try {
					String folder = getFolder(fileCharts[i]);
					String fileExtension = files.get(i).getOriginalFilename().substring(files.get(i).getOriginalFilename().lastIndexOf("."));
					String fileName = FileUtils.getNewFileName(folder + fileExtension, FileUtils.getCurrentYMDHm());
					FileUtils.uploadFile(files.get(i).getBytes(), fileUploadPath + folder, fileName);
					list.add(Global.getFileAccessPath()+folder+ File.separator+ fileName);
				} catch (Exception e) {
					throw new CommonException("上传失败");
				}
			} else {
				throw new CommonException("上传失败");
			}
		}
		return list;
	}

	private static String getFolder(char c){
		switch (c){
			case 'a':
			case 'b':
				return "USER-ID";
			case 'c':
			case 'd':
				return "COMPANY-ID";
		}
		throw new RuntimeException("字符"+c+"没有对应的folder");
	}

	/***
	 * 多文件上传方法
	 * @throws UnsupportedEncodingException 
	 */
	public static List<String> fileUpload(HttpServletRequest request,String folder) throws UnsupportedEncodingException{
		if(!(request instanceof MultipartHttpServletRequest)){
			return null;
		}
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
		if (request.getCharacterEncoding() == null) {  
			request.setCharacterEncoding("UTF-8");
        }
//        String absoluteDir ="D:\\files\\"+folder;//配置文件地址 获取 filePath
//		//创建目录
//		FileUtils.makeDir(absoluteDir);

        String fileUploadPath = Global.getFileUploadPath() + folder;

        ArrayList<String> list = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            if (!file.isEmpty()) {
                try {
                    String fileName = FileUtils.getNewFileName(folder + fileExtension, FileUtils.getCurrentYMDHm());
                    FileUtils.uploadFile(file.getBytes(), fileUploadPath, fileName);
                    list.add(Global.getFileAccessPath()+folder+ File.separator+ fileName);
                } catch (Exception e) {
                    throw new CommonException("上传失败");
                }
            } else {
                throw new CommonException("上传失败");
            }
        }
		return list;
	}
	
	public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception { 
        File targetFile = new File(filePath);  
        try {
        	if(!targetFile.exists()){    
                targetFile.mkdirs();    
            }       
            FileOutputStream out = new FileOutputStream(targetFile+"\\"+fileName);
            out.write(file);
            out.flush();
            out.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        
    }

	
	/**
	 * 文件拷贝
	 * @param sourceFile   原文件完整路径和名称
	 * @param destFile     目标文件完整路径和名称
	 */
	public static void fileCopy(String sourceFile, String destFile) { 
        FileInputStream in = null;   
        FileOutputStream out = null;   
        byte[] buffer = new byte[1024];   
        try {   
            in = new FileInputStream(sourceFile);   
            File dest = new File(destFile);   
            if(!dest.exists()){ 
            	//目标文件对应的目录不存在，创建新的目录   
                int index = new String(destFile).lastIndexOf("/");   
                String path = destFile.substring(0, index);   
                new File(path).mkdirs();   
            }   
            out = new FileOutputStream(destFile);   
            int num =0;   
            while((num = in.read(buffer)) != -1){   
                out.write(buffer, 0, num);   
            }   
            logger.info(sourceFile + " 拷贝到 " + destFile);
        } catch(Exception e){
        	e.printStackTrace();
        	logger.error(sourceFile + " 拷贝到 " + destFile);
        } finally {   
            try {   
                if(in!=null)   
                    in.close();   
                if(out!=null)   
                    out.close();   
            } catch (IOException ex) {  
            	ex.printStackTrace();
            }   
        }   
    }
	
	/**
	 * 文件下载
	 * @param path
	 * @param response
	 * @return
	 */
	public static HttpServletResponse download(String path, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }
	
	/**
	 * pdf转换成swf
	 * @param originalPath
	 * @param targetPath
	 * @return
	 */
	public static boolean pdf2swf(String originalPath, String targetPath) {
		BufferedReader bufferedReader = null;
		String classPath = Class.class.getResource("/").getPath();
		String str = classPath + "xpdf\\pdf2swf.exe -o \"" + targetPath + "\" -t \"" + originalPath + "\" -f -T 9 -s languagedir=" + classPath.substring(1) + "xpdf\\xpdf-chinese-simplified";
		System.out.println(str);
		
		try { 
			// 调用pdf2swf命令进行转换
			Process pro = Runtime.getRuntime().exec(str);   
			bufferedReader = new BufferedReader(new InputStreamReader(pro.getInputStream()));   
			
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {   
				System.out.println(line);
			}
//			bufferedReader.close();
			int exitValue = pro.waitFor();
			System.out.println(exitValue);
		} catch (Exception e) {   
			e.printStackTrace();
			return false;
		} finally {
			if (bufferedReader != null) {
				try{bufferedReader.close();}catch(Exception e){}
			};
		}
		
		return true;
	}
	
	/**
	 * 判断上传文件类型
	 * @param fileContentType
	 * @return
	 */
	public static boolean hasContentType(String fileContentType) {
		return fileContentSet.contains(fileContentType.toLowerCase());
	}
	
	/**
	 * 判断下载阅读文件类型
	 * @param fileExtension
	 * @return
	 */
	public static boolean hasReadExtensionType(String fileExtension) {
		return readExtensionSet.contains(fileExtension.toLowerCase());
	}
	
	/**
	 * 判断下载视频文件类型
	 * @param fileExtension
	 * @return
	 */
	public static boolean hasVideoExtensionType(String fileExtension) {
		return videoExtensionSet.contains(fileExtension.toLowerCase());
	}
	
	/**
	 * 判断下载图片文件类型
	 * @param fileExtension
	 * @return
	 */
	public static boolean hasImageExtensionType(String fileExtension) {
		return imageExtensionSet.contains(fileExtension.toLowerCase());
	}
	
	/**
	 * 判断在线阅读文件类型
	 * @param fileExtension
	 * @return
	 */
	public static boolean hasReadOnlineExtensionType(String fileExtension) {
		return readOnlineExtensionSet.contains(fileExtension.toLowerCase());
	}
	
	/**
	 * 判断在线视频文件类型
	 * @param fileExtension
	 * @return
	 */
	public static boolean hasVideoOnlineExtensionType(String fileExtension) {
		return videoOnlineExtensionSet.contains(fileExtension.toLowerCase());
	}
	
	/**
	 * 判断在线图片文件类型
	 * @param fileExtension
	 * @return
	 */
	public static boolean hasImageOnlineExtensionType(String fileExtension) {
		return imageOnlineExtensionSet.contains(fileExtension.toLowerCase());
	}
	
	/**
	 * 获取下载文件类型
	 * @param fileExtension
	 * @return read_extension, video_extension, image_extension, other_extension
	 */
	public static String getFileExtensionType(String fileExtension) {
		if (hasReadExtensionType(fileExtension)){
			return read_extension;
		}
		if (hasVideoExtensionType(fileExtension)){
			return video_extension;
		}
		if (hasImageExtensionType(fileExtension)){
			return image_extension;
		}
		return other_extension;
	}
	
	/**
	 * 获取在线文件类型
	 * @param fileExtension
	 * @return read_extension, video_extension, image_extension, other_extension
	 */
	public static String getFileOnlineExtensionType(String fileExtension) {
		if (hasReadOnlineExtensionType(fileExtension)){
			return read_extension;
		}
		if (hasVideoOnlineExtensionType(fileExtension)){
			return video_extension;
		}
		if (hasImageOnlineExtensionType(fileExtension)){
			return image_extension;
		}
		return fileExtension;
	}
	
	/**
	 * 获取在线文件类型
	 * @param fileExtension
	 * @return read_extension, video_extension, image_extension, other_extension
	 */
	public static String getOnlineExtensionType(String fileExtension) {
		if (hasReadExtensionType(fileExtension)){
			return read_extension;
		}
		if (hasVideoExtensionType(fileExtension)){
			return video_extension;
		}
		if (hasImageExtensionType(fileExtension)){
			return image_extension;
		}
		return other_extension;
	}
	
	public static void makeDir(String path) {
		if (path != null && !path.trim().equals("")) {
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
		}
	}
	
	public static String getCurrentYMD() {
		SimpleDateFormat simple = new SimpleDateFormat("yyyyMMdd");
		return simple.format(new Date());
	}
	
	public static String getCurrentYMDHm() {
		SimpleDateFormat simple = new SimpleDateFormat("yyyyMMddHHmm");
		return simple.format(new Date()) + "_" + (int)((Math.random()*9+1)*10000);
	}
	
	/**
	 * 重新构造文件名 例如：java_20101129.txt
	 * @param originalName
	 * @param addStr
	 * @return
	 */
	public static String getNewFileName(String originalName, String addStr) {
		String newFileName = "";
		int regex;
		if (originalName != null && !originalName.trim().equals("")) {
			regex = originalName.lastIndexOf(".");
			if (regex != -1) {
				newFileName = originalName.substring(0, regex) + "_" + addStr + "." + 
						originalName.substring(regex + 1, originalName.length());
			}
		}
		return newFileName;
	}
	
	/**
	 * 构造英文文件名 例如：ZD05001
	 * @param category
	 * @return
	 */
//	public static String getFileNameEn(Category category) {
//		String newFileName = "";
//		newFileName = CategoryUtil.getCategoryPath(category) + NumberUtil.getZeroBefore(NumberUtil.getIndex(), 3);
//		return newFileName;
//	}
	
	/**
	 * 返回文件扩展名
	 * @param path
	 * @return
	 */
	public static String getFileExtension(String path) {
		String extension = "";
		int regex;
		if (path != null && !path.trim().equals("")) {
			regex = path.lastIndexOf(".");
			if (regex != -1) {
				extension = path.substring(regex + 1, path.length());
			}
		}
		return extension;
	}
	
	/**
	 * 返回页面文件图片名称
	 * @param fileExtension
	 * @return
	 */
	public static String getImage(String fileExtension) {
		String str = "";
		if (fileExtension.equals("jpg") || fileExtension.equals("bmp")) {
			str = "jpg.gif";
		} else if (fileExtension.equals("gif")) {
			str = "gif.gif";
		} else if (fileExtension.equals("png")) {
			str = "png.gif";
		} else if (fileExtension.equals("pdf")) {
			str = "pdf.gif";
		} else if (fileExtension.equals("doc") || fileExtension.equals("docx")) {
			str = "doc.gif";
		} else if (fileExtension.equals("txt")) {
			str = "txt.gif";
		} else if (fileExtension.equals("ppt") || fileExtension.equals("pptx")) {
			str = "ppt.gif";
		} else if (fileExtension.equals("xls") || fileExtension.equals("xlsx") || fileExtension.equals("csv")) {
			str = "xls.gif";
		} else if (fileExtension.equals("rar") || fileExtension.equals("zip")) {
			str = "rar.gif";
		} else if (fileExtension.equals("wmv") || fileExtension.equals("avi") || fileExtension.equals("mpg") || fileExtension.equals("vob")) {
			str = "video.gif";
		} else {
			str = "other.gif";
		}
		return str;
	}
	/**
	 * 根据文件类型传入1 2 3
	 * @return
	 */
	public static String getFileType(String fileExtension){
		if(fileExtension.equals("jpg")||fileExtension.equals("png")||fileExtension.equals("gif")||fileExtension.equals("ps")||fileExtension.equals("jpeg")||fileExtension.equals("bmp")){
			return  "3";
		}
		if(fileExtension.equals("doc")||fileExtension.equals("docx")||fileExtension.equals("ppt")||fileExtension.equals("pdf")||fileExtension.equals("xls")||fileExtension.equals("excel")||fileExtension.equals("txt")){
			return  "1";
		}
		if(fileExtension.equals("wmv")||fileExtension.equals("avi")||fileExtension.equals("rmvb")||fileExtension.equals("swf")||fileExtension.equals("flv")){
			return  "2";
		}
		return fileExtension;
		
	}
	
	public static Set<String> getReadExtensionSet() {
		return readExtensionSet;
	}

	public static Set<String> getVideoExtensionSet() {
		return videoExtensionSet;
	}

	public static Set<String> getImageExtensionSet() {
		return imageExtensionSet;
	}

	public static void main(String[] args) throws IOException {
//		FileUtils.deleteFile("/C:/tomcat6/temp");
		
		for (int i = 0; i < 1; i++) {
			FileUtils.pdf2swf("c:\\a1.pdf", "c:\\a"+i+".swf");
		}
		
//		System.out.println(FileUtils.hasContentType("application/octet-stream"));
		
//		FileUtils.makeDir("d:/test");
		
//		System.out.println(FileUtils.getCurrentYMDHm());
		
//		System.out.println(FileUtils.getNewFileName("e.xt.a", "111"));
		
//		System.out.println(FileUtils.getFileExtension("/asdfa\\dafadsdse.xt.a"));
		
	}

	public  static  Map  getDepartmentIdStr(Long DepartmentId,Map queryMap){
		String departmentIdStr = String.valueOf(DepartmentId);
	    if(departmentIdStr.length() == 11){
	    	 String departmentId =departmentIdStr.substring(0,3);
	    	 String departmentIdO = departmentIdStr.substring(0,6);
	    	 String departmentIdT =departmentIdStr.substring(0,8);
	    	 String departmentIdS = departmentIdStr.substring(0,10);
	    	 String departmentIdF = departmentIdStr.substring(0,11);
	    	 queryMap.put("departmentId", Long.valueOf(departmentId));
	    	 queryMap.put("departmentIdO", Long.valueOf(departmentIdO));
	    	 queryMap.put("departmentIdT", Long.valueOf(departmentIdT));
	    	 queryMap.put("departmentIdS", Long.valueOf(departmentIdS));
	    	 queryMap.put("departmentIdF", Long.valueOf(departmentIdF));
	    }
	    if(departmentIdStr.length() ==9){
	    	 String departmentId =departmentIdStr.substring(0,3);
	    	 String departmentIdO = departmentIdStr.substring(0,6);
	    	 String departmentIdT =departmentIdStr.substring(0,8);
	    	 String departmentIdS = departmentIdStr.substring(0,9);
	    	 queryMap.put("departmentId", Long.valueOf(departmentId));
	    	 queryMap.put("departmentIdO", Long.valueOf(departmentIdO));
	    	 queryMap.put("departmentIdT", Long.valueOf(departmentIdT));
	    	 queryMap.put("departmentIdS", Long.valueOf(departmentIdS));
	    
	    }
	    if(departmentIdStr.length() ==7){
	    	 String departmentId =departmentIdStr.substring(0,3);
	    	 String departmentIdO = departmentIdStr.substring(0,6);
	    	 String departmentIdT =departmentIdStr.substring(0,7);
	    	 queryMap.put("departmentId", Long.valueOf(departmentId));
	    	 queryMap.put("departmentIdO", Long.valueOf(departmentIdO));
	    	 queryMap.put("departmentIdT", Long.valueOf(departmentIdT));
	  

	    }
	    if(departmentIdStr.length() ==5){
	    	 String departmentId =departmentIdStr.substring(0,3);
	    	 String departmentIdO = departmentIdStr.substring(0,5);
	    	 queryMap.put("departmentId", Long.valueOf(departmentId));
	    	 queryMap.put("departmentIdO", Long.valueOf(departmentIdO));
	  
	    }
	    if(departmentIdStr.length() ==3){
	    	 String departmentId =departmentIdStr.substring(0,3);
	    	 queryMap.put("departmentId", Long.valueOf(departmentId));
	    }
		return queryMap;
		
	}
}
