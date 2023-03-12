package com.fashion.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fashion.configFactory.ConfigFactory;
import com.fashion.entity.TinTuc;

public class TinTucIml implements ImplDao<TinTuc> {

	public static TinTucIml getNewTinTuc() {
		return new TinTucIml();
	}

	private SessionFactory FACTORY;

	private TinTucIml() {
		FACTORY = ConfigFactory.getSessionFactory();
		if (FACTORY == null) {
			System.out.println("Lỗi Không có kết nối với biến SessionFactory");
		}
	}

	@Override
	public List<TinTuc> selectAll() {
		// Lấy list tin tức
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<TinTuc> list = ss.createQuery("from TinTuc").list();
			ss.close();
			return list;
		} catch (Exception e) {
			System.out.println("Xảy ra lỗi ngoại lệ");
			ss.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public TinTuc selectById(int id) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			TinTuc tintuc = ss.get(TinTuc.class, id);
			return tintuc;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	@Override
	public boolean insert(TinTuc t) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			ss.save(t);
			System.out.println("Bạn đã thêm id là " + t.getId() + " vào cơ sở dữ liệu");
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
	public boolean update(TinTuc t) {
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
	public List<TinTuc> selectByName(String idtim) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int idxoa) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			// Xóa tin tức
			TinTuc tintuc = ss.get(TinTuc.class, idxoa);
			ss.delete(tintuc);
			ss.getTransaction().commit();
			System.out.println("Bạn đã xóa id là " + idxoa + " vào cơ sở dữ liệu");
			return true;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return false;
	}

	// Lấy số lượng tin tức
	public Long countTinTuc() {
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Long> soluong =  ss.createQuery("select count(*) from TinTuc").list();
			return soluong.get(0);
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

}
