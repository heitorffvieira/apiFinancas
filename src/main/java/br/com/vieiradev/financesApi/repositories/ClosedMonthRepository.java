package br.com.vieiradev.financesApi.repositories;

import br.com.vieiradev.financesApi.entities.ClosedMonth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClosedMonthRepository extends JpaRepository<ClosedMonth, Long> {
    boolean existsByMonthAndYear(Integer month, Integer year);
    List<ClosedMonth> findAllByOrderByYearDescMonthDesc();
}
