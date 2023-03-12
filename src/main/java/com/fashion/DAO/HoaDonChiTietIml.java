package com.fashion.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fashion.configFactory.ConfigFactory;
import com.fashion.entity.HoaDon;
import com.fashion.entity.HoaDonChiTiet;
import com.fashion.entity.SanPhamChiTiet;

public class HoaDonChiTietIml implements ImplDao<HoaDonChiTiet> {

	public static HoaDonChiTietIml getNewHoaDonChiTiet() {
		return new HoaDonChiTietIml();
	}

	private SessionFactory FACTORY;

	private HoaDonChiTietIml() {
		FACTORY = ConfigFactory.getSessionFactory();
		if (FACTORY == null) {
			System.out.println("Không có kết nối với biến Factory");
		}
	}

	@Override
	public List<HoaDonChiTiet> selectAll() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<HoaDonChiTiet> lst = ss.createQuery("from HoaDonChiTiet").list();
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
	public HoaDonChiTiet selectById(int id) {
//		// Mở 1 biến session
//		Session ss = FACTORY.openSession();
//		try {
//			ss.beginTransaction();
//			HoaDonChiTiet hd = ss.get(HoaDonChiTiet.class, id);
//			return hd;
//		} catch (Exception e) {
//			System.out.println("Lỗi Truy Vấn");
//			ss.getTransaction().rollback();
//		} finally {
//			ss.close();
//		}
		return null;
	}

	@Override
	public boolean insert(HoaDonChiTiet t) {
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
	public boolean update(HoaDonChiTiet t) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			ss.update(t);
			System.out.println("Bạn đã sửa id là " + " vào cơ sở dữ liệu");
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
	public List<HoaDonChiTiet> selectByName(String idtim) {
		// TODO Auto-generated method stub
		return null;
	}

	// Lọc hóa đơn chi tiết
	public List<HoaDonChiTiet> selectByIdHd(int id) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<HoaDonChiTiet> lst = ss.createQuery("from HoaDonChiTiet where id_hoadon.id = :idla")
					.setParameter("idla", id).list();
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
	public boolean delete(int idxoa) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			int idXoa = ss.createQuery("delete from KhachHang where id = :idla").setParameter("idla", idxoa)
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

	// Xóa theo mã sản phẩm chi tiết
	public boolean deleteIdSanPhamCT(int idla) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			int idXoa = ss.createQuery("delete from HoaDonChiTiet where id_sp.id = :idla")
					.setParameter("idla", idla).executeUpdate();
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

}
