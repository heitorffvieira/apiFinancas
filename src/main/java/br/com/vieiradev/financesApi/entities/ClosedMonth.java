package br.com.vieiradev.financesApi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(
        name = "closed_months",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"month", "year"})
        }
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClosedMonth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference_month", nullable = false)
    private Integer month;

    @Column(name = "reference_year", nullable = false)
    private Integer year;

    @Column(nullable = false)
    private LocalDate closedAt;
}