package service;

import org.springframework.stereotype.Service;

import models.Payment;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

import repositories.PaymentRepository;

@Service
public class PaymentService {
	@Autowired
	private PaymentRepository paymentRepository;
	
	public Payment getPaymentByPaymentNumber(Long payment_number) {
		return paymentRepository.findByPaymentnumber(payment_number);
	}
	
	public List<Payment> getAllPayments() {
		return (List<Payment>) paymentRepository.findAll();
	}
	
	public Optional<Payment> getPayment(Long payment_number) {
		return this.paymentRepository.findById(payment_number);
	}
	
	public Boolean savePayment(Payment payment) {
		paymentRepository.save(payment);
		return true;
	}
}
