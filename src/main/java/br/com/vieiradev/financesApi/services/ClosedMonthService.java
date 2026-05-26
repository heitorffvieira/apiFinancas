package br.com.vieiradev.financesApi.services;

import br.com.vieiradev.financesApi.dtos.ClosedMonthRequest;
import br.com.vieiradev.financesApi.entities.ClosedMonth;
import br.com.vieiradev.financesApi.exceptions.BusinessException;
import br.com.vieiradev.financesApi.exceptions.ResourceNotFoundException;
import br.com.vieiradev.financesApi.repositories.ClosedMonthRepository;
import br.com.vieiradev.financesApi.repositories.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClosedMonthService {

    private final ClosedMonthRepository closedMonthRepository;
    private final ExpenseRepository expenseRepository;

    public ClosedMonthService(ClosedMonthRepository closedMonthRepository, ExpenseRepository expenseRepository) {
        this.closedMonthRepository = closedMonthRepository;
        this.expenseRepository = expenseRepository;
    }

    public ClosedMonth create(ClosedMonthRequest dto) {

        boolean alreadyClosed = closedMonthRepository.existsByMonthAndYear(dto.month(), dto.year());

        if(alreadyClosed) {
            throw new BusinessException("Mês já encerrado.");
        }

        LocalDate start = LocalDate.of(
                dto.year(),
                dto.month(),
                1
        );

        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        boolean hasExpenses = expenseRepository.existsByDateBetween(start, end);

        if(!hasExpenses) {
            throw new BusinessException("Não existem despesas neste mês.");
        }

        ClosedMonth closedMonth = new ClosedMonth();

        closedMonth.setMonth(dto.month());
        closedMonth.setYear(dto.year());
        closedMonth.setClosedAt(LocalDate.now());

        return closedMonthRepository.save(closedMonth);
    }

    public List<ClosedMonth> getHistory() {
        return closedMonthRepository.findAllByOrderByYearDescMonthDesc();
    }

    private ClosedMonth findMonthOrThrow(Long id) {
        return closedMonthRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mês não encontrado."));
    }

    public void delete(Long id) {
        ClosedMonth closedMonth = findMonthOrThrow(id);
        closedMonthRepository.delete(closedMonth);
    }

    public ClosedMonth getById(Long id) {
        return findMonthOrThrow(id);
    }

    public void reopen(Long id) {
        ClosedMonth closedMonth = findMonthOrThrow(id);

        ClosedMonth lastClosed = closedMonthRepository
                .findTopByOrderByYearDescMonthDesc()
                .orElseThrow(() ->
                        new BusinessException("Não existem meses fechados."));

        boolean isLastMonth = closedMonth.getId().equals(lastClosed.getId());

        if(!isLastMonth) {
            throw new BusinessException("Apenas o último mês fechado pode ser reaberto.");
        }

        closedMonthRepository.delete(closedMonth);
    }

}
