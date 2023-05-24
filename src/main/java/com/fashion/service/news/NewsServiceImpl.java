package com.fashion.service.news;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fashion.entity.NewsEntity;
import com.fashion.config.ConfigFactory;

public class NewsServiceImpl implements NewsService<NewsEntity> {

	public static NewsServiceImpl getNewTinTuc() {
		return new NewsServiceImpl();
	}

	private SessionFactory FACTORY;

	private NewsServiceImpl() {
		FACTORY = ConfigFactory.getSessionFactory();
		if (FACTORY == null) {
			System.out.println("Lỗi Không có kết nối với biến SessionFactory");
		}
	}

	@Override
	public List<NewsEntity> selectAll() {
		// Lấy list tin tức
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<NewsEntity> list = ss.createQuery("from NewsEntity order by id desc").list();
			ss.close();
			return list;
		} catch (Exception e) {
			System.out.println("Xảy ra lỗi ngoại lệ");
			ss.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public NewsEntity selectById(int id) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			NewsEntity tintuc = ss.get(NewsEntity.class, id);
			return tintuc;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	@Override
	public boolean insert(NewsEntity t) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			ss.save(t);
			System.out.println("Bạn đã thêm id là " + t.getId() + " vào cơ sở dữ liệu");
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
	public boolean update(NewsEntity t) {
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
	public List<NewsEntity> selectByName(String idtim) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int idxoa) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			// Xóa tin tức
			NewsEntity tintuc = ss.get(NewsEntity.class, idxoa);
			ss.delete(tintuc);
			ss.getTransaction().commit();
			System.out.println("Bạn đã xóa id là " + idxoa + " vào cơ sở dữ liệu");
			return true;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return false;
	}

	// Lấy số lượng tin tức
	public Long countTinTuc() {
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Long> soluong =  ss.createQuery("select count(*) from NewsEntity").list();
			return soluong.get(0);
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

}
