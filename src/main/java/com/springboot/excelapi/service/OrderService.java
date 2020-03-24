package com.springboot.excelapi.service;

import java.util.List;

import com.springboot.excelapi.dto.CustomerOrder;

public interface OrderService {
	
	public List<CustomerOrder> getOrdersList();
	
	public void insertOrder(CustomerOrder order);

}
