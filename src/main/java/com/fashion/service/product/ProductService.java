package com.fashion.service.product;

import java.util.List;

public interface ProductService <T> {

	public List<T> selectAll();
	public T selectById(int id);
	public boolean insert(T t);
	public boolean update(T t);
	
	public List<T> selectByName(String idtim);
	public boolean delete(int idxoa);
}
