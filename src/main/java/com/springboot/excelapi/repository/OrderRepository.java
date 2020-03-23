package com.springboot.excelapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.springboot.excelapi.dto.CustomerOrder;

public interface OrderRepository extends CrudRepository<CustomerOrder, Integer> {

}
