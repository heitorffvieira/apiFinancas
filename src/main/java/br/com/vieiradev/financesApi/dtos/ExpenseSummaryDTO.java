package br.com.vieiradev.financesApi.dtos;

import br.com.vieiradev.financesApi.enums.Category;

import java.math.BigDecimal;
import java.util.Map;

public record ExpenseSummaryDTO(

        BigDecimal total,
        BigDecimal totalCredit,
        BigDecimal totalImmediate,

        Map<Category, BigDecimal> categories

) {
}
