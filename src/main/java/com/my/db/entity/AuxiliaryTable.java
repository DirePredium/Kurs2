package com.my.db.entity;

import java.io.Serializable;

import com.my.db.Abstract.DBEntity;

public class AuxiliaryTable extends DBEntity  implements Serializable{
	public int t1;
	public int t2;
	public int getT1() {
		return t1;
	}
	public void setT1(int t1) {
		this.t1 = t1;
	}
	public int getT2() {
		return t2;
	}
	public void setT2(int t2) {
		this.t2 = t2;
	}
	@Override
	public String toString() {
		return "AuxiliaryTable"+getId()+" [t1=" + t1 + ", t2=" + t2 + "]";
	}
}
