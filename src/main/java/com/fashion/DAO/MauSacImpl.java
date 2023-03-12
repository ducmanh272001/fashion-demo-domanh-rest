package com.fashion.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fashion.configFactory.ConfigFactory;
import com.fashion.entity.Mausac;

public class MauSacImpl implements ImplDao<Mausac> {

	public static MauSacImpl getNewMauSac() {
		return new MauSacImpl();
	}

	private SessionFactory FACTORY;

	private MauSacImpl() {
		FACTORY = ConfigFactory.getSessionFactory();
		if (FACTORY == null) {
			System.out.println("Không có biến session factory");
		}
	}

	@Override
	public List<Mausac> selectAll() {
		// mỞ Session ra
		Session ss = FACTORY.openSession();
		try {
			// Bắt đầu 1 phiên làm việc
			ss.beginTransaction();
			List<Mausac> lst = ss.createQuery("from Mausac").list();
			return lst;
		} catch (Exception e) {
			System.out.println("Xảy ra lỗi truy vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	@Override
	public Mausac selectById(int id) {
		// mỞ Session ra
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			Mausac ms = ss.get(Mausac.class, id);
			return ms;
		} catch (Exception e) {
			System.out.println("Xảy ra lỗi truy vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	@Override
	public boolean insert(Mausac t) {
		// mỞ Session ra
		Session ss = FACTORY.openSession();
		try {
			// Bắt đầu 1 phiên làm việc
			ss.beginTransaction();
			ss.save(t);
			System.out.println("Bạn đã thêm id là " + t.getId() + " vào cơ sở dữ liệu!");
			ss.getTransaction().commit();
			return true;
		} catch (Exception e) {
			System.out.println("Xảy ra lỗi truy vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return false;
	}

	@Override
	public boolean update(Mausac t) {
		// mỞ Session ra
		Session ss = FACTORY.openSession();
		try {
			// Bắt đầu 1 phiên làm việc
			ss.beginTransaction();
			ss.update(t);
			System.out.println("Bạn đã sửa id là " + t.getId() + " vào cơ sở dữ liệu!");
			ss.getTransaction().commit();
			return true;
		} catch (Exception e) {
			System.out.println("Xảy ra lỗi truy vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return false;
	}

	@Override
	public List<Mausac> selectByName(String idtim) {
		// TODO Auto-generated method stub
		return null;
	}

	public Mausac findByName(String idtim) {
		// mỞ Session ra
		Session ss = FACTORY.openSession();
		try {
			// Bắt đầu 1 phiên làm việc
			ss.beginTransaction();
			Mausac ms = (Mausac) ss.createQuery("from Mausac where name = :idla").setParameter("idla", idtim).uniqueResult();
			ss.getTransaction().commit();
            return ms;
		} catch (Exception e) {
			System.out.println("Xảy ra lỗi truy vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	@Override
	public boolean delete(int idxoa) {
		// mỞ Session ra
		Session ss = FACTORY.openSession();
		try {
			// Bắt đầu 1 phiên làm việc
			ss.beginTransaction();
			int xoaOk = ss.createQuery("delete from Mausac where id = :idla").setParameter("idla", idxoa)
					.executeUpdate();
			System.out.println("Bạn đã xóa id là " + idxoa + " ra khỏi cơ sở dữ liệu!");
			ss.getTransaction().commit();
			if (xoaOk > 0) {
				System.out.println("Xóa thành công");
				return true;
			} else {
				System.out.println("Xóa thất bại");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Xảy ra lỗi truy vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return false;
	}

}
