package com.example.library.service;

import com.example.library.dto.request.BookCreateRequest;
import com.example.library.dto.request.BookUpdateRequest;
import com.example.library.entity.Book;
import com.example.library.entity.Borrowing;
import com.example.library.exception.BookNotFoundException;
import com.example.library.mapper.BookMapper;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BorrowingRepository borrowingRepository;

    public Book createBook(BookCreateRequest request) {
        Book book = bookMapper.toEntity(request);
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Kitab tapılmadı: " + id));
    }

    public Book updateBook(Long id, BookUpdateRequest request) {
        Book book = getBookById(id);
        bookMapper.updateEntity(book, request);
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        Book book = getBookById(id);

        List<Borrowing> borrowings = borrowingRepository.findByBookIdAndReturnedFalse(id);
        if (!borrowings.isEmpty()) {
            throw new IllegalStateException("Bu kitab hal-hazırda götürülüb, silmək mümkün deyil");
        }

        bookRepository.delete(book);
    }
}