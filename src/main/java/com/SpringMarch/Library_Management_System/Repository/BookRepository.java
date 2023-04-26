package com.SpringMarch.Library_Management_System.Repository;

import com.SpringMarch.Library_Management_System.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
}
