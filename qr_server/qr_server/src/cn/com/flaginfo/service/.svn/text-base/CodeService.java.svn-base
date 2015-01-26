package cn.com.flaginfo.service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.flaginfo.bean.Pagination;
import cn.com.flaginfo.content.ErrorCode;
import cn.com.flaginfo.dao.impl.CardDaoImpl;
import cn.com.flaginfo.dao.impl.QRCodeDaoImpl;
import cn.com.flaginfo.dao.model.CardModel;
import cn.com.flaginfo.dao.model.QRCodeModel;

@Service
public class CodeService {

	@Autowired
	private QRCodeDaoImpl qRCodeDaoImpl;
	

	@Autowired
	private CardDaoImpl cardDaoImpl;

	@Autowired
	private CodeGenerateService codeGenerateService;

	/**
	 * 创建二维码
	 * 
	 * @param reqJson
	 * @return
	 */
	public String buidCode(String reqJson, String path) {

		boolean flag = baseCheck(reqJson);
		if (!flag) {
			return null;
		}
		JSONObject json = JSONObject.fromObject(reqJson);
		String url = codeGenerateService.generate(json, path);
		return url;
	}

	/**
	 * 请求json 数据基础校验
	 * 
	 * @param reqJson
	 * @return
	 */
	private boolean baseCheck(String reqJson) {
		
		// 非空校验
		if (null == reqJson || reqJson.trim() == "") {
			return false;
		}
		JSONObject json = JSONObject.fromObject(reqJson);
		// 二维码内容非空校验
		if (json.get("content") == null) {
			return false;
		}
		if(json.get("guid") == null || json.getString("guid").trim().equals("")){
			return false;
		}
		return true;
	}

	public Map<String, Object> getModelByPage(QRCodeModel model,
			Pagination<QRCodeModel> page) {
		page = qRCodeDaoImpl.selectQRCodeByPage(model, page);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("retCode", ErrorCode.SUCC.getCode());
		map.put("data", page);
		return map;
	}

	/**
	 * 新增二维码信息
	 * 
	 * @param model
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public Map<String, Object> addCode(QRCodeModel model) throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> map = new HashMap<String, Object>();

		int i;
		
		QRCodeModel bean = qRCodeDaoImpl.selectQRCode(model);
		
		if(null != bean){
			BeanUtils.copyProperties(bean, model);
			i = qRCodeDaoImpl.updateModel(model);
		
		}else{
			
			i = qRCodeDaoImpl.saveModel(model);
		}
		
		if (i == 1) {
			map.put("retCode", ErrorCode.SUCC.getCode());
		} else {
			map.put("retCode", ErrorCode.FAIL.getCode());
		}
		return map;
	}

	/**
	 * 二维码信息查询
	 * 
	 * @param model
	 * @return
	 */
	public QRCodeModel getCode(QRCodeModel model) {

		model = qRCodeDaoImpl.selectQRCode(model);
		return model;
	}

	/**
	 * 二维码信息删除接口
	 * 
	 * @param model
	 * @return
	 */
	public Map<String, Object> deleteModel(QRCodeModel model) {
		Map<String, Object> map = new HashMap<String, Object>();
		int i = qRCodeDaoImpl.deleteModel(model);
		if (i == 1) {
			map.put("retCode", ErrorCode.SUCC.getCode());
		} else {
			map.put("retCode", ErrorCode.FAIL.getCode());
		}
		return map;
	}

	/**
	 * 二维码信息修改
	 * 
	 * @param model
	 * @return
	 */
	public Map<String, Object> modifyModel(QRCodeModel model) {
		Map<String, Object> map = new HashMap<String, Object>();
		int i = qRCodeDaoImpl.updateModel(model);
		if (i == 1) {
			map.put("retCode", ErrorCode.SUCC.getCode());
		} else {
			map.put("retCode", ErrorCode.FAIL.getCode());
		}
		return map;
	}

	/**
	 * @param codeModel
	 * @param cardModel
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public Map<String, Object> addCodeWithCard(QRCodeModel codeModel, CardModel cardModel) throws IllegalAccessException, InvocationTargetException {
		
		Map<String, Object> map = new HashMap<String, Object>();

		//默认设置
		map.put("retCode", ErrorCode.FAIL.getCode());
		
		int i;
		
		QRCodeModel bean = qRCodeDaoImpl.selectQRCode(codeModel);
		
		if(null != bean){
			BeanUtils.copyProperties(bean, codeModel);
			i = qRCodeDaoImpl.updateModel(codeModel);
		
		}else{
			
			i = qRCodeDaoImpl.saveModel(codeModel);
		}
		
		if (i == 1) {
			cardModel.setQcId(bean.getId());
			/**检查更新或者提交保存*/
			i = cardDaoImpl.saveModel(cardModel);
			if(i == 1){
				
				map.put("retCode", ErrorCode.SUCC.getCode());
			}
		}
		
		return map;
		
	}

}
