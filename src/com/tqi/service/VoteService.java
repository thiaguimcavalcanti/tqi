package com.tqi.service;

import java.util.List;

import com.tqi.model.ResultAnalyticBean;
import com.tqi.model.VoteBean;
import com.tqi.util.exceptions.AppException;

public interface VoteService extends CommonService<VoteBean> {

	List<ResultAnalyticBean> getVotesResults() throws AppException;
	
}
