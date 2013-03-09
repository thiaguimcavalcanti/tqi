package com.tqi.dao;

import org.springframework.stereotype.Repository;

import com.tqi.model.AppConfigurationBean;

@Repository
public class AppConfigurationDAOImpl extends CommonDAOImpl<AppConfigurationBean> implements AppConfigurationDAO {

	public AppConfigurationDAOImpl() {
		super(AppConfigurationBean.class);
	}

}
