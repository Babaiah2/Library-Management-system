package com.SpringMarch.Library_Management_System.Repository;

import com.SpringMarch.Library_Management_System.Entity.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryCardRepository extends JpaRepository<LibraryCard,Integer> {
}
