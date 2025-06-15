package com.middlewares.www.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.middlewares.www.pojo.EvPayment;
import com.middlewares.www.repository.EvRepository;
import com.middlewares.www.service.EvPaymentService;

@RestController
@CrossOrigin("*")
public class EvPaymentController {
	
	@Autowired
	private EvPaymentService evservice;
	@Autowired
    private EvRepository evpayrepo;
	
	@GetMapping("/")
	public String Init() {
		return"index";
	}
@PostMapping(value="/add_payment", produces="application/json")
public ResponseEntity<EvPayment> createPayment(@RequestBody EvPayment evpay)throws Exception{
	EvPayment payment=evservice.createPayment(evpay);
	return new ResponseEntity<>(payment,HttpStatus.CREATED);
	
}
@PostMapping("/handle-payment-callback")
public String handlePaymentCallback(@RequestParam Map<String ,String>respPayload) {
	System.out.println(respPayload);
	EvPayment updateorder=evservice.updatePaymentStatus(respPayload);
	
	return "success";
}

@PostMapping("/capture-payment")
public ResponseEntity<String> capturePayment(
        @RequestParam(name = "paymentId") String paymentId,
        @RequestParam(name = "amount") int amount) throws Exception {

    String message = evservice.capturePayment(paymentId, amount);
    return new ResponseEntity<>(message, HttpStatus.OK);
}

@PostMapping("/refund-payment")
public ResponseEntity<String> refundPayment(
        @RequestParam(name = "paymentId") String paymentId,
        @RequestParam(name = "amount") int amount) throws Exception {

    String message = evservice.refundPayment(paymentId, amount);
    return new ResponseEntity<>(message, HttpStatus.OK);
}

@GetMapping("/get-payment")
public EvPayment getPaymentByOrderId(@RequestParam("orderId") String orderId) {
    return evpayrepo.findByRazorpayOrderid(orderId);
}


}
