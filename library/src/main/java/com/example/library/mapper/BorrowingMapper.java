package com.example.library.mapper;

import com.example.library.dto.response.BorrowingResponse;
import com.example.library.entity.Borrowing;
import org.springframework.stereotype.Component;

@Component
public class BorrowingMapper {

    public BorrowingResponse toResponse(Borrowing borrowing) {
        return new BorrowingResponse(
                borrowing.getId(),
                borrowing.getBook().getId(),
                borrowing.getBook().getTitle(),
                borrowing.getReader().getId(),
                borrowing.getReader().getFullName(),
                borrowing.getBorrowDate(),
                borrowing.getReturnDate(),
                borrowing.getReturned()
        );
    }
}