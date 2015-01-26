package cn.com.flaginfo.dao.mapper;

import cn.com.flaginfo.bean.Pagination;
import cn.com.flaginfo.dao.model.VisitModel;

public interface VisitMapper {

	/**
	 * 查询二维码单条记录
	 * @param model
	 * @return
	 */
	public VisitModel selectCard(VisitModel model);
	/**
	 * 分页查询二维码记录列表
	 * @param model
	 * @param page
	 * @return
	 */
	public Pagination<VisitModel> selectCardByPage(VisitModel model, Pagination<VisitModel> page);
	
	/**
	 * 获取数据的总条数
	 * @param model
	 * @return
	 */
	public int getCount(VisitModel model);
	
	/**
	 * 保存
	 * @param model
	 * @return
	 */
	public int save(VisitModel model);
	
	/**
	 * 删除
	 * @param model
	 * @return
	 */
	public int delete(VisitModel model);
	/**
	 * 更新
	 * @param model
	 * @return
	 */
	public int update(VisitModel model);
	
	
	public int insert(VisitModel model);

}
