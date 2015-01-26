package cn.com.flaginfo.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.flaginfo.bean.Pagination;
import cn.com.flaginfo.dao.model.CardModel;
import cn.com.flaginfo.service.CardService;

@Controller
@RequestMapping("/card")
public class CardController {

	@Autowired
	private CardService cardService;
	
	/**
	 * 分页查询,单条数据查询
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getCardByPage(HttpServletRequest request,@PathVariable("id") int id,CardModel model,Pagination<CardModel> page){
		Map<String, Object> map ;
		map = cardService.getModelByPage(model, page);
		return map;
	}
	
	
	/**
	 * 二维码数据信息保存接口
	 * @param req
	 * @param model
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveCodeInfo(HttpServletRequest req ,CardModel model ) throws IllegalAccessException, InvocationTargetException{
		Map<String, String> map = cardService.addCode(model);
		return map;
	}
	
	/**
	 * 二维码信息删除接口
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> deleteCodeInfo(HttpServletRequest req,@PathVariable("id") int id){
		CardModel model = new CardModel();
		model.setId(id);
		return cardService.deleteModel(model);
	}
	
	/**
	 * 二维码信息修改接口
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> updateCodeInfo(HttpServletRequest req, @RequestBody String reqJson){
		CardModel model = new CardModel();
		JSONObject json = JSONObject.fromObject(reqJson);
		model = (CardModel) JSONObject.toBean(json, CardModel.class);
		return cardService.modifyModel(model);
	}
	
	
}
