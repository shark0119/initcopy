package com.cn.unary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import cn.com.unary.initcopy.config.BeanConfig;
import cn.com.unary.initcopy.dao.DataSource;

@Component
public class TestTest {
	@Test
	public void test () {
		Date d = new Date ();
		System.out.println(d);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			d = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(d.toString());
			System.out.println(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/*	@Test
	public void test1 () {
		Function<Te, String> f = Te::fun;
		// Function<Te, String> f2 = Te::fun2;
		Function<Te, Void> f1 = Te::nonShow;
		Supplier<String> s = Te::staticFun;
	}*/
	@Test
	public void test2 () {
		DataSource ds = new DataSource();
		try {
			ds.init("G:\\sqliteDB\\test.db");
			Statement stmt = ds.getConnection().createStatement();
			stmt.execute("INSERT INTO FILE_INFO VALUES('asdfadsdfasdf', 'dd', 2, '22', '222', 'd');");
			stmt.executeQuery("");
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ds.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	@Test
	public void test3 () {
		File file = new File("G:\\temp\\奸商.java");
		try {
			file.createNewFile();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		try {
			FileInputStream fis = new FileInputStream(file);
			byte [] b = new byte[100];
			fis.read(b);
			System.out.println(file.getName());
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	@Autowired
	@Qualifier("str2")
	String str;
	public static void main (String [] args) {
		try {
			AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(BeanConfig.class);
			TestTest tt = ac.getBean(TestTest.class);
			System.out.println(tt.str);
			ac.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

class Te {
	public static final Te t = new Te("static final var assign ");
	static {
		System.out.println("static block execute");
	}
	Te(){
		System.out.println("constructor begin");
	}
	Te(String str){
		System.out.println("constructor begin " + str);
	}
	public static String staticFun () {
		return "";
	}
	public String fun () {
		return "";
	}
	public String fun (Te t) {
		return "";
	}
	public String fun2 (Te t) {return "";}
	public Void nonShow () {
		return null;
	}
	public static void show () {
		System.out.println("dsdfsdf");
	}
}
