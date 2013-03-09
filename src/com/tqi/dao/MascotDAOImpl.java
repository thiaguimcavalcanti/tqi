package com.tqi.dao;

import java.util.List;

import org.hibernate.criterion.Projections;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import com.tqi.model.MascotBean;
import com.tqi.util.EnumMessages;
import com.tqi.util.exceptions.AppException;

@Repository
public class MascotDAOImpl extends CommonDAOImpl<MascotBean> implements MascotDAO {

	public MascotDAOImpl() {
		super(MascotBean.class);
	}
	
	@Override
	public List getVotesResults() throws AppException {
		try {
			return getCurrentSession().createCriteria(MascotBean.class) 
					.createAlias("listVotes", "votes", JoinType.LEFT_OUTER_JOIN)
                    .setProjection(Projections.projectionList()
                            .add(Projections.groupProperty("mascotID"))
                            .add(Projections.count("votes.mascoteBean"))           
                    ).list();
		} catch (Exception e) {
			logger.error(createLogMessage(EnumMessages.ERRO_FIND_BY_NAME), e);
			throw new AppException(EnumMessages.ERRO_FIND_BY_NAME);
		}
	}
}
