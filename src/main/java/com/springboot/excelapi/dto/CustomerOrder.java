package com.springboot.excelapi.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.print.attribute.standard.DateTimeAtCompleted;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "customer_order")
public class CustomerOrder {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	private String fullName;
    private String address;
    private String product;
    
    @Column(name = "order_date_time")
    private Date orderDate;
    
    private int quantity;
    
    

	public CustomerOrder() {
		super();
	}

	public CustomerOrder(String fullName, String address, String product, Date orderDate, int quantity) {
		super();
		//this.id = id;
		this.fullName = fullName;
		this.address = address;
		this.product = product;
		this.orderDate = orderDate;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CustomerOrder [id=" + id + ", fullName=" + fullName + ", address=" + address + ", product=" + product
				+ ", orderDate=" + orderDate + ", quantity=" + quantity + "]";
	}
	
	
    
}
