package com.example.library.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingResponse {
    private Long id;
    private Long bookId;
    private String bookTitle;
    private Long readerId;
    private String readerName;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private Boolean returned;
}