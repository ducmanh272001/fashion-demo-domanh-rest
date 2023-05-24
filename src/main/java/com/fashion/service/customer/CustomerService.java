package com.fashion.service.customer;

import java.util.List;

public interface CustomerService <T> {

	public List<T> selectAll();
	public T selectById(int id);
	public boolean insert(T t);
	public boolean update(T t);
	public List<T> selectByName(String idtim);
	public boolean delete(int idxoa);
}
