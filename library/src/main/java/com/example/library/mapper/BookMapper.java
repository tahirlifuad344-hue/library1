package com.example.library.mapper;

import com.example.library.dto.request.BookCreateRequest;
import com.example.library.dto.request.BookUpdateRequest;
import com.example.library.dto.response.BookResponse;
import com.example.library.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book toEntity(BookCreateRequest request) {
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setCategory(request.getCategory());
        book.setTotalCount(request.getTotalCount());
        book.setAvailableCount(request.getTotalCount());
        return book;
    }

    public void updateEntity(Book book, BookUpdateRequest request) {
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setCategory(request.getCategory());

        if (request.getTotalCount() != null) {
            int borrowedCount = book.getTotalCount() - book.getAvailableCount();
            book.setTotalCount(request.getTotalCount());
            book.setAvailableCount(request.getTotalCount() - borrowedCount);
        }
    }

    public BookResponse toResponse(Book book) {
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getCategory(),
                book.getTotalCount(),
                book.getAvailableCount()
        );
    }
}