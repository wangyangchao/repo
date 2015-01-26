package cn.com.flaginfo.dao.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import cn.com.flaginfo.bean.Pagination;

public abstract class BaseDao<M, B> {

	/**
	 * 获取记录总条数
	 * 
	 * @param b
	 * @return
	 */
	public abstract int getCount(B b);

	/**
	 * 加载分页信息，使用分页查询数据
	 * 
	 * @param m
	 * @param b
	 * @param r
	 * @return
	 */
	public abstract List<B> selectList(Class<M> m, B b, RowBounds r);

	/**
	 * 分页查询的模版方法</br> <B>使用方法：</B>子类中实现getCount() and selectList() 接口方法
	 * 
	 * @param m
	 * @param b
	 * @param p
	 * @return
	 */
	public Pagination<B> getDataByPage(Class<M> m, B b, Pagination<B> p) {
		int offset = (p.getCurrentPage() - 1) * p.getPageSize();
		int limit = p.getPageSize();
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<B> ls = this.selectList(m, b, rowBounds);

		Pagination<B> pg = new Pagination<B>();

		int count = this.getCount(b);
		pg.setDataList(ls);
		pg.setCurrentPage(p.getCurrentPage());
		pg.setPageSize(p.getPageSize());
		pg.setTotalPages(count % p.getPageSize() == 0 ? count / p.getPageSize()
				: count / p.getPageSize() + 1);
		pg.setTotalRecords(count);

		return pg;
	}
}
