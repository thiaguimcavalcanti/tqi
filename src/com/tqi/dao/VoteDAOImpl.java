package com.tqi.dao;

import java.util.List;

import org.hibernate.criterion.Projections;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.stereotype.Repository;

import com.tqi.model.ResultAnalyticBean;
import com.tqi.model.VoteBean;
import com.tqi.util.EnumMessages;
import com.tqi.util.exceptions.AppException;

@Repository
public class VoteDAOImpl extends CommonDAOImpl<VoteBean> implements VoteDAO {

	public VoteDAOImpl() {
		super(VoteBean.class);
	}
	
	/**
	 * Metodo que retorna os votos agrupados por mascote.
	 * 
	 * @return List<ResultAnalyticBean>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ResultAnalyticBean> getVotesResults() throws AppException {
		try {
			return (List<ResultAnalyticBean>) getCurrentSession().createCriteria(VoteBean.class) 
					.createAlias("mascoteBean", "mascot", JoinType.RIGHT_OUTER_JOIN)
                    .setProjection(Projections.projectionList()
                            .add(Projections.groupProperty("mascot.mascotID"), "mascotID")
                            .add(Projections.property("mascot.name"), "mascotName")
                            .add(Projections.count("mascoteBean"), "qtdVotes"))
                    .setResultTransformer(new AliasToBeanResultTransformer(ResultAnalyticBean.class))
                    .list();
		} catch (Exception e) {
			logger.error(createLogMessage(EnumMessages.ERRO_GET_VOTES_RESULT), e);
			throw new AppException(EnumMessages.ERRO_GET_VOTES_RESULT);
		}
	}
	
}
