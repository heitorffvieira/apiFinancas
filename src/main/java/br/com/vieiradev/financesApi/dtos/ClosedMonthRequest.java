package br.com.vieiradev.financesApi.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ClosedMonthRequest(

        @NotNull(message = "Informar mês é obrigatório.")
        @Min(value = 1, message = "Mês inválido.")
        @Max(value = 12, message = "Mês inválido.")
        Integer month,

        @NotNull(message = "Informar ano é obrigatório.")
        Integer year

) {}
