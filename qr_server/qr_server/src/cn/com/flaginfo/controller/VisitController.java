package cn.com.flaginfo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.flaginfo.dao.model.QRCodeModel;
import cn.com.flaginfo.dao.model.VisitModel;
import cn.com.flaginfo.service.CodeService;
import cn.com.flaginfo.service.VisitService;

@Controller
@RequestMapping("/visit")
public class VisitController {

	@Autowired
	private VisitService visitService;
	
	@Autowired
	private CodeService codeService;
	/**
	 * 
	 * @param request
	 * @param id
	 * @param model
	 * @param page
	 * @return
	 */
	@RequestMapping(value="", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> visitCode(HttpServletRequest request,VisitModel visitModel, @RequestParam String guid){
		Map<String, Object> map =null;
		if(StringUtils.isEmpty(guid)){
			return map;
		}
		QRCodeModel model = new QRCodeModel();
		model.setGuid(guid);
		//根据guid获取二维码数据
		model = codeService.getCode(model);
		visitModel.setQcId(model.getId());
		//获取请求Ip
		visitModel.setVisitIp(request.getRemoteAddr());
		//保存请求信息
		visitService.add(visitModel);
		map = visitService.process(model);
		return map;
	}
	
}
