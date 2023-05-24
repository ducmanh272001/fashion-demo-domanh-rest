package com.fashion.service.hotproduct;

import java.util.List;

public interface HotProductService<T> {
	
	public List<T> selectAll();
	public T selectById(int id);
	public boolean insert(T t);
	public boolean update(T t);
	public List<T> selectByName(String idtim);
	public boolean delete(int idxoa);
	public List<Long> idSpNews();
	public T findAllByIdsp(long id);
	public List<Long> findByIdSpSelling();

}
