package com.fashion.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fashion.configFactory.ConfigFactory;
import com.fashion.entity.HoaDon;
import com.fashion.entity.SanPhamChiTiet;

public class HoaDonImpl implements ImplDao<HoaDon> {

	public static HoaDonImpl getNewHoaDon() {
		return new HoaDonImpl();
	}

	private SessionFactory FACTORY;

	private HoaDonImpl() {
		FACTORY = ConfigFactory.getSessionFactory();
		if (FACTORY == null) {
			System.out.println("Không có kết nối với biến Factory");
		}
	}

	@Override
	public List<HoaDon> selectAll() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<HoaDon> lst = ss.createQuery("from HoaDon").list();
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
	public HoaDon selectById(int id) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			HoaDon hd = ss.get(HoaDon.class, id);
			return hd;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	@Override
	public boolean insert(HoaDon t) {
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
	public boolean update(HoaDon t) {
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

	// Lọc theo id
	public List<HoaDon> selectByIdKh(int id) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<HoaDon> lst = ss.createQuery("from HoaDon where makh.id = :idla").setParameter("idla", id).list();
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
	public List<HoaDon> selectByName(String idtim) {
		// TODO Auto-generated method stub
		return null;
	}

	// Lấy số lượng đơn hàng
	public Long selectCountHoaDon() {
		// Mở 1 biến Session
		Session ss = FACTORY.openSession();
		try {
			// Bắt đầu 1 phiên làm việc
			ss.beginTransaction();
			List<Long> list = ss.createQuery("select count (*) from HoaDon").list();
			return list.get(0);
		} catch (Exception e) {
			System.out.println("Không có kết nố với Biến Factory");
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
			int idXoa = ss.createQuery("delete from KhachHang where id = :idla").setParameter("idla", idxoa)
					.executeUpdate();
			ss.getTransaction().commit();
			if (idXoa > 1) {
				System.out.println("Xóa thành công");
				return true;
			} else {
				System.out.println("Xóa không thành công");
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

	// Phân trang list hóa đơn
	public List<HoaDon> phanTrangHoaDon(int sotrang) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<HoaDon> lst = ss.createQuery("from HoaDon").setMaxResults(8).setFirstResult((sotrang - 1) * 8)
					.getResultList();
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
