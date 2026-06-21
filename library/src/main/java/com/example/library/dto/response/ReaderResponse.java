package com.example.library.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReaderResponse {
    private Long id;
    private String fullName;
    private String phone;
    private String email;
}