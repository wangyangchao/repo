package cn.com.flaginfo.dao.mapper;

import cn.com.flaginfo.bean.Pagination;
import cn.com.flaginfo.dao.model.QRCodeModel;

public interface QRCodeMapper {

	/**
	 * 查询二维码单条记录
	 * @param model
	 * @return
	 */
	public QRCodeModel selectQRCode(QRCodeModel model);
	/**
	 * 分页查询二维码记录列表
	 * @param model
	 * @param page
	 * @return
	 */
	public Pagination<QRCodeModel> selectQRCodeByPage(QRCodeModel model, Pagination<QRCodeModel> page);
	
	/**
	 * 获取数据的总条数
	 * @param model
	 * @return
	 */
	public int getCount(QRCodeModel model);
	
	/**
	 * 保存
	 * @param model
	 * @return
	 */
	public int save(QRCodeModel model);
	
	/**
	 * 删除
	 * @param model
	 * @return
	 */
	public int delete(QRCodeModel model);
	/**
	 * 更新
	 * @param model
	 * @return
	 */
	public int update(QRCodeModel model);

}
