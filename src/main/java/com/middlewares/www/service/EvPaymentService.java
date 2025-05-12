package com.middlewares.www.service;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.middlewares.www.pojo.EvPayment;
import com.middlewares.www.repository.EvRepository;
import com.razorpay.Order;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Refund;

@Service
public class EvPaymentService {

    @Autowired
    private EvRepository evpayrepo;

    @Value("${razorpay.key.id}")
    private String razorPaykey;

    @Value("${razorpay.secret.key}")
    private String razorpaySecrete;

    private RazorpayClient client;

//    public EvPayment createPayment(EvPayment evpay) throws Exception {
//        JSONObject ordreq = new JSONObject();
//        ordreq.put("amount", evpay.getAmount()*100); // amount in paise
//        ordreq.put("currency", "INR");
//        ordreq.put("receipt", evpay.getEmail());
//
//        this.client = new RazorpayClient(razorPaykey, razorpaySecrete);
//        Order razorPayOrder = client.orders.create(ordreq);
//        System.out.println(razorPayOrder);
//
//        evpay.setRazorpayOrderid(razorPayOrder.get("id"));
//        evpay.setStatus(razorPayOrder.get("status"));
//        evpayrepo.save(evpay);
//        return evpay;
//    }
    
    public EvPayment createPayment(EvPayment evpay) throws Exception {
        JSONObject ordreq = new JSONObject();
        ordreq.put("amount", evpay.getAmount() * 100); // amount in paise
        ordreq.put("currency", "INR");
        ordreq.put("receipt", evpay.getEmail());
        ordreq.put("payment_capture", 0); // preauthorization only

        this.client = new RazorpayClient(razorPaykey, razorpaySecrete);
        Order razorPayOrder = client.orders.create(ordreq);
        System.out.println(razorPayOrder);

        evpay.setRazorpayOrderid(razorPayOrder.get("id"));
        evpay.setStatus(razorPayOrder.get("status"));
        evpayrepo.save(evpay);
        return evpay;
    }
    public String capturePayment(String paymentId, int amount) throws Exception {
        this.client = new RazorpayClient(razorPaykey, razorpaySecrete);
        JSONObject options = new JSONObject();
        options.put("amount", amount * 100); // amount in paise
        options.put("currency", "INR");

        client.payments.capture(paymentId, options);
        return "₹" + amount + " captured. Remaining auto-refunded.";
    }
//    public String refundPayment(String paymentId) throws Exception {
//        this.client = new RazorpayClient(razorPaykey, razorpaySecrete);
//        client.payments.refund(paymentId);
//        return "Payment Refunded Automatically";
//    }
    public String refundPayment(String paymentId, int amountInRupees) throws Exception {
        this.client = new RazorpayClient(razorPaykey, razorpaySecrete);

        // Convert rupees to paise
        int amountInPaise = amountInRupees * 100;

        // Prepare refund request
        JSONObject refundRequest = new JSONObject();
        refundRequest.put("amount", amountInPaise);  // partial amount
        refundRequest.put("payment_id", paymentId);

        Refund refund = client.payments.refund(refundRequest);

        return "Refund initiated successfully: " + refund.get("id");
    }

   

    public EvPayment updatePaymentStatus(Map<String, String> responsePayload) {
        String razorPayOrderid = responsePayload.get("razorpay_order_id");
        String razorPayPaymentId = responsePayload.get("razorpay_payment_id");

        EvPayment payment = evpayrepo.findByRazorpayOrderid(razorPayOrderid);

        if (payment == null) {
            throw new RuntimeException("Payment record not found for order id: " + razorPayOrderid);
        }

        payment.setStatus("PAYMENT_COMPLETED");
        payment.setRazorpayPaymentId(razorPayPaymentId);

        return evpayrepo.save(payment);
    }

	
}
