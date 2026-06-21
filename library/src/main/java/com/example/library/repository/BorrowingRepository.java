package com.example.library.repository;

import com.example.library.entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {

    List<Borrowing> findByReturnedFalse();

    List<Borrowing> findByReaderId(Long readerId);

    List<Borrowing> findByBookIdAndReturnedFalse(Long bookId);

    List<Borrowing> findByReturnedFalseAndBorrowDateBefore(LocalDate date);
}