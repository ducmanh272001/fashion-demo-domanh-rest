package com.fashion.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConfigFactory {

	
	private static SessionFactory FACTORY;
	
	private ConfigFactory(){
		
	}
	
	public static SessionFactory getSessionFactory() {
		if(FACTORY == null) {
			FACTORY = new Configuration().configure().buildSessionFactory();
		}
		return FACTORY;
	}
}
