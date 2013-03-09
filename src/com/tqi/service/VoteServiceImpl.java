package com.tqi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tqi.dao.CommonDAO;
import com.tqi.dao.VoteDAO;
import com.tqi.model.ResultAnalyticBean;
import com.tqi.model.VoteBean;
import com.tqi.util.exceptions.AppException;

@Service("voteService")
public class VoteServiceImpl extends CommonServiceImpl<VoteBean> implements VoteService {

	@Autowired
	private VoteDAO voteDAO;
	
	public VoteServiceImpl() {}
	
	public VoteServiceImpl(Class<VoteBean> clazzToSet) {
		super(clazzToSet);
	}
	
	@Override
	protected CommonDAO<VoteBean> getDao() throws AppException {
		return (CommonDAO<VoteBean>) voteDAO;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<ResultAnalyticBean> getVotesResults() throws AppException {
		return voteDAO.getVotesResults();
	}
}
