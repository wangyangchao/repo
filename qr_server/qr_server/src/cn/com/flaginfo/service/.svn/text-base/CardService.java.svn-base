package cn.com.flaginfo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.flaginfo.bean.Pagination;
import cn.com.flaginfo.content.ErrorCode;
import cn.com.flaginfo.dao.impl.CardDaoImpl;
import cn.com.flaginfo.dao.model.CardModel;

@Service
public class CardService {

	
	@Autowired
	private CardDaoImpl cardDaoImpl;
	/**
	 * 
	 * @param model
	 * @param page
	 * @return
	 */
	public Map<String, Object> getModelByPage(CardModel model,
			Pagination<CardModel> page) {
		
		Pagination<CardModel> pg = cardDaoImpl.selectQRCodeByPage(model, page);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("retCode", ErrorCode.SUCC.getCode());
		map.put("data", pg);
		return map;
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	public Map<String, String> addCode(CardModel model) {
		Map<String, String> map = new HashMap<String, String>();
		int i = cardDaoImpl.saveModel(model);
		if(i == 1){
			map.put("retCode", ErrorCode.SUCC.getCode());
		}else{
			map.put("retCode", ErrorCode.FAIL.getCode());
		}
		
		
		return map;
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	public Map<String, Object> deleteModel(CardModel model) {
		Map<String, Object> map = new HashMap<String, Object>();
		int i = cardDaoImpl.deleteModel(model);
		if(i ==1){
			
			map.put("retCode", ErrorCode.SUCC.getCode());
		}else{
			map.put("retCode", ErrorCode.FAIL.getCode());
			
		}
		return map;
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	public Map<String, Object> modifyModel(CardModel model) {
		Map<String, Object> map = new HashMap<String, Object>();
		int i = cardDaoImpl.updateModel(model);
		if(i == 1){
			map.put("retCode", ErrorCode.SUCC.getCode());
		}else{
			map.put("retCode", ErrorCode.FAIL.getCode());
			
		}
		return map;
	}

}
