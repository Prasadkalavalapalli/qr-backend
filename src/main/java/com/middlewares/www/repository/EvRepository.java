package com.middlewares.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.middlewares.www.pojo.EvPayment;

public interface EvRepository extends JpaRepository<EvPayment,Integer>{
 public EvPayment findByRazorpayOrderid(String orderId);
	
}
