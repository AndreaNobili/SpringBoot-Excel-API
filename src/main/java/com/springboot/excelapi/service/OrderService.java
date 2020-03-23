package com.springboot.excelapi.service;

import java.util.List;

import com.springboot.excelapi.dto.Order;

public interface OrderService {
	
	public List<Order> getOrdersList();

}
