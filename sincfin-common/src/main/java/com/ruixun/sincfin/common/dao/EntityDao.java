package com.ruixun.sincfin.common.dao;

public interface EntityDao<T>  extends BaseDao{

	public int insert(T Entity);

    public int insertSelective(T Entity);
	
    public int deleteByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(T Entity);

    public int updateByPrimaryKey(T Entity);

    public T selectByPrimaryKey(Long id);

}
