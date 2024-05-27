package com.jet.student.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Student {
  @Id private String id;
  private String firstName;
  private String lastName;
  @Column(unique = true)
  private String matricule;
  private String programId;
  private int age;
  private LocalDate dateOfBirth;
  private String gender;
  private String comment;
  private String photo;
}
