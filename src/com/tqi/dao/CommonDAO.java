package com.tqi.dao;

import java.io.Serializable;
import java.util.List;

import com.tqi.util.exceptions.AppException;

public interface CommonDAO<T extends Serializable> {

	public T findOne( final Long id ) throws AppException;
	
	public List<T> findAll() throws AppException;
   
	public T save( final T entity ) throws AppException;
   
	public void update( final T entity ) throws AppException;
   
	public void delete( final T entity ) throws AppException;
   
	public void deleteById( final Long entityId ) throws AppException;
	
	public T findByName(String name) throws AppException;
}
