package com.devdutt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.devdutt.entity.Employee;

public class PersistTest {

	public static void main(String[] args) {

		Configuration cfg = null;
		SessionFactory factory = null;
		Session ses = null;
		Employee employee = null;
		Transaction tx = null;
		boolean flag = false;

		// create Configuration class
		cfg = new Configuration();

		// read and locate configuration.xml file
		cfg.configure("com/devdutt/cfgs/hibernate.cfg.xml");

		// create Session Factory object
		factory = cfg.buildSessionFactory();

		// create Session object
		ses = factory.openSession();

		// create domain class object having data
		employee = new Employee();
		employee.setEid(112);
		employee.setEname("Mayur");
		employee.setEmail("mayur@gmail.com");
		employee.setSalary(34000.00);
		try {
			tx = ses.beginTransaction();
			ses.persist(employee);
			tx.commit();
			System.out.println("Record inserted successfully...");
		} // try
		catch (HibernateException he) {
			tx.rollback();
			he.printStackTrace();
			System.out.println("Record not inserted...");
		} // catch
		catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			System.out.println("Record not inserted...");
		} // catch
	}
}
