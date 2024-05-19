package com.jet.student.repository;

import com.jet.student.entity.Payment;
import com.jet.student.enums.PaymentStatus;
import com.jet.student.enums.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByStudentMatricule(String matricule);
    List<Payment> findByPaymentStatus(PaymentStatus paymentStatus);
    List<Payment> findByPaymentType(PaymentType paymentType);

}
