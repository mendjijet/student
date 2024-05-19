package com.jet.student.entity;

import com.jet.student.enums.PaymentStatus;
import com.jet.student.enums.PaymentType;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private double amount;
    private PaymentType paymentType;
    private PaymentStatus paymentStatus;
    private String recuImage;
    @ManyToOne
    private Student student;
}
