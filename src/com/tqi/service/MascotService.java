package com.tqi.service;

import java.util.List;

import com.tqi.model.MascotBean;
import com.tqi.util.exceptions.AppException;

public interface MascotService extends CommonService<MascotBean> {

	List getVotesResults() throws AppException;
	
	
	
}
