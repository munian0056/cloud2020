package com.munian.springcloud.service.impl;

import com.munian.springcloud.dao.PaymentDao;
import com.munian.springcloud.entities.Payment;
import com.munian.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    public int save(Payment payment) {
        return paymentDao.save(payment);
    }

    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
