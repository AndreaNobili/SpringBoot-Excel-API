package com.springboot.excelapi.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.excelapi.dto.CustomerOrder;

public interface OrderService {
	
	public List<CustomerOrder> getOrdersList();
	
	public void insertSingleOrder(CustomerOrder order);
	
	Boolean insertOrdersFromExcel(MultipartFile uploadfile) throws Exception;

}
