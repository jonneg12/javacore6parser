package ru.netology;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private String country;
    private int age;

}