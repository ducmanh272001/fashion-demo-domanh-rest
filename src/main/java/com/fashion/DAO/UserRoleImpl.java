package com.fashion.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fashion.configFactory.ConfigFactory;
import com.fashion.entity.User;
import com.fashion.entity.UserRole;

public class UserRoleImpl implements ImplDao<UserRole> {

	private SessionFactory FACTORY;

	private UserRoleImpl() {
		FACTORY = ConfigFactory.getSessionFactory();
		if (FACTORY == null) {
			System.out.println("Khong co ket noi voi session factory");
		}
	}

	public static UserRoleImpl getNewUserRole() {
		return new UserRoleImpl();
	}

	@Override
	public List<UserRole> selectAll() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<UserRole> lst = ss.createQuery("from UserRole").list();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// tesst cais dang nhap
//	public UserRole getDangNhap(String username, String password) {
//		Session ss = FACTORY.openSession();
//		try {		
//			UserRole uok = new UserRole();
//			UserRole dn = (UserRole) ss.createQuery("from UserRole where userId = :idla and roleId = :rolela").setParameter("idla",uok.setUserId();).uniqueResult();
//		} catch (Exception e) {
//			ss.getTransaction().rollback();
//		} finally {
//			ss.close();
//		}
//	}

	@Override
	public UserRole selectById(int id) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			UserRole us = ss.get(UserRole.class, id);
			return us;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}
	
	///tìm id người dungf
	public UserRole selectByNguoiDung(int id) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			UserRole us = (UserRole) ss.createQuery("from UserRole where userId.id = :idla").setParameter("idla", id).getSingleResult();
			return us;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}
	

	@Override
	public boolean insert(UserRole t) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			ss.save(t);
			System.out.println("Ban da them is userRole la " + t.getId() + " vao co so du lieu");
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
	public boolean update(UserRole t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<UserRole> selectByName(String idtim) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int idxoa) {
		// TODO Auto-generated method stub
		return false;
	}

}
