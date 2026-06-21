package com.example.library.controller;

import com.example.library.dto.request.BorrowRequest;
import com.example.library.dto.response.BorrowingResponse;
import com.example.library.entity.Borrowing;
import com.example.library.mapper.BorrowingMapper;
import com.example.library.service.BorrowingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/borrowings")
@RequiredArgsConstructor
public class BorrowingController {

    private final BorrowingService borrowingService;
    private final BorrowingMapper borrowingMapper;

    @PostMapping
    public ResponseEntity<BorrowingResponse> borrowBook(@Valid @RequestBody BorrowRequest request) {
        Borrowing borrowing = borrowingService.borrowBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(borrowingMapper.toResponse(borrowing));
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<BorrowingResponse> returnBook(@PathVariable Long id) {
        Borrowing borrowing = borrowingService.returnBook(id);
        return ResponseEntity.ok(borrowingMapper.toResponse(borrowing));
    }

    @GetMapping("/active")
    public ResponseEntity<List<BorrowingResponse>> getActiveBorrowings() {
        List<BorrowingResponse> result = borrowingService.getActiveBorrowings().stream()
                .map(borrowingMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<BorrowingResponse>> getOverdueBorrowings(
            @RequestParam(defaultValue = "14") int daysLimit) {
        List<BorrowingResponse> result = borrowingService.getOverdueBorrowings(daysLimit).stream()
                .map(borrowingMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<BorrowingResponse>> getAllBorrowings() {
        List<BorrowingResponse> result = borrowingService.getAllBorrowings().stream()
                .map(borrowingMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }
}