package com.example.library.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReaderUpdateRequest {

    @NotBlank(message = "Ad soyad tələb olunur")
    private String fullName;

    @NotBlank(message = "Telefon tələb olunur")
    private String phone;

    @Email(message = "Email formatı düzgün deyil")
    private String email;
}