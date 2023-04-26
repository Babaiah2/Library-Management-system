package com.SpringMarch.Library_Management_System.Controller;

import com.SpringMarch.Library_Management_System.DTO.AuthorRequestDto;
import com.SpringMarch.Library_Management_System.DTO.AuthorResponseDto;
import com.SpringMarch.Library_Management_System.DTO.StudentResponseDto;
import com.SpringMarch.Library_Management_System.Entity.Author;
import com.SpringMarch.Library_Management_System.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;
    @PostMapping("/add")
    public AuthorResponseDto addAuthor(@RequestBody AuthorRequestDto authorRequestDto){
        return authorService.addAuthor(authorRequestDto);
    }

    @GetMapping("/get_authors")
    public List<Author> getAuthors(){
        return authorService.getAuthors();
    }


}
