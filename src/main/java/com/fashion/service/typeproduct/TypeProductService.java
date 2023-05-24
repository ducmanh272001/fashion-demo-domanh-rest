package com.fashion.service.typeproduct;

import java.util.List;

public interface TypeProductService <T> {

	public List<T> selectAll();
	public T selectById(int id);
	public boolean insert(T t);
	public boolean update(T t);
	public List<T> selectByName(String idtim);
	public boolean delete(int idxoa);
}
