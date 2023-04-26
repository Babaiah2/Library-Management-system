package com.SpringMarch.Library_Management_System.DTO;

import com.SpringMarch.Library_Management_System.Enum.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class BookRequestDto {

    private String title;

    private int price;

    private Genre genre;

    private int authorId;

}
