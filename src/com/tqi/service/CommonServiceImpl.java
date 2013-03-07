package com.tqi.service;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.tqi.dao.CommonDAO;
import com.tqi.util.exceptions.AppException;

@Component
public abstract class CommonServiceImpl<T extends Serializable> implements CommonService<T> {
	
	protected final Logger logger = Logger.getLogger( getClass() );

	@SuppressWarnings("unused")
	private Class< T > clazz;

	@Autowired 
	protected ApplicationEventPublisher eventPublisher;

	public CommonServiceImpl() {}
	
	public CommonServiceImpl( final Class< T > clazzToSet ) {
		super();

		clazz = clazzToSet;
	}

	// find

	@Transactional(readOnly = true)
	public T findOne( final long id ) throws AppException {
		return getDao().findOne( id );
	}

	@Transactional(readOnly = true)
	public List< T > findAll() throws AppException {
		return Lists.newArrayList( getDao().findAll() );
	}

	// save/create/persist

	@Transactional(rollbackFor={AppException.class})
	public T save( final T entity ) throws AppException {
		Preconditions.checkNotNull( entity );

		final T persistedEntity = getDao().save( entity );
		
		return persistedEntity;
	}

	// update/merge

	@Transactional(rollbackFor={AppException.class})
	public void update( final T entity ) throws AppException {
		Preconditions.checkNotNull( entity );

		getDao().update( entity );
	}

	@Transactional(rollbackFor={AppException.class})
	public void delete( final long id ) throws AppException {
		final T entity = getDao().findOne( id );
		getDao().delete( entity );
	}
	
	@Transactional(rollbackFor={AppException.class})
	public void delete( final T entity ) throws AppException {
		Preconditions.checkNotNull( entity );
		getDao().delete( entity );
	}
	
	@Transactional(readOnly=true)
	public T findByName(String name) throws AppException {
		return getDao().findByName(name);
	}

	// template method

	protected abstract CommonDAO<T> getDao() throws AppException;
}