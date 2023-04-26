package com.SpringMarch.Library_Management_System.Service;

import com.SpringMarch.Library_Management_System.DTO.AuthorRequestDto;
import com.SpringMarch.Library_Management_System.DTO.AuthorResponseDto;
import com.SpringMarch.Library_Management_System.DTO.StudentResponseDto;
import com.SpringMarch.Library_Management_System.Entity.Author;
import com.SpringMarch.Library_Management_System.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto) {

        Author author = new Author();
        //setting values  author;
        author.setName(authorRequestDto.getName());
        author.setAge(authorRequestDto.getAge());
        author.setEmail(authorRequestDto.getEmail());
        author.setMblNo(authorRequestDto.getMblNo());

        authorRepository.save(author);

        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        authorResponseDto.setAge(author.getAge());
        authorResponseDto.setName(author.getName());
        authorResponseDto.setEmail(author.getEmail());
        authorResponseDto.setMblNo(author.getMblNo());

        return authorResponseDto;
    }

    public List<Author> getAuthors(){
        return authorRepository.findAll();
    }
}
