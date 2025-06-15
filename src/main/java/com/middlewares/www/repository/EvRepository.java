package com.middlewares.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.middlewares.www.pojo.EvPayment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EvRepository extends JpaRepository<EvPayment,Integer> {
    @Query("SELECT e FROM EvPayment e WHERE e.razorpayOrderid = :orderId")
    EvPayment findByRazorpayOrderid(@Param("orderId") String orderId);
}

