package com.SpringMarch.Library_Management_System.Repository;

import com.SpringMarch.Library_Management_System.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    List<Author> findAll();
}
