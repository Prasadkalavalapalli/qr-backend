package com.middlewares.www.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EvPayment {
	
	public EvPayment(int id, String evusername, String evusermobile, String email, int amount,
			String razorpayOrderid, String razorpayPaymentId, String status) {
		super();
		this.id = id;
		this.evusername = evusername;
		this.evusermobile = evusermobile;
		this.email = email;
		this.amount = amount;
		this.razorpayOrderid = razorpayOrderid;
		this.razorpayPaymentId = razorpayPaymentId;
	    this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEvusername() {
		return evusername;
	}
	public void setEvusername(String evusername) {
		this.evusername = evusername;
	}
	public String getEvusermobile() {
		return evusermobile;
	}
	public void setEvusermobile(String evusermobile) {
		this.evusermobile = evusermobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getRazorpayOrderid() {
		return razorpayOrderid;
	}
	public void setRazorpayOrderid(String razorpayOrderid) {
		this.razorpayOrderid = razorpayOrderid;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	 private int id;
	private  String evusername;
	private String evusermobile;
	private String email;
	private int amount;
	private String razorpayOrderid;
	private String 	status;
	private String razorpayPaymentId;

	public String getRazorpayPaymentId() {
	    return razorpayPaymentId;
	}

	public void setRazorpayPaymentId(String razorpayPaymentId) {
	    this.razorpayPaymentId = razorpayPaymentId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public EvPayment() {
	    // required by JPA/Hibernate
	}

}
