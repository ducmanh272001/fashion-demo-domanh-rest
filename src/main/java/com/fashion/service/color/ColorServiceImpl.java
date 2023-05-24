package com.fashion.service.color;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fashion.config.ConfigFactory;
import com.fashion.entity.ColorEntity;

public class ColorServiceImpl implements ColorService<ColorEntity> {

	public static ColorServiceImpl getNewColorEntity() {
		return new ColorServiceImpl();
	}

	private SessionFactory FACTORY;

	private ColorServiceImpl() {
		FACTORY = ConfigFactory.getSessionFactory();
		if (FACTORY == null) {
			System.out.println("Không có biến session factory");
		}
	}

	@Override
	public List<ColorEntity> selectAll() {
		// mỞ Session ra
		Session ss = FACTORY.openSession();
		try {
			// Bắt đầu 1 phiên làm việc
			ss.beginTransaction();
			List<ColorEntity> lst = ss.createQuery("from ColorEntity").list();
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
	public ColorEntity selectById(int id) {
		// mỞ Session ra
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			ColorEntity ms = ss.get(ColorEntity.class, id);
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
	public boolean insert(ColorEntity t) {
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
	public boolean update(ColorEntity t) {
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
	public List<ColorEntity> selectByName(String idtim) {
		// TODO Auto-generated method stub
		return null;
	}

	public ColorEntity findByName(String idtim) {
		// mỞ Session ra
		Session ss = FACTORY.openSession();
		try {
			// Bắt đầu 1 phiên làm việc
			ss.beginTransaction();
			ColorEntity ms = (ColorEntity) ss.createQuery("from ColorEntity where name = :idla").setParameter("idla", idtim).uniqueResult();
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
			int xoaOk = ss.createQuery("delete from ColorEntity where id = :idla").setParameter("idla", idxoa)
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
