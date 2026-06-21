package com.example.library.service;

import com.example.library.dto.request.ReaderCreateRequest;
import com.example.library.dto.request.ReaderUpdateRequest;
import com.example.library.entity.Borrowing;
import com.example.library.entity.Reader;
import com.example.library.exception.ReaderNotFoundException;
import com.example.library.mapper.ReaderMapper;
import com.example.library.repository.BorrowingRepository;
import com.example.library.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderService {

    private final ReaderRepository readerRepository;
    private final ReaderMapper readerMapper;
    private final BorrowingRepository borrowingRepository;

    public Reader createReader(ReaderCreateRequest request) {
        Reader reader = readerMapper.toEntity(request);
        return readerRepository.save(reader);
    }

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public Reader getReaderById(Long id) {
        return readerRepository.findById(id)
                .orElseThrow(() -> new ReaderNotFoundException("Oxucu tapılmadı: " + id));
    }

    public Reader updateReader(Long id, ReaderUpdateRequest request) {
        Reader reader = getReaderById(id);
        readerMapper.updateEntity(reader, request);
        return readerRepository.save(reader);
    }

    public void deleteReader(Long id) {
        Reader reader = getReaderById(id);

        List<Borrowing> borrowings = borrowingRepository.findByReaderId(id);
        if (!borrowings.isEmpty()) {
            throw new IllegalStateException("Bu oxucunun borrowing tarixçəsi var, silmək mümkün deyil");
        }

        readerRepository.delete(reader);
    }
}