package com.jet.student.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class NewStudentDto {
    private String id;
    private String firstName;
    private String lastName;
    private String matricule;
    private String programId;
    private int age;
    private LocalDate dateOfBirth;
    private String gender;
    private String comment;
    private String photo;
}
