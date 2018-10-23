package controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import models.Loan;
import models.Payment;
import models.PaymentPost;
import service.PaymentService;
import service.LoanService;

@Controller
@RestController
@RequestMapping(value = "/payment")
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private LoanService loanService;
	
	protected PaymentController() {}
	
	@RequestMapping(value = "/list/all")
	@GetMapping
	public List<Payment> getAllPayments() {
		return this.paymentService.getAllPayments();
	}
	
	@RequestMapping(value = "/get/{paymentnumber}")
	@GetMapping
	public @ResponseBody Payment getPayment(@PathVariable("paymentnumber") Long paymentnumber) {
		Optional<Payment> payment = this.paymentService.getPayment(paymentnumber);
		if (payment.isPresent()) {
			return payment.get();
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "/getwithloan/{paymentnumber}")
	@GetMapping
	public @ResponseBody PaymentPost getPaymentWithLoan(@PathVariable("paymentnumber") Long paymentnumber) {
		Optional<Payment> paymentOptional = this.paymentService.getPayment(paymentnumber);
		Payment payment;
		if (paymentOptional.isPresent()) {
			payment = paymentOptional.get();
		} else {
			return null;
		}
		PaymentPost paymentPost = new PaymentPost();
		paymentPost.setLoannumber(payment.getLoan().getLoannumber());
		paymentPost.setLoan(payment.getLoan());
		paymentPost.setPaymentamount(payment.getPaymentamount());
		paymentPost.setPaymentnumber(payment.getPaymentnumber());
		return paymentPost;
	}
	
	@RequestMapping(value = "/update/new", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@PostMapping
	public @ResponseBody Boolean addNewPayment(PaymentPost paymentPost) {
		System.out.println("Trying to save payment: " + paymentPost.toCustomString());
		Payment payment = new Payment();
		payment.setPaymentamount(payment.getPaymentamount());
		payment.setPaymentdate(LocalDateTime.now());
		Optional<Loan> loanOptional = this.loanService.getLoan(paymentPost.getLoannumber());
		Loan loan;
		if (!loanOptional.isPresent()) {
			return false;
		}
		loan = loanOptional.get();
		loan.getPayments().add(payment);
		payment.setLoan(loanOptional.get());
		this.paymentService.savePayment(payment);
		return this.loanService.saveLoan(loan);
	}
	
	/*@RequestMapping(value = "/update/existing", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@PostMapping
	public @ResponseBody Boolean updatePayment(PaymentPost payment) {
		System.out.println("Trying to save payment: " + payment.toCustomString());
		return this.paymentService.savePayment(payment);
	}*/
	
	@RequestMapping(value = "/delete/{paymentnumber}")
	@GetMapping
	public @ResponseBody Boolean deletePayment(@PathVariable("paymentnumber") Long paymentnumber) {
		Optional<Payment> paymentOptional = this.paymentService.getPayment(paymentnumber);
		if (!paymentOptional.isPresent()) {
			return false;
		}
		return this.paymentService.deletePayment(paymentnumber);
	}
	
}
