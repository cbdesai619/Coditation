package com.demo;

public class Cell {
	String name;
	int state;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Cell name=" + name + ", state=" + state ;
	}
	
}
