package com.fashion.config;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.fashion.entity.BillDetailEntity;
import com.fashion.entity.BillEntity;
import com.fashion.entity.BranchEntity;
import com.fashion.entity.CalculateEntity;
import com.fashion.entity.ColorEntity;
import com.fashion.entity.CustomerEntity;
import com.fashion.entity.HotProductEntity;
import com.fashion.entity.ImagerEntity;
import com.fashion.entity.NewsEntity;
import com.fashion.entity.PaymentEntity;
import com.fashion.entity.ProductDetailEntity;
import com.fashion.entity.ProductEntity;
import com.fashion.entity.RoleEntity;
import com.fashion.entity.SizeEntity;
import com.fashion.entity.TypeProductEntity;
import com.fashion.entity.UserEntity;
import com.fashion.entity.UserRoleEntity;

public class ConfigFactory {

	private ConfigFactory() {

	}

	private static final SessionFactory concreteSessionFactory;

	static {
		try {
			Properties prop = new Properties();
			prop.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
			prop.setProperty("hibernate.connection.url",
					"jdbc:postgresql://localhost:5432/macbookair");
			prop.setProperty("hibernate.connection.username", "postgres");
			prop.setProperty("hibernate.connection.password",
					"postgres");
			prop.setProperty("hibernate.hbm2ddl.auto", "update");
			prop.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
			prop.setProperty("hibernate.show_sql", "false");

			concreteSessionFactory = new Configuration().addAnnotatedClass(SizeEntity.class)
					.addAnnotatedClass(TypeProductEntity.class).addAnnotatedClass(BranchEntity.class)
					.addAnnotatedClass(ProductEntity.class).addAnnotatedClass(ColorEntity.class)
					.addAnnotatedClass(ProductDetailEntity.class).addAnnotatedClass(UserEntity.class)
					.addAnnotatedClass(RoleEntity.class).addAnnotatedClass(UserRoleEntity.class)
					.addAnnotatedClass(CustomerEntity.class).addAnnotatedClass(BillEntity.class)
					.addAnnotatedClass(BillDetailEntity.class).addAnnotatedClass(NewsEntity.class)
					.addAnnotatedClass(ImagerEntity.class).addAnnotatedClass(HotProductEntity.class)
					.addAnnotatedClass(PaymentEntity.class).addAnnotatedClass(CalculateEntity.class).setProperties(prop)
					.buildSessionFactory();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return concreteSessionFactory;
	}
}
