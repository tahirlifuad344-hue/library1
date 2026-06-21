package com.example.library.mapper;

import com.example.library.dto.request.ReaderCreateRequest;
import com.example.library.dto.request.ReaderUpdateRequest;
import com.example.library.dto.response.ReaderResponse;
import com.example.library.entity.Reader;
import org.springframework.stereotype.Component;

@Component
public class ReaderMapper {

    public Reader toEntity(ReaderCreateRequest request) {
        Reader reader = new Reader();
        reader.setFullName(request.getFullName());
        reader.setPhone(request.getPhone());
        reader.setEmail(request.getEmail());
        return reader;
    }

    public void updateEntity(Reader reader, ReaderUpdateRequest request) {
        reader.setFullName(request.getFullName());
        reader.setPhone(request.getPhone());
        reader.setEmail(request.getEmail());
    }

    public ReaderResponse toResponse(Reader reader) {
        return new ReaderResponse(
                reader.getId(),
                reader.getFullName(),
                reader.getPhone(),
                reader.getEmail()
        );
    }
}