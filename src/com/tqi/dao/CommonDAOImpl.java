package com.tqi.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.tqi.util.AttributesSession;
import com.tqi.util.EnumMessages;
import com.tqi.util.exceptions.AppException;

@Transactional(propagation = Propagation.REQUIRED)
public class CommonDAOImpl<T extends Serializable> implements CommonDAO<T> {

	private Class<T> clazz;

	protected final Logger logger = Logger.getLogger( getClass() );

	@Autowired
	private SessionFactory sessionFactory;

	public CommonDAOImpl(final Class<T> clazzToSet) {
		Preconditions.checkNotNull(clazzToSet);
		this.clazz = clazzToSet;
	}

	@SuppressWarnings("unchecked")
	public T findOne(final Long id) throws AppException {
		try {
			Preconditions.checkArgument(id != null);
			return (T) getCurrentSession().get(clazz, id);
		} catch (Exception e) {
			logger.error(createLogMessage(EnumMessages.ERRO_FIND_ONE), e);
			throw new AppException(EnumMessages.ERRO_FIND_ONE);
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() throws AppException {
		try {
			return getCurrentSession().createQuery("from " + clazz.getName())
					.list();
		} catch (Exception e) {
			logger.error(createLogMessage(EnumMessages.ERRO_FIND_ALL), e);
			throw new AppException(EnumMessages.ERRO_FIND_ALL);
		}
	}

	public T save(final T entity) throws AppException {
		try {
			Preconditions.checkNotNull(entity);
			getCurrentSession().persist(entity);
			return entity;
		} catch (ConstraintViolationException e) {
			logger.error(createLogMessage(EnumMessages.ERRO_DUPLICATE_SAVE), e);
			throw new AppException(EnumMessages.ERRO_DUPLICATE_SAVE);
		} catch (Exception e) {
			logger.error(createLogMessage(EnumMessages.ERRO_SAVE), e);
			throw new AppException(EnumMessages.ERRO_SAVE);
		}
	}

	public void update(final T entity) throws AppException {
		try {
			Preconditions.checkNotNull(entity);
			getCurrentSession().merge(entity);
		} catch (ConstraintViolationException e) {
			logger.error(createLogMessage(EnumMessages.ERRO_DUPLICATE_SAVE), e);
			throw new AppException(EnumMessages.ERRO_DUPLICATE_SAVE);
		} catch (Exception e) {
			logger.error(createLogMessage(EnumMessages.ERRO_UPDATE), e);
			throw new AppException(EnumMessages.ERRO_UPDATE);
		}
	}

	public void delete(final T entity) throws AppException {
		try {
			Preconditions.checkNotNull(entity);
			getCurrentSession().delete(entity);
		} catch (Exception e) {
			logger.error(createLogMessage(EnumMessages.ERRO_DELETE), e);
			throw new AppException(EnumMessages.ERRO_DELETE);
		}
	}

	public void deleteById(final Long entityId) throws AppException {
		try {
			final T entity = findOne(entityId);
			Preconditions.checkState(entity != null);
			delete(entity);
		} catch (Exception e) {
			logger.error(createLogMessage(EnumMessages.ERRO_DELETE_BY_ID), e);
			throw new AppException(EnumMessages.ERRO_DELETE_BY_ID);
		}
	}

	@SuppressWarnings("unchecked")
	public T findByName(String name) throws AppException {
		try {
			return (T) getCurrentSession().createCriteria(clazz)
					.add(Restrictions.eq("name", name)).uniqueResult();
		} catch (Exception e) {
			logger.error(createLogMessage(EnumMessages.ERRO_FIND_BY_NAME), e);
			throw new AppException(EnumMessages.ERRO_FIND_BY_NAME);
		}
	}

	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public static String createLogMessage(EnumMessages enumMessage) {
		return "DESCRICAO: " + enumMessage.getKey() + " - " + AttributesSession.resourceBundleMsgs.getString(enumMessage.getKey()) + "\r\n";
	}
}