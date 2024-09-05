package com.startup.repository;

import java.util.*;
import org.hibernate.*;
import org.hibernate.query.Query;
import com.startup.helper.factoryProvider;
import com.startup.model.*;

public class repositroy {

	public static void savestartup(startup startup) {
		Session session = factoryProvider.getfactory().openSession();
		Transaction tx = session.beginTransaction();
		session.save(startup);
		tx.commit();
		session.close();
	}

	public static void savesaatracker(List<saatracker> list) {
		Session session = factoryProvider.getfactory().openSession();
		Transaction tx = session.beginTransaction();
		for (saatracker obj : list)
			session.save(obj);

		tx.commit();
		session.close();

	}

	public static List<String> getnames() {
		Session session = factoryProvider.getfactory().openSession();
		@SuppressWarnings({ "unchecked" })
		Query<startup> Q = session.createQuery("from startup");
		List<startup> list = Q.list();
		List<String> startupnames = new ArrayList<String>();
		for (startup names : list) {
			startupnames.add(names.getStartupname());
		}
		session.close();
		return startupnames;
	}

	public static void savesamanual(List<samanual> list) {
		Session session = factoryProvider.getfactory().openSession();
		Transaction tx = session.beginTransaction();
		for (samanual obj : list)
			session.save(obj);

		tx.commit();
		session.close();
	}

	public static void savesscard(List<sscard> list) {
		Session session = factoryProvider.getfactory().openSession();
		Transaction tx = session.beginTransaction();
		for (sscard obj : list)
			session.save(obj);

		tx.commit();
		session.close();
	}

	@SuppressWarnings("deprecation")
	public static List<startup> getdata(String startupname) {
		Session session = factoryProvider.getfactory().openSession();
		@SuppressWarnings("unchecked")
		Query<startup> Q = session.createQuery("from startup where startupname=:startupname");
		Q.setString("startupname", startupname);
		List<startup> result = Q.list();
		result.forEach(list -> {
			list.setWebsite(list.getWebsite().trim());
		});
		session.close();
		return result;
	}

	@SuppressWarnings("deprecation")
	public static List<saatracker> getdata1(String startupname) {
		Session session = factoryProvider.getfactory().openSession();
		@SuppressWarnings("unchecked")
		Query<saatracker> Q = session.createQuery("from saatracker where startupname=:startupname");
		Q.setString("startupname", startupname);
		List<saatracker> result = Q.list();
		session.close();
		return result;
	}

	@SuppressWarnings("deprecation")
	public static List<samanual> getdata2(String startupname) {
		Session session = factoryProvider.getfactory().openSession();
		@SuppressWarnings("unchecked")
		Query<samanual> Q = session.createQuery("from samanual where startupname=:startupname");
		Q.setString("startupname", startupname);
		List<samanual> result = Q.list();
		session.close();
		return result;
	}

	@SuppressWarnings("deprecation")
	public static List<sscard> getdata3(String startupname) {
		Session session = factoryProvider.getfactory().openSession();
		@SuppressWarnings("unchecked")
		Query<sscard> Q = session.createQuery("from sscard where startupname=:startupname");
		Q.setString("startupname", startupname);
		List<sscard> result = Q.list();
		session.close();
		return result;
	}

	public static void update(startup startup) {
		Session session = factoryProvider.getfactory().openSession();
		Transaction tx = session.beginTransaction();
		@SuppressWarnings("unchecked")
		Query<startup> query = session.createQuery(
				"Update startup set startupcategory=:startupcategory,startupcode=:startupcode,website=:website Where startupname=:startupname and srno=:srno");
		query.setParameter("startupcategory", startup.getStartupcategory());
		query.setParameter("startupcode", startup.getStartupcode());
		query.setParameter("website", startup.getWebsite());
		query.setParameter("startupname", startup.getStartupname());
		query.setParameter("srno", startup.getSrno());

		query.executeUpdate();
		tx.commit();
		session.close();

	}

	public static int deletedata(String name) {
		Session s = factoryProvider.getfactory().openSession();
		Transaction tx = s.beginTransaction();
		Query<?> query = s.createQuery("delete from startup where startupname=:startupname");
		query.setParameter("startupname", name);
		int result = query.executeUpdate();
		tx.commit();
		s.close();
		return result;
	}

	public static void updatemanual(samanual one) {
		Session s = factoryProvider.getfactory().openSession();
		Transaction tx = s.beginTransaction();
		samanual samanual = s.get(samanual.class, new samanualkey(one.getStartupname(), one.getId()));
		samanual.setActualscore(one.getActualscore());
		samanual.setRemark(one.getRemark());
		s.update(samanual);
		sscard sscard = s.get(sscard.class, new sscardkey(one.getStartupname(), one.getId()));
		sscard.setActualscore(one.getActualscore());
		sscard.setRemark(one.getRemark());
		sscard.setDate_process(new Date());
		s.update(sscard);
		tx.commit();
		s.close();
		System.out.println("Update Successfylly...");
	}
}
