package com.atguigu.bean;

public class Car {
	
	private String carName;
	private String color;
	private String price;
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Car [carName=" + carName + ", color=" + color + ", price=" + price + "]";
	}
	public Car(String carName, String color, String price) {
		super();
		this.carName = carName;
		this.color = color;
		this.price = price;
	}
	public Car() {
		super();
		System.out.println("car创建");
	}
	
	

}
