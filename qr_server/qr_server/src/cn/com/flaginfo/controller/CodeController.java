package cn.com.flaginfo.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.flaginfo.bean.Pagination;
import cn.com.flaginfo.content.CodeType;
import cn.com.flaginfo.content.ErrorCode;
import cn.com.flaginfo.dao.model.CardModel;
import cn.com.flaginfo.dao.model.QRCodeModel;
import cn.com.flaginfo.service.CardService;
import cn.com.flaginfo.service.CodeService;

@Controller
@RequestMapping("/code")
public class CodeController {

	@Autowired
	private CodeService codeService;

	@Autowired
	private CardService cardService;

	/**
	 * 生成二维码
	 * 
	 * @param request
	 * @param response
	 * @param reqJson
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/generate", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> generateQRCode(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "reqJson", required = true) String reqJson,
			QRCodeModel codeModel, CardModel cardModel) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		String path = request.getRealPath("");
		//生成二维码
		String dir = codeService.buidCode(reqJson, path);

		if (null == dir || dir.trim().equals("")) {
			map.put("retCode", ErrorCode.FAIL.getCode());
			
			//保存二维码信息
		} else {
			try {
				//判断是否是名片类型
				if(CodeType.CARD.getType() == codeModel.getQrType()){
					map = codeService.addCodeWithCard(codeModel,cardModel);
				}
				map = codeService.addCode(codeModel);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	/**
	 * 分页查询,单条数据查询
	 * 
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getQRCodeByPage(HttpServletRequest request,
			@PathVariable("id") String id, QRCodeModel model,
			Pagination<QRCodeModel> page) {
		Map<String, Object> map;
		if (id != "-1") {
			model = new QRCodeModel();
			model.setId(Integer.valueOf(id));
		}
		map = codeService.getModelByPage(model, page);
		return map;
	}

	/**
	 * 二维码数据信息保存接口
	 * 
	 * @param req
	 * @param model
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveCodeInfo(HttpServletRequest req,
			QRCodeModel model) throws IllegalAccessException,
			InvocationTargetException {
		Map<String, Object> map = codeService.addCode(model);
		return map;
	}

	/**
	 * 二维码信息删除接口
	 * 
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> deleteCodeInfo(HttpServletRequest req,
			@PathVariable("id") String id) {
		QRCodeModel model = new QRCodeModel();
		model.setId(Integer.valueOf(id));
		return codeService.deleteModel(model);
	}

	/**
	 * 二维码信息修改接口
	 * 
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> updateCodeInfo(HttpServletRequest req,
			@RequestBody String reqJson) {
		QRCodeModel model = new QRCodeModel();
		JSONObject json = JSONObject.fromObject(reqJson);
		model = (QRCodeModel) JSONObject.toBean(json, QRCodeModel.class);
		return codeService.modifyModel(model);
	}

}
