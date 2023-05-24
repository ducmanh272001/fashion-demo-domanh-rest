package com.fashion.service.calculate;

import java.util.List;

public interface CalculateService<T> {
	public boolean save(T entity);
	public boolean delete(Integer id);
    public List<T> findAllByProductId(Integer id);
    public long getQuantityFromProductId(Integer id);
    public boolean deleteAllFindProductId(Integer productId);
    public boolean deleteAll();
}
