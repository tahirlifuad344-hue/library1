package com.example.library.controller;

import com.example.library.dto.request.ReaderCreateRequest;
import com.example.library.dto.request.ReaderUpdateRequest;
import com.example.library.dto.response.ReaderResponse;
import com.example.library.entity.Reader;
import com.example.library.mapper.ReaderMapper;
import com.example.library.service.ReaderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/readers")
@RequiredArgsConstructor
public class ReaderController {

    private final ReaderService readerService;
    private final ReaderMapper readerMapper;

    @PostMapping
    public ResponseEntity<ReaderResponse> createReader(@Valid @RequestBody ReaderCreateRequest request) {
        Reader reader = readerService.createReader(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(readerMapper.toResponse(reader));
    }

    @GetMapping
    public ResponseEntity<List<ReaderResponse>> getAllReaders() {
        List<ReaderResponse> readers = readerService.getAllReaders().stream()
                .map(readerMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(readers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReaderResponse> getReaderById(@PathVariable Long id) {
        Reader reader = readerService.getReaderById(id);
        return ResponseEntity.ok(readerMapper.toResponse(reader));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReaderResponse> updateReader(
            @PathVariable Long id,
            @Valid @RequestBody ReaderUpdateRequest request) {
        Reader reader = readerService.updateReader(id, request);
        return ResponseEntity.ok(readerMapper.toResponse(reader));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReader(@PathVariable Long id) {
        readerService.deleteReader(id);
        return ResponseEntity.noContent().build();
    }
}