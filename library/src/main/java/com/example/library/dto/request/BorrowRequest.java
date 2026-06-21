package com.example.library.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowRequest {

    @NotNull(message = "Kitab ID tələb olunur")
    private Long bookId;

    @NotNull(message = "Oxucu ID tələb olunur")
    private Long readerId;
}