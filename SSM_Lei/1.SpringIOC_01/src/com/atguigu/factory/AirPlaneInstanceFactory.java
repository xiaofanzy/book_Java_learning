package com.atguigu.factory;

import com.atguigu.bean.AirPlane;

public class AirPlaneInstanceFactory {
	
	public AirPlane getAirPlane(String jzName){
		AirPlane airPlane = new AirPlane();
		airPlane.setFdj("太行");
		airPlane.setFjzName("ddd");
		airPlane.setJaName("ddddddd");
		airPlane.setYc(1000);
		
		return airPlane;
	}

}
