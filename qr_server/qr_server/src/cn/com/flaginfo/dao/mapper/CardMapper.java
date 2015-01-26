package cn.com.flaginfo.dao.mapper;

import cn.com.flaginfo.bean.Pagination;
import cn.com.flaginfo.dao.model.CardModel;

public interface CardMapper {

	/**
	 * 查询二维码单条记录
	 * @param model
	 * @return
	 */
	public CardModel selectCard(CardModel model);
	/**
	 * 分页查询二维码记录列表
	 * @param model
	 * @param page
	 * @return
	 */
	public Pagination<CardModel> selectCardByPage(CardModel model, Pagination<CardModel> page);
	
	/**
	 * 获取数据的总条数
	 * @param model
	 * @return
	 */
	public int getCount(CardModel model);
	
	/**
	 * 保存
	 * @param model
	 * @return
	 */
	public int save(CardModel model);
	
	/**
	 * 删除
	 * @param model
	 * @return
	 */
	public int delete(CardModel model);
	/**
	 * 更新
	 * @param model
	 * @return
	 */
	public int update(CardModel model);

}
