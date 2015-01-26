package cn.com.flaginfo.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.com.flaginfo.content.ErrorCode;

public class FileUploadUtil {

	public static Map<String, Object> upload(HttpServletRequest request) 
			throws IOException{
		/**
		 * 	* 创建一个磁盘文件项工厂.
			* 创建一个核心文件上传类.
			* 使用核心类 解析request.
			* 解析完了之后返回一个List集合.
			*	* 遍历这个集合.
			*		* 是普通项
			*			* 直接获得值就OK了
			*		* 是文件项
			*			* 文件上传项 写文件上传的代码.
		 */
		
		Map<String, Object> map = null;
		try {
			// 1.创建磁盘文件项工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 1.1 设置缓冲区的大小.
			factory.setSizeThreshold(3 * 1024 * 1024);//以字节为单位. 默认是10kb.
			// 1.2 设置临时文件存放的路径.
			@SuppressWarnings("deprecation")
			String temp = request.getRealPath("/temp");
			factory.setRepository(new File(temp));
			// 2.创建核心解析类
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			// 解决中文文件名乱码
			fileUpload.setHeaderEncoding("UTF-8");
			// 设置上传的总大小
			fileUpload.setSizeMax(5 * 1024 * 1024);
			// 3.解析request对象.
			List<FileItem> list = fileUpload.parseRequest(request);
			for (FileItem fileItem : list) {
				// 判断是否是文件项.
				if(fileItem.isFormField()){
					// 是表单的普通项
					String name = fileItem.getFieldName();// 获得普通项的名称
					String value = fileItem.getString("UTF-8");// 获得普通项的值.
					System.out.println("普通项: " + name + "  "+ value);
				}else{
					// 文件上传项
					String filename = fileItem.getName();//获得的是文件名.
					System.out.println(filename);
					// 兼容IE6
					if(filename.lastIndexOf("\\") != -1){
						// 使用的是IE6
						filename = filename.substring(filename.lastIndexOf("\\")+1);
					}
					// 解决文件名重名的问题.
					filename = UUID.randomUUID().toString()+"_"+filename;
					@SuppressWarnings("deprecation")
					String path = request.getRealPath("/upload");
					File f = new File(path);
					System.out.println(path);
					if(!f.exists()){
						// 路径不存在.
						f.mkdirs();
					}
					// 获得文件中的数据.
					InputStream is = fileItem.getInputStream();
					// 获得webRoot下的upload的路径.
					OutputStream os = new FileOutputStream(path + "/"+filename);
					// 两个流对接
					int len = 0;
					byte[] b = new byte[1024];
					while((len = is.read(b))!=-1){
						os.write(b,0,len);
					}
					is.close();
					os.close();
					// 用于删除临时文件
					fileItem.delete();
					map = new HashMap<String, Object>();
					String con = PropertiesUtil.getInstance().getValue("httpConnect");
					map.put("retCode", ErrorCode.SUCC.getCode());
					map.put("url", con + filename);
				}
			}
		} catch (FileUploadException e) {
			map = new HashMap<String, Object>();
			map.put("retCode", ErrorCode.FAIL.getCode());
			e.printStackTrace();
		}
	
		return map;
	}
}
