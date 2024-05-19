package com.jet.student.web;

import com.jet.student.dto.NewPaymentDto;
import com.jet.student.entity.Payment;
import com.jet.student.entity.Student;
import com.jet.student.enums.PaymentStatus;
import com.jet.student.enums.PaymentType;
import com.jet.student.repository.PaymentRepository;
import com.jet.student.repository.StudentRepository;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("*")
public class PaymentRestController {
  private StudentRepository studentRepository;
  private PaymentRepository paymentRepository;

  public PaymentRestController(
      StudentRepository studentRepository, PaymentRepository paymentRepository) {
    this.studentRepository = studentRepository;
    this.paymentRepository = paymentRepository;
  }

  @GetMapping(path = "/payments")
  public List<Payment> allPayements() {
    return paymentRepository.findAll();
  }

  @GetMapping(path = "/student/{matricule}/payments")
  public List<Payment> getPayementsByStudent(@PathVariable String matricule) {
    return paymentRepository.findByStudentMatricule(matricule);
  }

  @GetMapping(path = "/payments/byStatus")
  public List<Payment> getPayementsByStatus(@RequestParam PaymentStatus paymentStatus) {
    return paymentRepository.findByPaymentStatus(paymentStatus);
  }

  @GetMapping(path = "/payments/byType")
  public List<Payment> getPayementsByType(@RequestParam PaymentType paymentType) {
    return paymentRepository.findByPaymentType(paymentType);
  }

  @GetMapping(path = "/payment/{id}")
  public Payment getPayementById(@PathVariable Long id) {
    return paymentRepository.findById(id).get();
  }

  @GetMapping(path = "/students")
  public List<Student> allStudents() {
    return studentRepository.findAll();
  }

  @GetMapping(path = "/student/{matricule}")
  public Student getStudentByMatricule(@PathVariable String matricule) {
    return studentRepository.findByMatricule(matricule);
  }

  @GetMapping(path = "/studentByProgramId")
  public List<Student> getStudentByProgramId(@RequestParam String programId) {
    return studentRepository.findByProgramId(programId);
  }

  @PutMapping(path = "/payment/{id}")
  public Payment updatePayment(@RequestParam PaymentStatus paymentStatus, @PathVariable Long id) {
    Payment payment = paymentRepository.findById(id).get();
    payment.setPaymentStatus(paymentStatus);
    return paymentRepository.save(payment);
  }

  @PostMapping(path = "/payment", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public Payment savePayment(
      @RequestParam("file") MultipartFile file,
      NewPaymentDto newPaymentDto)
      throws IOException {
    Path folderPath = Paths.get(System.getProperty("user.home"), "student", "payments");
    if (!Files.exists(folderPath)) {
      Files.createDirectories(folderPath);
    }

    String fileName = UUID.randomUUID().toString();
    Path filePath =
        Paths.get(System.getProperty("user.home"), "student", "payments", fileName + ".png");
    Files.copy(file.getInputStream(), filePath);
    return paymentRepository.save(
        Payment.builder()
            .student(studentRepository.findByMatricule(newPaymentDto.getMatricule()))
            .amount(newPaymentDto.getAmount())
            .paymentType(newPaymentDto.getType())
            .date(newPaymentDto.getDate())
            .paymentStatus(PaymentStatus.CREATED)
            .recuImage(filePath.toUri().toString())
            .build());
  }

  @GetMapping(path = "paymentFile/{paymentId}", produces = MediaType.IMAGE_PNG_VALUE)
  public byte[] getPaymentFile(@PathVariable Long paymentId) throws IOException {
    return Files.readAllBytes(Path.of(URI.create(paymentRepository.findById(paymentId).get().getRecuImage())));
  }
}
