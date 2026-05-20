package br.com.vieiradev.financesApi.entities;

import br.com.vieiradev.financesApi.enums.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Expense {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal value;

    @Column(name = "expense_date", nullable = false)
    private LocalDate date;

}
