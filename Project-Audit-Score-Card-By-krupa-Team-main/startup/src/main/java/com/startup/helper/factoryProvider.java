package com.startup.helper;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class factoryProvider {

	public static SessionFactory factory;
	
	public static SessionFactory getfactory() {
		if(factory == null) {
			factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		}
		return factory;
	}
	
	public static void shutdown() {
        if (factory != null && !factory.isClosed()) {
            factory.close();
        }
    }
	}
