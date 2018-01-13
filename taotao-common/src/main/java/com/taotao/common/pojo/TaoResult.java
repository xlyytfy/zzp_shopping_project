package com.taotao.common.pojo;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @author zzp
 * 分页工具类
 * @param <T> 分页数据泛型
 */
public class TaoResult<T> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long total;//总数据数目
	
	private List<T> rows;//返回列表数据
	
	public TaoResult() {
		
	}
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
}	
