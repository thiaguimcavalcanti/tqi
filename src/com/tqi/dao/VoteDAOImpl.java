package com.tqi.dao;

import org.springframework.stereotype.Repository;

import com.tqi.model.VoteBean;

@Repository
public class VoteDAOImpl extends CommonDAOImpl<VoteBean> implements VoteDAO {

	public VoteDAOImpl() {
		super(VoteBean.class);
	}
}
