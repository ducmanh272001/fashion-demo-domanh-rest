package com.fashion.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fashion.configFactory.ConfigFactory;
import com.fashion.entity.User;

public class UserImpl implements ImplDao<User> {

	private SessionFactory FACTORY;

	private UserImpl() {
		FACTORY = ConfigFactory.getSessionFactory();
		if (FACTORY == null) {
			System.out.println("Khong co ket noi voi session factory");
		}
	}

	public static UserImpl getNewUser() {
		return new UserImpl();
	}

	@Override
	public List<User> selectAll() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<User> lst = ss.createQuery("from User").list();
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
	public User selectById(int id) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			User user = ss.get(User.class, id);
			return user;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}
	
	
	
	public User selectByUserName(String name) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			User user = (User) ss.createQuery("from User where name = :namela").setParameter("namela", name).uniqueResult();
			return user;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}
	
	
	//logi form dang nhap
	public User selectByDangNhap(String username, String password) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			User user = (User) ss.createQuery("from User where name = :namela and password = :pass").setParameter("namela", username)
					.setParameter("pass", password).uniqueResult();
			return user;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	
	
	public User selectByPass(String pass) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			User user = (User) ss.createQuery("from User where password = :passla").setParameter("passla", pass).uniqueResult();
			return user;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	@Override
	public boolean insert(User t) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			ss.save(t);
			System.out.println("Ban da them is user la " + t.getId() + " vao co so du lieu");
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
	public boolean update(User t) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			ss.update(t);
			System.out.println("Ban da sua is user la " + t.getId() + " vao co so du lieu");
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
	public List<User> selectByName(String idtim) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int idxoa) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			int xoaTc = ss.createQuery("delete from User where id = :idla").setParameter("idla", idxoa).executeUpdate();
			ss.getTransaction().commit();
			if (xoaTc > 0) {
				System.out.println("Ban da xoa id user la " + idxoa + " ra khoi co so du lieu");
				return true;
			} else {
				System.out.println("Xoa khong thanh cong");
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
