package com.my.db.entity;

import com.my.db.Abstract.DBEntity;

public class Equipment extends DBEntity{
	public String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Equipment"+ getId() +" [name=" + name + "]";
	}
}
