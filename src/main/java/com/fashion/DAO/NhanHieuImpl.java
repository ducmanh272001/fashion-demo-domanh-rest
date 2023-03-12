package com.fashion.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fashion.configFactory.ConfigFactory;
import com.fashion.entity.NhanHieu;

public class NhanHieuImpl implements ImplDao<NhanHieu> {

	private SessionFactory FACTORY;

	public static NhanHieuImpl getNewNhanHieu() {
		return new NhanHieuImpl();
	}

	private NhanHieuImpl() {
		FACTORY = ConfigFactory.getSessionFactory();
		if (FACTORY == null) {
			System.out.println("Không có kết nối với biến FACTORY");
		}
	}

	@Override
	public List<NhanHieu> selectAll() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<NhanHieu> lst = ss.createQuery("from NhanHieu").list();
			return lst;
		} catch (Exception e) {
			System.out.println("Xảy ra lỗi câu lệnh truy vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	@Override
	public NhanHieu selectById(int id) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			NhanHieu nh = (NhanHieu) ss.createQuery("from NhanHieu where id = :idla").setParameter("idla", id)
					.uniqueResult();
			return nh;
		} catch (Exception e) {
			System.out.println("Xảy ra lỗi câu lệnh truy vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	@Override
	public boolean insert(NhanHieu t) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			ss.save(t);
			System.out.println("Bạn đã thêm id là " + t.getId() + " vào cơ sở dữ liệu");
			ss.getTransaction().commit();
			return true;
		} catch (Exception e) {
			System.out.println("Xảy ra lỗi câu lệnh truy vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return false;
	}

	@Override
	public boolean update(NhanHieu t) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			ss.update(t);
			System.out.println("Bạn đã sửa id là " + t.getId() + " vào cơ sở dữ liệu");
			ss.getTransaction().commit();
			return true;
		} catch (Exception e) {
			System.out.println("Xảy ra lỗi câu lệnh truy vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return false;
	}

	@Override
	public List<NhanHieu> selectByName(String idtim) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<NhanHieu> lst = ss.createQuery("from NhanHieu where name_brand = :name").setParameter("name", idtim).list();
			return lst;
		} catch (Exception e) {
			System.out.println("Xảy ra lỗi câu lệnh truy vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	@Override
	public boolean delete(int idxoa) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			NhanHieu nh = ss.get(NhanHieu.class, idxoa);
			ss.delete(nh);
			System.out.println("Bạn đã xóa id là " + idxoa + " ra khỏi cơ sở dữ liệu");
			ss.getTransaction().commit();
			return true;
		} catch (Exception e) {
			System.out.println("Xảy ra lỗi câu lệnh truy vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return false;
	}

}
