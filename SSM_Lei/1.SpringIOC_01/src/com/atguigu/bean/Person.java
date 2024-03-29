package com.atguigu.bean;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Person {
	
	private String lastName;
	private String gender;
	private Integer age;
	private String email;
	
	private Car car;
	
	private List<Book> book;
	
	private Map<String,Object> maps;
	
	private Properties properties;
	
	public Person() {
		super();
		System.out.println("person 被创建");
	}
	
	
	
	
	public Person(Car car, List<Book> book) {
		super();
		this.car = car;
		this.book = book;
	}




	public Car getCar() {
		return car;
	}




	public void setCar(Car car) {
		this.car = car;
	}




	public List<Book> getBook() {
		return book;
	}




	public void setBook(List<Book> book) {
		this.book = book;
	}




	public Map<String, Object> getMaps() {
		return maps;
	}




	public void setMaps(Map<String, Object> maps) {
		this.maps = maps;
	}




	public Properties getProperties() {
		return properties;
	}




	public void setProperties(Properties properties) {
		this.properties = properties;
	}




	public Person(String lastName, String gender, Integer age) {
		super();
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
	}
	
	public Person(String lastName, String gender, String email) {
		super();
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
	}



	public Person(String lastName, String gender, Integer age, String email) {
		super();
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		System.out.println("调用了有参构造器");
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}




	@Override
	public String toString() {
		return "Person [lastName=" + lastName + ", gender=" + gender + ", age=" + age + ", email=" + email + ", car="
				+ car + ", book=" + book + ", maps=" + maps + ", properties=" + properties + "]";
	}
	
	
	
	

}
