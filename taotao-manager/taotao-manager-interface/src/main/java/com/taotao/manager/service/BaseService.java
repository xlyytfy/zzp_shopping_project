package com.taotao.manager.service;

import java.util.List;

public interface BaseService<T> {
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public T queryById(Long id);
	
	/**
	 * 获取所有数据
	 * @return
	 */
	public List<T> queryAll();
	
	/**
	 * 条件查询
	 * @return
	 */
	public int queryCountByWhere(T t);
	
	/**
	 * 条件列表查询多个
	 * @return
	 */
	public List<T> queryListByWhere(T t);
	
	/**
	 * 分页获取数据
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<T> queryByPage(Integer page,Integer rows);
	
	/**
	 * 根据条件获取某一个对象
	 * @param t
	 * @return
	 */
	public T queryOne(T t);
	
	/**
	 * 保存对象
	 * @param t
	 */
	public void save(T t);
	
	/**
	 * 不保存null
	 * @param t
	 */
	public void saveSelective(T t);
	
	/**
	 * 根据ID更新数据
	 * @param id
	 */
	public void updateById(T t);
	
	/**
	 * 根据id更新,不更新null
	 * @param id
	 */
	public void updateByIdSelective(T t);
	
	/**
	 * 根据ID删除数据
	 * @param id
	 */
	public void deleteById(Long id);
	
	/**
	 * 根据id集合删除多个数据
	 * @param ids
	 */
	public void deleteByIds(List<Object> ids);
}
