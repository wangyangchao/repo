package cn.com.flaginfo.service;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import cn.com.flaginfo.util.PropertiesUtil;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

@Service
public class CodeGenerateService {

	// 颜色设置
	private static final int BLACK = 0xff000000;
	private static final int WHITE = 0xFFFFFFFF;

	// QRCODE规格
	private static final int QRCODE_WIDTH = 300;

	// logo对二维码缩放的比例
	private static final double RATE = 0.20;

	/**
	 * 生成二维码
	 * 
	 * @param json
	 * @param map
	 * @return
	 */
	public String generate(JSONObject json, String path) {
		this.defaultSet(json);

		BufferedImage image = null;
		BitMatrix byteMatrix;
		try {
			// 设置二维码的细节信息（编码，形状，容错）
			Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel
					.valueOf(json.get("errLevel") == null ? "H" : json.get(
							"errLevel").toString()));

			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			// 生成矩阵数据
			byteMatrix = new MultiFormatWriter().encode(
					json.get("content").toString(),
					BarcodeFormat.QR_CODE,
					json.getInt("width") == 0 ? QRCODE_WIDTH : json
							.getInt("width"),
					json.getInt("width") == 0 ? QRCODE_WIDTH : json
							.getInt("width"), hints);
			// 将矩阵数据转换成BufferdImage
			image = this.toBufferedImage(byteMatrix, json);

			// 判断是否存在logo图图片
			if (json.get("logo")!=null && json.get("logo").equals("1")) {
				image = this.generateQRCodeWithLogo(image, path,
						json.get("guid").toString());
			}

			StringBuilder sb = new StringBuilder(path
					+ PropertiesUtil.getInstance().getValue("imagecode"));
			File f = new File(sb.toString());
			if (!f.exists()) {
				f.mkdirs();
			}
			String path_sub = "/" + json.getString("guid") + ".png";
			sb.append(path_sub);
			ImageIO.write(image, "png", new File(sb.toString()));
			return path_sub;
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 系统默认设置生成二维码参数
	 * */
	private void defaultSet(JSONObject json) {
		/** 宽度不存在，设置默认 */
		if (json.get("width") == null) {
			json.put("width", 0);
		}
		/** 前景色不存在，设置默认 */
		if (json.get("foreColor") == null) {
			json.put("foreColor", 0);
		}
		/** 背景色不存在，设置默认 */
		if (json.get("backColor") == null) {
			json.put("backColor", 0);
		}
	}

	/**
	 * 将BitMatrix 转化为 bufferedImage</BR>
	 * <p>
	 * 设置前景色和背景色（默认设置为黑 & 白）
	 * </p>
	 * 
	 * @param bitMatrix
	 */
	protected BufferedImage toBufferedImage(BitMatrix matrix, JSONObject json) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(
						x,
						y,
						matrix.get(x, y) == true ? (json.getInt("foreColor") == 0 ? BLACK
								: json.getInt("foreColor"))
								: (json.getInt("backColor") == 0 ? WHITE : json
										.getInt("backColor")));
			}
		}
		return image;
	}

	public BufferedImage generateQRCodeWithLogo(BufferedImage bufferedImage,
			String path, String guid) throws MalformedURLException, IOException {
		// 获取logo图片
		BufferedImage logo = ImageIO.read(new File(path
				+ PropertiesUtil.getInstance().getValue("imagelog") + guid));

		if(logo == null){
			return bufferedImage;
		}
		Graphics g = bufferedImage.getGraphics();
		// 中心点位置
		int centerX = (int) (bufferedImage.getWidth() * (1 - RATE) / 2);
		int centerY = (int) (bufferedImage.getHeight() * (1 - RATE) / 2);
		// 根据缩放比例生成logo规格
		int logoWidth = (int) (bufferedImage.getWidth() * RATE);
		int logoHeight = (int) (bufferedImage.getHeight() * RATE);

		g.drawImage(logo, centerX, centerY, logoWidth, logoHeight, null);
		g.dispose();
		logo.flush();
		return bufferedImage;
	}

}
