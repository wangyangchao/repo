package cn.com.flaginfo.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.com.flaginfo.bean.Pagination;
import cn.com.flaginfo.content.CodeType;
import cn.com.flaginfo.dao.impl.VisitDaoImpl;
import cn.com.flaginfo.dao.model.CardModel;
import cn.com.flaginfo.dao.model.QRCodeModel;
import cn.com.flaginfo.dao.model.VisitModel;

@Service
public class VisitService {

	@Autowired
	private CardService cardService;
	
	@Autowired
	private VisitDaoImpl visitDaoImpl;
	/**
	 * 
	 * @param model
	 * @return
	 */
	public Map<String, Object> process(QRCodeModel model) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(null == CodeType.getType(model.getQrType())){
			model.setQrType(1);
		}
		
		switch (CodeType.getType(model.getQrType())) {
		case TEXT:
			map.put("data", model.getText());
			break;
		case URL:
			map.put("data", model.getUrl());
			break;
		case MSG:
			map.put("data", model.getMdn());
			break;
		case CARD:
			CardModel card = new CardModel();
			card.setQcId(model.getId());
			Pagination<CardModel> page = new Pagination<CardModel>();
			map.put("data", cardService.getModelByPage(card, page));
			break;
		case IMG:
			break;
		case FILE:
			break;
		default:
			break;
		}
		return map;
	}
	
	public int add(VisitModel model){
		
		return visitDaoImpl.save(model);
	}

}
