package br.com.vieiradev.financesApi.dtos;

import br.com.vieiradev.financesApi.enums.Category;
import br.com.vieiradev.financesApi.enums.PaymentMethod;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseRequestDTO (

        @NotBlank(message = "Nome da despesa é obrigatório")
        String name,

        @NotNull(message = "Categoria é obrigatório")
        Category category,

        @NotNull(message = "Valor da despesa é obrigatório")
        @DecimalMin(value = "0.01", message = "Valor deve ser maior que zero")
        BigDecimal value,

        @NotNull(message = "Método de pagamento é obrigatório")
        PaymentMethod paymentMethod,

        @NotNull(message = "Data da despesa é obrigatório")
        LocalDate date

) {}
