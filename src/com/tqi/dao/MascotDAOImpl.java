package com.tqi.dao;

import org.springframework.stereotype.Repository;

import com.tqi.model.MascotBean;

@Repository
public class MascotDAOImpl extends CommonDAOImpl<MascotBean> implements MascotDAO {

	public MascotDAOImpl() {
		super(MascotBean.class);
	}

}
