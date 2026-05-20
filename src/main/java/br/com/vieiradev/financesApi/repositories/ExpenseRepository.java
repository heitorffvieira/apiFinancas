package br.com.vieiradev.financesApi.repositories;

import br.com.vieiradev.financesApi.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByDateBetween(
            LocalDate start,
            LocalDate end
    );

    boolean existsByDateBetween(
            LocalDate start,
            LocalDate end
    );

}
