package com.fashion.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fashion.configFactory.ConfigFactory;
import com.fashion.entity.Hinhanh;
import com.fashion.entity.HoaDon;

public class HinhanhImpl implements ImplDao<Hinhanh> {

	public static HinhanhImpl getNewHinhAnh() {
		return new HinhanhImpl();
	}

	private SessionFactory FACTORY;

	private HinhanhImpl() {
		FACTORY = ConfigFactory.getSessionFactory();
		if (FACTORY == null) {
			System.out.println("Không có kết nối với biến Factory");
		}
	}

	@Override
	public List<Hinhanh> selectAll() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Hinhanh> lst = ss.createQuery("from Hinhanh").list();
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
	public Hinhanh selectById(int id) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			Hinhanh ha = ss.get(Hinhanh.class, id);
			return ha;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	@Override
	public boolean insert(Hinhanh t) {
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
	public boolean update(Hinhanh t) {
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
	public List<Hinhanh> selectByName(String idtim) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int idxoa) {
		// TODO Auto-generated method stub
		return false;
	}

	// Tìm list hình ảnh theo id của sản phẩm
	public List<Hinhanh> selectByIdSanPham(int idsp) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Hinhanh> lst = ss.createQuery("from Hinhanh where idhinhanh.id = :idla").setParameter("idla", idsp)
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
	// lấy 2 hình ảnh bằng câu lệnh sql

	public List<Hinhanh> selectHinhAnh2() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Hinhanh> lst = ss.createQuery("from Hinhanh").setMaxResults(2).getResultList();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Xóa hình ảnh theo id của sản phẩm
	public boolean deleteByIdSanPham(int idsp) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			int xoatc = ss.createQuery("delete from Hinhanh where idhinhanh.id = :idla").setParameter("idla", idsp)
					.executeUpdate();
			if (xoatc > 0) {
				System.out.println("Xóa thành công !");
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

}
