package com.tqi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tqi.dao.AppConfigurationDAO;
import com.tqi.dao.CommonDAO;
import com.tqi.model.AppConfigurationBean;
import com.tqi.util.exceptions.AppException;

@Service("appConfigurationService")
public class AppConfigurationServiceImpl extends CommonServiceImpl<AppConfigurationBean> implements AppConfigurationService {

	@Autowired
	private AppConfigurationDAO appConfigurationDAO;
	
	public AppConfigurationServiceImpl() {}
	
	public AppConfigurationServiceImpl(Class<AppConfigurationBean> clazzToSet) {
		super(clazzToSet);
	}
	
	@Override
	protected CommonDAO<AppConfigurationBean> getDao() throws AppException {
		return (CommonDAO<AppConfigurationBean>) appConfigurationDAO;
	}

}
