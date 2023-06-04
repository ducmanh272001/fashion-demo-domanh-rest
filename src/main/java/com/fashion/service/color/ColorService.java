package com.fashion.service.color;

import java.util.List;

public interface ColorService <T> {

	public List<T> selectAll();
	public T selectById(int id);
	public boolean insert(T t);
	public boolean update(T t);
	public List<T> selectByName(String idtim);
	public boolean delete(int idxoa);
	public List<T> pageSize(int pageNumber);
	public Long count();
}
