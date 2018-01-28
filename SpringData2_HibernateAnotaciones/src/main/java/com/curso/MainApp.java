package com.curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


//@Configuration
//@EnableTransactionManagement
//@ComponentScan("com.curso")
////@PropertySource("classpath:application.properties")
//@EnableJpaRepositories("com.curso.repository")
public class MainApp {

	@Autowired
	private Test test;
	
	public static void main(String args[]){
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		Test test = (Test)context.getBean("test");
		test.begins();

	}

}
