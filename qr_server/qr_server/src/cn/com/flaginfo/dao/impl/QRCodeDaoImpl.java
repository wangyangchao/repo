package cn.com.flaginfo.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.com.flaginfo.bean.Pagination;
import cn.com.flaginfo.dao.base.BaseDao;
import cn.com.flaginfo.dao.mapper.QRCodeMapper;
import cn.com.flaginfo.dao.model.QRCodeModel;

@Repository
public class QRCodeDaoImpl extends BaseDao<QRCodeMapper, QRCodeModel> {

	@Autowired
	private SqlSessionTemplate sessionTemplate;
	

	/**
	 * 单条数据查询
	 * @param model
	 * @return
	 */
	public QRCodeModel selectQRCode(QRCodeModel model) {
	
		return sessionTemplate.getMapper(QRCodeMapper.class).selectQRCode(model);
	}

	/**
	 * 分页数据查询
	 * @param model
	 * @param page
	 * @return
	 */
	public Pagination<QRCodeModel> selectQRCodeByPage(QRCodeModel model,
			Pagination<QRCodeModel> page) {
		return this.getDataByPage(QRCodeMapper.class, model, page);
	}

	
	@Override
	public int getCount(QRCodeModel model) {
		return sessionTemplate.getMapper(QRCodeMapper.class).getCount(model);
	}


	@Override
	public List<QRCodeModel> selectList(Class<QRCodeMapper> m, QRCodeModel b,
			RowBounds r) {
		return sessionTemplate.selectList(m.getName() + ".getList", b, r);
		
	}

	
	public static void main(String[] args){
//		QRCodeDaoImpl d = new QRCodeDaoImpl();
//		d.selectList(QRCodeMapper.class, null, null);
	}

	/**
	 * 数据库保存二维码信息
	 * @param model
	 * @return
	 */
	public int saveModel(QRCodeModel model) {
		
		return sessionTemplate.getMapper(QRCodeMapper.class).save(model);
	}

	/**
	 * 删除二维码信息
	 * @param model
	 * @return
	 */
	public int deleteModel(QRCodeModel model) {
		return sessionTemplate.getMapper(QRCodeMapper.class).delete(model);
	}

	/**
	 * 二维码信息修改
	 * @param model
	 * @return
	 */
	public int updateModel(QRCodeModel model) {
		
		return sessionTemplate.getMapper(QRCodeMapper.class).update(model);
	}


}
