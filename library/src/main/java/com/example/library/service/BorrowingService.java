package com.example.library.service;

import com.example.library.dto.request.BorrowRequest;
import com.example.library.entity.Book;
import com.example.library.entity.Borrowing;
import com.example.library.entity.Reader;
import com.example.library.exception.BookNotAvailableException;
import com.example.library.exception.BookNotFoundException;
import com.example.library.exception.BorrowingNotFoundException;
import com.example.library.exception.ReaderNotFoundException;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowingRepository;
import com.example.library.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowingService {

    private final BorrowingRepository borrowingRepository;
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;

    @Transactional
    public Borrowing borrowBook(BorrowRequest request) {
        Book book = bookRepository.findByIdWithLock(request.getBookId())
                .orElseThrow(() -> new BookNotFoundException("Kitab tapılmadı: " + request.getBookId()));

        Reader reader = readerRepository.findById(request.getReaderId())
                .orElseThrow(() -> new ReaderNotFoundException("Oxucu tapılmadı: " + request.getReaderId()));

        if (book.getAvailableCount() <= 0) {
            throw new BookNotAvailableException("Kitab əlçatan deyil: " + book.getTitle());
        }

        book.setAvailableCount(book.getAvailableCount() - 1);
        bookRepository.save(book);

        Borrowing borrowing = new Borrowing();
        borrowing.setBook(book);
        borrowing.setReader(reader);
        borrowing.setBorrowDate(LocalDate.now());
        borrowing.setReturned(false);

        return borrowingRepository.save(borrowing);
    }

    @Transactional
    public Borrowing returnBook(Long borrowingId) {
        Borrowing borrowing = borrowingRepository.findById(borrowingId)
                .orElseThrow(() -> new BorrowingNotFoundException("Borrowing tapılmadı: " + borrowingId));

        if (Boolean.TRUE.equals(borrowing.getReturned())) {
            throw new IllegalStateException("Bu kitab artıq qaytarılıb");
        }

        borrowing.setReturned(true);
        borrowing.setReturnDate(LocalDate.now());

        Book book = borrowing.getBook();
        book.setAvailableCount(book.getAvailableCount() + 1);
        bookRepository.save(book);

        return borrowingRepository.save(borrowing);
    }

    public List<Borrowing> getActiveBorrowings() {
        return borrowingRepository.findByReturnedFalse();
    }

    public List<Borrowing> getOverdueBorrowings(int daysLimit) {
        LocalDate limitDate = LocalDate.now().minusDays(daysLimit);
        return borrowingRepository.findByReturnedFalseAndBorrowDateBefore(limitDate);
    }

    public List<Borrowing> getAllBorrowings() {
        return borrowingRepository.findAll();
    }
}