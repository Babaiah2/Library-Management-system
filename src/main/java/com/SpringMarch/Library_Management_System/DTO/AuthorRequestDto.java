package com.SpringMarch.Library_Management_System.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorRequestDto {
    private String name;
    private int age;
    private String mblNo;
    private String email;
}
