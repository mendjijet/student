package com.jet.student.dto;

import com.jet.student.enums.PaymentType;
import java.time.LocalDate;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewPaymentDto {
  private LocalDate date;
  private double amount;
  private PaymentType type;
  private String matricule;
}
