package com.tqi.dao;

import java.util.List;

import com.tqi.model.ResultAnalyticBean;
import com.tqi.model.VoteBean;
import com.tqi.util.exceptions.AppException;

public interface VoteDAO extends CommonDAO<VoteBean> {

	List<ResultAnalyticBean> getVotesResults() throws AppException;
}
