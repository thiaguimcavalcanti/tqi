package com.tqi.service;

import java.io.Serializable;
import java.util.List;

import com.tqi.util.exceptions.AppException;

public interface CommonService<T extends Serializable> {

	public T findOne( final long id ) throws AppException;

	public List< T > findAll() throws AppException;

	public T save( final T entity ) throws AppException;

	public void update( final T entity ) throws AppException;

	public void delete( final long id ) throws AppException;
	
	public void delete( final T entity ) throws AppException;
	
	public T findByName(String name) throws AppException;
}