package cn.com.flaginfo.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.flaginfo.dao.mapper.VisitMapper;
import cn.com.flaginfo.dao.model.VisitModel;

@Component
public class VisitDaoImpl{

	@Autowired
	private SqlSessionTemplate sessionTemplate;

	public int save(VisitModel model){
		return sessionTemplate.getMapper(VisitMapper.class).insert(model);
	}
}
