package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cart_table")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "menu_id")
	private Integer menu_id;
	
	@Column(name = "mname")
	private String mname;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "total_amount")
	private float total_amount;
	
	@Column(name = "quantity")
	private Integer quantity;
	public Cart() {
		super();
	}

	public Cart(Integer id, Integer menu_id,String mname, float price, float total_amount, Integer quantity) {
		super();
		this.id = id;
		this.menu_id = menu_id;
		this.mname=mname;
		this.price = price;
		this.total_amount = total_amount;
		this.quantity = quantity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(Integer menu_id) {
		this.menu_id = menu_id;
	}
	

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(float total_amount) {
		this.total_amount = this.getPrice()*this.getQuantity();
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
