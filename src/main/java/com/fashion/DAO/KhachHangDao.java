package com.fashion.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fashion.configFactory.ConfigFactory;
import com.fashion.entity.KhachHang;

public class KhachHangDao implements ImplDao<KhachHang> {

	public static KhachHangDao newKhachHang() {
		return new KhachHangDao();
	}

	private SessionFactory FACTORY;

	private KhachHangDao() {
		FACTORY = ConfigFactory.getSessionFactory();
		if (FACTORY == null) {
			System.out.println("Không có kết nối với Factory");
		}
	}

	@Override
	public List<KhachHang> selectAll() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<KhachHang> lst = ss.createQuery("from KhachHang").list();
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
	public KhachHang selectById(int id) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			KhachHang kh = ss.get(KhachHang.class, id);
			return kh;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Lấy ra số lượng khách hàng
	public Long selectCountKh() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Long> list = ss.createQuery("select count (*) from KhachHang").list();
			ss.getTransaction().commit();
			return list.get(0);
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	@Override
	public boolean insert(KhachHang t) {
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
	public boolean update(KhachHang t) {
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
	public List<KhachHang> selectByName(String idtim) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int idxoa) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			int idXOA = ss.createQuery("delete from KhachHang where id = :idxoa").setParameter("idxoa", idxoa)
					.executeUpdate();
			ss.getTransaction().commit();
			if (idXOA > 0) {
				System.out.println("Bạn đã xóa thành công");
				return true;
			} else {
				System.out.println("Không có cơ sở dữ liệu để xóa");
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

	// Phân trang khách hàng
	public List<KhachHang> phanTrangKhachHang(int sotrang) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<KhachHang> lst = ss.createQuery("from KhachHang").setMaxResults(8).setFirstResult((sotrang - 1) * 8).getResultList();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

}
