package com.SpringMarch.Library_Management_System.Service;

import com.SpringMarch.Library_Management_System.DTO.IssueBookRequestDto;
import com.SpringMarch.Library_Management_System.DTO.IssueBookResponseDto;
import com.SpringMarch.Library_Management_System.Entity.Book;
import com.SpringMarch.Library_Management_System.Entity.LibraryCard;
import com.SpringMarch.Library_Management_System.Entity.Transaction;
import com.SpringMarch.Library_Management_System.Enum.CardStatus;
import com.SpringMarch.Library_Management_System.Enum.TransactionStatus;
import com.SpringMarch.Library_Management_System.Repository.BookRepository;
import com.SpringMarch.Library_Management_System.Repository.LibraryCardRepository;
import com.SpringMarch.Library_Management_System.Repository.TransactionRepository;
import com.fasterxml.jackson.annotation.JacksonAnnotationValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController

public class TransactionService {


    @Autowired
    LibraryCardRepository libraryCardRepository;

    @Autowired
    BookRepository bookRepository;
   @Autowired
    TransactionRepository transactionRepository;

   @Autowired
   private JavaMailSender emailSender;
    public IssueBookResponseDto issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception {

        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setIssuedOperation(true);

        //Either library card is present or not
        LibraryCard card;
         try{
              card = libraryCardRepository.findById(issueBookRequestDto.getCardId()).get();
         }
         catch(Exception e){
             transaction.setTransactionStatus(TransactionStatus.FAILED);
             transaction.setMessage("Invalid cardId");
             transactionRepository.save(transaction);
             throw new Exception("Invalid CardId");
         }

         // Either book is present or not
         Book book;
         try{
              book = bookRepository.findById(issueBookRequestDto.getBookId()).get();
         }
         catch(Exception e){
             transaction.setTransactionStatus(TransactionStatus.FAILED);
             transaction.setMessage("Invalid book ID");
             transactionRepository.save(transaction);
             throw new Exception(" No book is present with this book id");
         }
         //card and book are valid
         transaction.setBook(book);
         transaction.setCard(card);

          //Either card is active or not
         if(card.getStatus()!= CardStatus.ACTIVATED){
             transaction.setTransactionStatus(TransactionStatus.FAILED);
             transaction.setMessage("card is not active");
             transactionRepository.save(transaction);
              throw new Exception("Card Status is Not Active");
         }

         if(book.isIssued()==true){
             transaction.setTransactionStatus(TransactionStatus.FAILED);
             transaction.setMessage("Book is Already Issued");
             transactionRepository.save(transaction);
             throw new Exception("Sorry! Book is Already Issued");
         }

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
         transaction.setMessage("transaction was successful");
         book.setIssued(true);
         book.setCard(card);
         book.getTransactions().add(transaction);
         card.getTransactionList().add(transaction);
         card.getBooksIssued().add(book);

         libraryCardRepository.save(card);//will save book and transaction

        //responseDto
        IssueBookResponseDto issueBookResponseDto = new IssueBookResponseDto();
        issueBookResponseDto.setTransactionId(transaction.getTransactionNumber());
        issueBookResponseDto.setTransactionStatus(TransactionStatus.SUCCESS);
        issueBookResponseDto.setBookName(book.getTitle());

        String text="Hi!"+card.getStudent().getName()+ "you have been issued "+book.getTitle()+" book.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("babaiahsanjamala5@gmail.com");
        message.setTo(card.getStudent().getEmail());
        message.setSubject("issue book notification");
        message.setText(text);
        emailSender.send(message);

        return issueBookResponseDto;


    }
}
