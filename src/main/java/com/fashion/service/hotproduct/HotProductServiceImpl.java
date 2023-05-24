package com.fashion.service.hotproduct;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fashion.config.ConfigFactory;
import com.fashion.entity.HotProductEntity;
import com.fashion.entity.ProductNumberSell;

public class HotProductServiceImpl implements HotProductService<HotProductEntity> {

	public static HotProductServiceImpl newHotProduct() {
		return new HotProductServiceImpl();
	}

	private SessionFactory FACTORY;

	private HotProductServiceImpl() {
		FACTORY = ConfigFactory.getSessionFactory();
		if (FACTORY == null) {
			System.out.println("Không có kết nối với Factory");
		}
	}

	@Override
	public List<HotProductEntity> selectAll() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<HotProductEntity> lst = ss.createQuery("from HotProductEntity order by sellNumber desc")
					.setMaxResults(12).list();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	@Override
	public HotProductEntity selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(HotProductEntity t) {
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			ss.save(t);
			System.out.println("Bạn đã lưu id là " + t.getId() + " vào cơ sở dữ liệu");
			ss.getTransaction().commit();
			return true;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return false;
	}

	@Override
	public boolean update(HotProductEntity t) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			ss.update(t);
			System.out.println("Bạn đã sửa id là " + t.getId() + " vào cơ sở dữ liệu");
			ss.getTransaction().commit();
			return true;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return false;
	}

	@Override
	public List<HotProductEntity> selectByName(String idtim) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int idxoa) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public HotProductEntity findAllByIdsp(long id) {
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			HotProductEntity hotProductEntity = (HotProductEntity) ss
					.createQuery("FROM HotProductEntity WHERE idsp = :id").setParameter("id", id).getSingleResult();
			ss.getTransaction().commit();
			return hotProductEntity;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	@Override
	public List<Long> findByIdSpSelling() {
		List<Long> longIdSp = null;
		try (Session ss = FACTORY.openSession()) {
			ss.beginTransaction();
			longIdSp = ss.createQuery("SELECT hp.idsp FROM HotProductEntity hp ORDER BY hp.sellNumber DESC", Long.class)
					.setMaxResults(8).getResultList();
			ss.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn: " + e.getMessage());
		}
		return longIdSp;
	}

	@Override
	public List<Long> idSpNews() {
	    List<Long> spIds = null;
	    try (Session ss = FACTORY.openSession()) {
	        ss.beginTransaction();
	        spIds = ss.createQuery("SELECT hp.idsp " +
	                "FROM HotProductEntity hp " +
	                "GROUP BY hp.idsp " +
	                "ORDER BY SUM(hp.sellNumber) DESC", Long.class)
	                .setMaxResults(4)
	                .getResultList();
	        ss.getTransaction().commit();
	    } catch (Exception e) {
	        System.out.println("Lỗi Truy Vấn: " + e.getMessage());
	    }
	    return spIds;
	}



}
