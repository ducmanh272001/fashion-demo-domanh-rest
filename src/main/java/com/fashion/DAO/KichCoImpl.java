package com.fashion.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fashion.configFactory.ConfigFactory;
import com.fashion.entity.Kichco;

public class KichCoImpl implements ImplDao<Kichco> {

	public static KichCoImpl getNewKichCo() {
		return new KichCoImpl();
	}

	private SessionFactory FACTORY;

	private KichCoImpl() {
		FACTORY = ConfigFactory.getSessionFactory();
		if (FACTORY == null) {
			System.out.println("Không có kết nối với biến SessionFactory");
		}
	}

	@Override
	public List<Kichco> selectAll() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Kichco> lst = ss.createQuery("from Kichco").list();
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
	public Kichco selectById(int id) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			Kichco kc = ss.get(Kichco.class, id);
			return kc;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	@Override
	public boolean insert(Kichco t) {
		// Mở 1 biến session
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
	public boolean update(Kichco t) {
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
	public List<Kichco> selectByName(String idtim) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int idxoa) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			int idXoa = ss.createQuery("delete from Kichco where id = :idla").setParameter("idla", idxoa)
					.executeUpdate();
			System.out.println("Bạn đã sửa id là " + idxoa + " vào cơ sở dữ liệu");
			ss.getTransaction().commit();
			if (idXoa > 1) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return false;
	}
	
	
	public Kichco findByName(String idtim) {
		// mỞ Session ra
		Session ss = FACTORY.openSession();
		try {
			// Bắt đầu 1 phiên làm việc
			ss.beginTransaction();
			Kichco ks = (Kichco) ss.createQuery("from Kichco where name = :idla").setParameter("idla", idtim).uniqueResult();
			ss.getTransaction().commit();
            return ks;
		} catch (Exception e) {
			System.out.println("Xảy ra lỗi truy vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

}
