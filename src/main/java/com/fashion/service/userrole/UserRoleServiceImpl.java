package com.fashion.service.userrole;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fashion.config.ConfigFactory;
import com.fashion.entity.UserEntity;
import com.fashion.entity.UserRoleEntity;


public class UserRoleServiceImpl implements UserRoleService<UserRoleEntity> {

	private SessionFactory FACTORY;

	private UserRoleServiceImpl() {
		FACTORY = ConfigFactory.getSessionFactory();
		if (FACTORY == null) {
			System.out.println("Khong co ket noi voi session factory");
		}
	}

	public static UserRoleServiceImpl getNewUserRole() {
		return new UserRoleServiceImpl();
	}

	@Override
	public List<UserRoleEntity> selectAll() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<UserRoleEntity> lst = ss.createQuery("from UserRoleEntity").list();
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
	public UserRoleEntity selectById(int id) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			UserRoleEntity us = ss.get(UserRoleEntity.class, id);
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
	public UserRoleEntity selectByNguoiDung(int id) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			UserRoleEntity us = (UserRoleEntity) ss.createQuery("from UserRoleEntity where userId.id = :idla").setParameter("idla", id).getSingleResult();
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
	public boolean insert(UserRoleEntity t) {
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
	public boolean update(UserRoleEntity t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<UserRoleEntity> selectByName(String idtim) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int idxoa) {
		// TODO Auto-generated method stub
		return false;
	}

}
