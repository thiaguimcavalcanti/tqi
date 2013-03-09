package com.tqi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tqi.dao.CommonDAO;
import com.tqi.dao.MascotDAO;
import com.tqi.model.MascotBean;
import com.tqi.util.exceptions.AppException;

@Service("mascotService")
public class MascotServiceImpl extends CommonServiceImpl<MascotBean> implements MascotService {

	@Autowired
	private MascotDAO mascotDAO;
	
	public MascotServiceImpl() {}
	
	public MascotServiceImpl(Class<MascotBean> clazzToSet) {
		super(clazzToSet);
	}
	
	@Override
	protected CommonDAO<MascotBean> getDao() throws AppException {
		return (CommonDAO<MascotBean>) mascotDAO;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List getVotesResults() throws AppException {
		return mascotDAO.getVotesResults();
	}
}
