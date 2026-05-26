package br.com.vieiradev.financesApi.dtos;

import br.com.vieiradev.financesApi.enums.Category;
import br.com.vieiradev.financesApi.enums.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseResponseDTO (
        Long id,
        String name,
        Category category,
        PaymentMethod paymentMethod,
        BigDecimal value,
        LocalDate date
) {}
