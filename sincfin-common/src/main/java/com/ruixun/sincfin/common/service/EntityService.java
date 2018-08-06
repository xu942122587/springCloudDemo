package com.ruixun.sincfin.common.service;

public interface EntityService<T>  extends BaseService{

	/**
	 * 增删改查
	 * @param entity
	 * @return
	 */
	public void add(T entity);

	public void add(T entity,boolean selective);
	
	public void delete(T entity);
	
	public void update(T entity);

	public void update(T entity,boolean selective);
	
	public T findById(Long id);
}
