package com.springboot.excelapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.excelapi.dto.CustomerOrder;
import com.springboot.excelapi.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	
	public List<CustomerOrder> getOrdersList() {
		
		List<CustomerOrder> result = (List<CustomerOrder>) orderRepository.findAll();
		
		//List<Order> result = new ArrayList<Order>();
		
		return result;
	}

}
