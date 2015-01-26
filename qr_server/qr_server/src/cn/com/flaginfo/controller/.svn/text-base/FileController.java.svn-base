package cn.com.flaginfo.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfWriter;

import cn.com.flaginfo.content.ErrorCode;
import cn.com.flaginfo.content.FileType;
import cn.com.flaginfo.util.PropertiesUtil;

@Controller("/file")
public class FileController {

	/**
	 * 上传文件
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "resource", "deprecation" })
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> uploadFile(HttpServletRequest request,
			String type, String guid, @RequestParam MultipartFile file) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(file.getContentType());
		map.put("retCode", ErrorCode.FAIL.getCode());
		FileOutputStream out;
		// 文件类型分类存放
		String path = "";
		if (!StringUtils.isEmpty(type)) {
			if (type.trim().equals(FileType.LOGO.getType())) {
				path = PropertiesUtil.getInstance().getValue("logopath");
			} else if (type.trim().equals(FileType.HEAD.getType())) {
				path = PropertiesUtil.getInstance().getValue("headpath");
			} else {
				path = PropertiesUtil.getInstance().getValue("filepath");
			}
		} else {
			return map;
		}
		try {
			// 组合文件信息
			String tail = file.getOriginalFilename().split(".")[1];
			out = new FileOutputStream(new File(request.getRealPath(path)
					+ guid + tail));

			out.write(file.getBytes());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return map;
		} catch (IOException e) {

			e.printStackTrace();
			return map;
		}
		map.put("retCode", ErrorCode.SUCC.getCode());
		return map;
	}

	/**
	 * 文件下载
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/pdf",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> downLoadFile(HttpServletRequest request,
			String guid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("retCode", ErrorCode.FAIL.getCode());

		// step 1：创建Document对象
		Document document = new Document();
		// 解决文件名重名的问题.
		String filePath = request.getRealPath("")
				+ PropertiesUtil.getInstance().getValue("filedown") + "/"
				+ guid + ".pdf";
		File f = new File(filePath);
		if (!f.exists()) {
			// 路径不存在.
			f.mkdirs();
		}
		// step 2：获取PdfWriter实例
		try {
			PdfWriter.getInstance(document, new FileOutputStream(filePath));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return map;
		} catch (DocumentException e1) {
			e1.printStackTrace();
			return map;
		}

		// step 3：打开Document
		document.open();

		// step 4：添加内容
		// document.add(new Paragraph("Hello World!"));
		// 读取一个图片
		Image image;
		try {
			image = Image.getInstance(request.getRealPath("")
					+ PropertiesUtil.getInstance().getValue("imagecode") + "/"
					+ guid + ".png");
			document.add(image);
		} catch (BadElementException e) {
			e.printStackTrace();
			return map;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return map;
		} catch (IOException e) {
			e.printStackTrace();
			return map;
		}
		catch (DocumentException e) {
			e.printStackTrace();
			return map;
		}
		// step 5：关闭打开的Document
		document.close();
		map.put("retCode", ErrorCode.SUCC.getCode());
		return map;
	}
}
