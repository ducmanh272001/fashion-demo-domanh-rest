package com.fashion.service.user;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fashion.config.ConfigFactory;
import com.fashion.entity.UserEntity;

public class UserServiceImpl implements UserService<UserEntity> {

	private SessionFactory FACTORY;

	private UserServiceImpl() {
		FACTORY = ConfigFactory.getSessionFactory();
		if (FACTORY == null) {
			System.out.println("Khong co ket noi voi session factory");
		}
	}

	public static UserServiceImpl getNewUser() {
		return new UserServiceImpl();
	}

	@Override
	public List<UserEntity> selectAll() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<UserEntity> lst = ss.createQuery("from UserEntity").list();
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
	public UserEntity selectById(int id) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			UserEntity user = ss.get(UserEntity.class, id);
			return user;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	public UserEntity selectByUserName(String name) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			UserEntity user = (UserEntity) ss.createQuery("from UserEntity where name = :namela")
					.setParameter("namela", name).uniqueResult();
			return user;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// logi form dang nhap
	public UserEntity selectByDangNhap(String username, String password) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			UserEntity user = (UserEntity) ss.createQuery("from UserEntity where name = :namela and password = :pass")
					.setParameter("namela", username).setParameter("pass", password).uniqueResult();
			return user;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	public UserEntity selectByPass(String pass) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			UserEntity user = (UserEntity) ss.createQuery("from UserEntity where password = :passla")
					.setParameter("passla", pass).uniqueResult();
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
	public boolean insert(UserEntity t) {
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
	public boolean update(UserEntity t) {
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
	public List<UserEntity> selectByName(String idtim) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int idxoa) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			int xoaTc = ss.createQuery("delete from UserEntity where id = :idla").setParameter("idla", idxoa)
					.executeUpdate();
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

	@Override
	public UserEntity findByEmail(String email) {
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			UserEntity user = (UserEntity) ss.createQuery("from UserEntity where name = :name")
					.setParameter("name", email).uniqueResult();
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
	public UserEntity save(UserEntity t) {
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			ss.save(t);
			System.out.println("Bạn đã thêm user có ID là " + t.getId() + " vào cơ sở dữ liệu");
			ss.getTransaction().commit();
			return t;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

}
