package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import models.Payment;
import service.PaymentService;

@Controller
@RestController
@RequestMapping(value = "/payment")
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
	
	protected PaymentController() {}
	
	@RequestMapping(value = "/list/all")
	@GetMapping
	public List<Payment> getAllPayments() {
		return this.paymentService.getAllPayments();
	}
	
	@RequestMapping(value = "/update/new", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@PostMapping
	public @ResponseBody Boolean addNewBranch(Payment payment) {
		System.out.println("Trying to save payment: " + payment.toCustomString());
		return this.paymentService.savePayment(payment);
	}
}
