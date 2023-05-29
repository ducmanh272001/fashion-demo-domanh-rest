package com.fashion.service.payment;

import java.util.List;

import com.fashion.entity.PaymentEntity;

public interface PaymentService {
	
	
	public Boolean create(PaymentEntity paymentEntity);
	
	public List<PaymentEntity> list();

}
