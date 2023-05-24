package com.fashion.service.bill;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fashion.config.ConfigFactory;
import com.fashion.entity.BillEntity;

public class BillServiceImpl implements BillService<BillEntity> {

	public static BillServiceImpl getNewHoaDon() {
		return new BillServiceImpl();
	}

	private SessionFactory FACTORY;

	private BillServiceImpl() {
		FACTORY = ConfigFactory.getSessionFactory();
		if (FACTORY == null) {
			System.out.println("Không có kết nối với biến Factory");
		}
	}

	@Override
	public List<BillEntity> selectAll() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<BillEntity> lst = ss.createQuery("from BillEntity order by id DESC").list();
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
	public BillEntity selectById(int id) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			BillEntity hd = ss.get(BillEntity.class, id);
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
	public boolean insert(BillEntity t) {
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
	public boolean update(BillEntity t) {
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
	public List<BillEntity> selectByIdKh(int id) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<BillEntity> lst = ss.createQuery("from BillEntity where makh.id = :idla").setParameter("idla", id).list();
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
	public List<BillEntity> selectByName(String idtim) {
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
			List<Long> list = ss.createQuery("select count (*) from BillEntity").list();
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
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			int idXOA = ss.createQuery("delete from BillEntity where id = :idxoa").setParameter("idxoa", idxoa)
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

	// Phân trang list hóa đơn
	public List<BillEntity> phanTrangHoaDon(int pageNumber) {
		  Session ss = FACTORY.openSession();
		    try {
		        ss.beginTransaction();
		        List<BillEntity> lst = ss.createQuery("from BillEntity order by id desc")
		                                .setFirstResult((pageNumber - 1) * 8)
		                                .setMaxResults(8)
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
