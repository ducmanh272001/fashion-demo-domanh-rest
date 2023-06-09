package com.fashion.service.user;

import java.util.List;

public interface UserService <T> {

	public List<T> selectAll();
	public T selectById(int id);
	public boolean insert(T t);
	public boolean update(T t);
	public List<T> selectByName(String idtim);
	public boolean delete(int idxoa);
	public T findByEmail(String email);
	public T save(T t);
}
