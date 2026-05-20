package br.com.vieiradev.financesApi.dtos;

import br.com.vieiradev.financesApi.enums.Category;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseResponseDTO (
        Long id,
        String name,
        Category category,
        BigDecimal value,
        LocalDate date
) {}
