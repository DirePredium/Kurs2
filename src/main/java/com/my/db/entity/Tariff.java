package com.my.db.entity;

import com.my.db.Abstract.DBEntity;

public class Tariff extends DBEntity{
	String title;
	float price;
	float speed;
	String img_link;
	
	public String getImg_link() {
		return img_link;
	}

	public void setImg_link(String img_link) {
		this.img_link = img_link;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Tariff [title=" + title + ", price=" + price + ", speed=" + speed + ", ing_link=" + img_link + "]";
	}

}
