package com.fashion.service.payment;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fashion.config.ConfigFactory;
import com.fashion.entity.NewsEntity;
import com.fashion.entity.PaymentEntity;

public class PaymentServiceImpl implements PaymentService {

	public static PaymentServiceImpl getNewPayment() {
		return new PaymentServiceImpl();
	}

	private SessionFactory FACTORY;

	private PaymentServiceImpl() {
		FACTORY = ConfigFactory.getSessionFactory();
		if (FACTORY == null) {
			System.out.println("Lỗi Không có kết nối với biến SessionFactory");
		}
	}

	@Override
	public Boolean create(PaymentEntity paymentEntity) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			ss.save(paymentEntity);
			System.out.println("Bạn đã thêm id là " + paymentEntity.getId() + " vào cơ sở dữ liệu");
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
	public List<PaymentEntity> list() {
		// Lấy list tin tức
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<PaymentEntity> list = ss.createQuery("from PaymentEntity order by id desc").list();
			ss.close();
			return list;
		} catch (Exception e) {
			System.out.println("Xảy ra lỗi ngoại lệ");
			ss.getTransaction().rollback();
		}
		return null;
	}

}
