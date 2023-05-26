package com.fashion.service.branch;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fashion.config.ConfigFactory;
import com.fashion.entity.BranchEntity;
import com.fashion.entity.TypeProductEntity;

public class BranchServiceImpl implements BranchService<BranchEntity> {

	private SessionFactory FACTORY;

	public static BranchServiceImpl getNewBranchEntity() {
		return new BranchServiceImpl();
	}

	private BranchServiceImpl() {
		FACTORY = ConfigFactory.getSessionFactory();
		if (FACTORY == null) {
			System.out.println("Không có kết nối với biến FACTORY");
		}
	}

	@Override
	public List<BranchEntity> selectAll() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<BranchEntity> lst = ss.createQuery("from BranchEntity").list();
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
	public BranchEntity selectById(int id) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			BranchEntity nh = (BranchEntity) ss.createQuery("from BranchEntity where id = :idla")
					.setParameter("idla", id).uniqueResult();
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
	public boolean insert(BranchEntity t) {
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
	public boolean update(BranchEntity t) {
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
	public List<BranchEntity> selectByName(String idtim) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<BranchEntity> lst = ss.createQuery("from BranchEntity where name_brand = :name")
					.setParameter("name", idtim).list();
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
			BranchEntity nh = ss.get(BranchEntity.class, idxoa);
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

	// Phân trang
	public List<BranchEntity> phanTrang(int sotrang) {
		// mỞ sesion ra
		Session ss = FACTORY.openSession();
		try {
			// Bắt đầu 1 phiên làm việc
			ss.beginTransaction();
			// Vì là lấy ra dữ liệu nên sẽ ko cần commit
			List<BranchEntity> lst = ss.createQuery("from BranchEntity order by id desc").setMaxResults(8)
					.setFirstResult((sotrang - 1) * 8).getResultList();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi câu lệnh truy vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Số lượng danh mục
	public Long selectCount() {
		// Bắt đầu 1 phiên làm việc
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			// Vì trả về 1 cái nên không cần commit
			List<Long> list = ss.createQuery("select count(*) from BranchEntity").list();
			return list.get(0);
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Lỗi câu lệnh try vấn");
		} finally {
			ss.close();
		}
		return null;
	}

}
