package com.fashion.service.billdetail;

import java.util.Collection;
import java.util.List;

public interface BillDetailService <T> {

	public List<T> selectAll();
	public T selectById(int id);
	public boolean insert(T t);
	public boolean update(T t);
	public List<T> selectByName(String idtim);
	public boolean delete(int idxoa);
	public List<T> findAllByIdIn(Collection<Long> collections);
	public boolean deleteAllByBillId(Integer id);
}
