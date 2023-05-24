package com.fashion.service.calculate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.fashion.config.ConfigFactory;
import com.fashion.entity.BillDetailEntity;
import com.fashion.entity.BranchEntity;
import com.fashion.entity.CalculateEntity;
import com.fashion.entity.ColorEntity;

public class CalculateServiceImpl implements CalculateService<CalculateEntity> {

	public static CalculateServiceImpl getCalculateServiceImpl() {
		return new CalculateServiceImpl();
	}

	private SessionFactory FACTORY;

	private CalculateServiceImpl() {
		FACTORY = ConfigFactory.getSessionFactory();
		if (FACTORY == null) {
			System.out.println("Không có kết nối với biến Factory");
		}
	}

	@Override
	public boolean save(CalculateEntity entity) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			ss.save(entity);
			System.out.println("Bạn đã lưu id là " + entity.getId() + " vào cơ sở dữ liệu");
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
	public boolean delete(Integer id) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			int idXOA = ss.createQuery("delete from CalculateEntity where id = :idxoa").setParameter("idxoa", id)
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

	@Override
	public List<CalculateEntity> findAllByProductId(Integer id) {
		Session session = FACTORY.openSession();
		try {
			session.beginTransaction();
			List<CalculateEntity> lst = session.createQuery("FROM CalculateEntity WHERE productId = :id")
					.setParameter("id", id).getResultList();
			session.getTransaction().commit();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public long getQuantityFromProductId(Integer id) {
		Session session = FACTORY.openSession();
		try {
			session.beginTransaction();
			Query<Long> query = session.createQuery(
					"SELECT SUM(entity.quantity) FROM CalculateEntity entity WHERE entity.productId = :id");
			query.setParameter("id", id);
			Long sum = query.uniqueResult();
			session.getTransaction().commit();
			return sum != null ? sum : 0;
		} catch (Exception e) {
			System.out.println("Xảy ra lỗi truy vấn" + e.getMessage());
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return 0;
	}

	@Override
	public boolean deleteAllFindProductId(Integer productId) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			ss.createQuery("delete from CalculateEntity where productId = :idxoa").setParameter("idxoa", productId)
					.executeUpdate();
			System.out.println("Bạn đã xóa id là " + productId + " ra khỏi cơ sở dữ liệu");
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
	public boolean deleteAll() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			ss.createQuery("delete from CalculateEntity")
					.executeUpdate();
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
