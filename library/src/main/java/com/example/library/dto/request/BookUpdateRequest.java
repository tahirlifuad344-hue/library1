package com.example.library.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookUpdateRequest {

    @NotBlank(message = "Başlıq tələb olunur")
    private String title;

    @NotBlank(message = "Müəllif tələb olunur")
    private String author;

    private String category;

    @Min(value = 1, message = "Say minimum 1 olmalıdır")
    private Integer totalCount;
}