package com.devdutt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.devdutt.entity.Employee;

public class SaveTest {

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
		employee.setEid(111);
		employee.setEname("Devdutt");
		employee.setEmail("devdutt@gmail.com");
		employee.setSalary(80000.00);
		try {

			tx = ses.beginTransaction();
			int idVal = (Integer) ses.save(employee);

			System.out.println("Identity Value:- " + idVal);
			flag = true;
		} // try
		catch (HibernateException he) {
			flag = false;
			he.printStackTrace();

		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		} // catch
		finally {
			if (flag) {
				tx.commit();
				System.out.println("Object Saved Successfully");
			} else {
				tx.rollback();
				System.out.println("Object not Saved");
			} // else
		} // finally
	}// main
}// class
