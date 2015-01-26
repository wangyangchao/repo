package cn.com.flaginfo.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.flaginfo.bean.Pagination;
import cn.com.flaginfo.dao.base.BaseDao;
import cn.com.flaginfo.dao.mapper.CardMapper;
import cn.com.flaginfo.dao.model.CardModel;

@Component
public class CardDaoImpl extends BaseDao<CardMapper, CardModel> {

	@Autowired
	private SqlSessionTemplate sessionTemplate;
	

	/**
	 * @param model
	 * @return
	 */
	public CardModel selectQRCode(CardModel model) {
	
		return sessionTemplate.getMapper(CardMapper.class).selectCard(model);
	}

	/**
	 * @param model
	 * @param page
	 * @return
	 */
	public Pagination<CardModel> selectQRCodeByPage(CardModel model,
			Pagination<CardModel> page) {
		return this.getDataByPage(CardMapper.class, model, page);
	}

	
	@Override
	public int getCount(CardModel model) {
		return sessionTemplate.getMapper(CardMapper.class).getCount(model);
	}


	@Override
	public List<CardModel> selectList(Class<CardMapper> m, CardModel b,
			RowBounds r) {
		return sessionTemplate.selectList(m.getName() + ".getList", b, r);
		
	}

	/**
	 * @param model
	 * @return
	 */
	public int saveModel(CardModel model) {
		CardModel modelDB = this.selectQRCode(model);
		int i;
		if(null != modelDB){
			i = this.updateModel(model);
			return i;
		}else{
			return sessionTemplate.getMapper(CardMapper.class).save(model);
		}
		
	}

	/**
	 * @param model
	 * @return
	 */
	public int deleteModel(CardModel model) {
		return sessionTemplate.getMapper(CardMapper.class).delete(model);
	}

	/**
	 * @param model
	 * @return
	 */
	public int updateModel(CardModel model) {
		
		return sessionTemplate.getMapper(CardMapper.class).update(model);
	}


}
