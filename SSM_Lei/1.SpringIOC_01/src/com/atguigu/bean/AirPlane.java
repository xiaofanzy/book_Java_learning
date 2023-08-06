package com.atguigu.bean;

public class AirPlane {
	
	private String jaName;
	private Integer yc;
	private String FjzName;
	public String getJaName() {
		return jaName;
	}
	public void setJaName(String jaName) {
		this.jaName = jaName;
	}
	public Integer getYc() {
		return yc;
	}
	public void setYc(Integer yc) {
		this.yc = yc;
	}
	public String getFjzName() {
		return FjzName;
	}
	public void setFjzName(String fjzName) {
		FjzName = fjzName;
	}
	public String getFdj() {
		return fdj;
	}
	public void setFdj(String fdj) {
		this.fdj = fdj;
	}
	private String fdj;
	@Override
	public String toString() {
		return "AirPlane [jaName=" + jaName + ", yc=" + yc + ", FjzName=" + FjzName + ", fdj=" + fdj + "]";
	}
	public AirPlane(String jaName, Integer yc, String fjzName, String fdj) {
		super();
		this.jaName = jaName;
		this.yc = yc;
		FjzName = fjzName;
		this.fdj = fdj;
	}
	public AirPlane() {
		super();
	}
	
	
	

}
