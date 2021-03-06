package com.infotech.client;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.infotech.entities.Person;
import com.infotech.util.HibernateUtil;

public class AntiPatternBatchInsertClientTest {

	public static void main(String[] args) {
		Transaction tx =  null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ) {
			tx = session.beginTransaction();
			//for ( int i = 1; i <= 100_000; i++ ) {
			for ( int i = 1; i <= 100; i++ ) {
				Person  person = new Person();
				person.setFirstName("Gavin_"+i);
				person.setLastName("King_"+i);
				
				session.persist(person);
		    }
			tx.commit();
		} catch (Exception e) {
			if(tx != null && tx.isActive())
				tx.rollback();
			throw e;
		}
	}
}