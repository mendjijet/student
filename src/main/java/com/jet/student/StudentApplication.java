package com.jet.student;

import com.jet.student.entity.Payment;
import com.jet.student.entity.Student;
import com.jet.student.enums.PaymentStatus;
import com.jet.student.enums.PaymentType;
import com.jet.student.repository.PaymentRepository;
import com.jet.student.repository.StudentRepository;
import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StudentApplication {

  public static void main(String[] args) {
    SpringApplication.run(StudentApplication.class, args);
  }

  @Bean
  CommandLineRunner commandLineRunner(
      StudentRepository studentRepository, PaymentRepository paymentRepository) {
    return args -> {
      studentRepository.save(
          Student.builder()
              .id(UUID.randomUUID().toString())
              .firstName("Mendjijet")
              .lastName("jet")
              .matricule("24I00001")
              .programId("GI001")
              .build());
      studentRepository.save(
          Student.builder()
              .id(UUID.randomUUID().toString())
              .firstName("Essola")
              .lastName("Bienvenu")
              .matricule("24I00002")
              .programId("GL003")
              .build());
      studentRepository.save(
          Student.builder()
              .id(UUID.randomUUID().toString())
              .firstName("Dongfack")
              .lastName("Simon")
              .matricule("24I00003")
              .programId("GI002")
              .build());

      PaymentType[] paymentTypes = PaymentType.values();
      PaymentStatus[] paymentStatuses = PaymentStatus.values();
      Random random = new Random();
      studentRepository
          .findAll()
          .forEach(
              st -> {
                int index = random.nextInt(paymentTypes.length);
                  int index2 = random.nextInt(paymentStatuses.length);
                for (int i = 0; i < 10; i++) {
                  paymentRepository.save(
                      Payment.builder()
                          .amount(1000 + (int) (Math.random() * 20000))
                          .paymentType(paymentTypes[index])
                          .date(LocalDate.now())
                          .paymentStatus(paymentStatuses[index2])
                          .student(st)
                          .build());
                }
              });
    };
  }
}
