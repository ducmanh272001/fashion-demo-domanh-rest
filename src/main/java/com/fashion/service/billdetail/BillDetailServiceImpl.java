package com.fashion.service.billdetail;

import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fashion.config.ConfigFactory;
import com.fashion.entity.BillDetailEntity;

public class BillDetailServiceImpl implements BillDetailService<BillDetailEntity> {

	public static BillDetailServiceImpl getNewBillDetailEntity() {
		return new BillDetailServiceImpl();
	}

	private SessionFactory FACTORY;

	private BillDetailServiceImpl() {
		FACTORY = ConfigFactory.getSessionFactory();
		if (FACTORY == null) {
			System.out.println("Không có kết nối với biến Factory");
		}
	}

	@Override
	public List<BillDetailEntity> selectAll() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<BillDetailEntity> lst = ss.createQuery("from BillDetailEntity").list();
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
	public BillDetailEntity selectById(int id) {
//		// Mở 1 biến session
//		Session ss = FACTORY.openSession();
//		try {
//			ss.beginTransaction();
//			BillDetailEntity hd = ss.get(BillDetailEntity.class, id);
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
	public boolean insert(BillDetailEntity t) {
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
	public boolean update(BillDetailEntity t) {
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
	public List<BillDetailEntity> selectByName(String idtim) {
		// TODO Auto-generated method stub
		return null;
	}

	// Lọc hóa đơn chi tiết
	public List<BillDetailEntity> selectByIdHd(int id) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<BillDetailEntity> lst = ss.createQuery("from BillDetailEntity where id_hoadon.id = :idla")
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
			int idXoa = ss.createQuery("delete from CustomerEntity where id = :idla").setParameter("idla", idxoa)
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
			int idXoa = ss.createQuery("delete from BillDetailEntity where id_sp.id = :idla").setParameter("idla", idla)
					.executeUpdate();
			ss.getTransaction().commit();
			if (idXoa > 1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn" + e.getMessage());
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return false;
	}

	@Override

	public List<BillDetailEntity> findAllByIdIn(Collection<Long> collections) {
		Session session = FACTORY.openSession();
		try {
			session.beginTransaction();
			List<BillDetailEntity> lst = session.createQuery("FROM BillDetailEntity WHERE id IN :ids")
					.setParameterList("ids", collections).list();
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
	public boolean deleteAllByBillId(Integer id) {
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			int idXoa = ss.createQuery("delete from BillDetailEntity where id_hoadon.id = :idla").setParameter("idla", id)
					.executeUpdate();
			ss.getTransaction().commit();
			if (idXoa > 1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn" + e.getMessage());
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return false;
	}
}
