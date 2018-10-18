package com.java;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.dto.Phone;
import com.java.dto.Student;
import com.java.dto.Type;

public class Main {
	static SessionFactory sf;
	static {
		Configuration cfg = new Configuration().addPackage("com.java.dto");
		cfg.configure("hibernate.cfg.xml");
		sf = cfg.buildSessionFactory();
	}

	public static void main(String args[]) {
		Main obj = new Main();
		try {
			obj.insertRecords();
			obj.fetchData();
		} finally {
			sf.close();
		}

	}

	public void insertRecords() {
		Phone p1 = new Phone(76_372l, Type.LANDLINE);
		Phone p2 = new Phone(7_38_47_476l, Type.MOBILE);
		List<Phone> list = Arrays.asList(p1, p2);
		Student st = new Student(1, "payal", null);
		Session s = sf.openSession();
		s.beginTransaction();
		s.save(p1);
		s.save(p2);
		st.setPhones(list);
		s.save(st);
		s.getTransaction().commit();
		s.close();
	}

	public void fetchData() {
		Session s = sf.openSession();
		Student st = s.get(Student.class, 1);
		System.out.println(st.getPhones());
		s.close();
	}
}
