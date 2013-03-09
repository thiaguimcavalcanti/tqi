package com.tqi.dao;

import java.util.List;

import com.tqi.model.MascotBean;
import com.tqi.util.exceptions.AppException;

public interface MascotDAO extends CommonDAO<MascotBean> {

	List getVotesResults() throws AppException;

	
}
