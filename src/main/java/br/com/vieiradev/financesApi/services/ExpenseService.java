package br.com.vieiradev.financesApi.services;

import br.com.vieiradev.financesApi.dtos.ExpenseRequestDTO;
import br.com.vieiradev.financesApi.dtos.ExpenseResponseDTO;
import br.com.vieiradev.financesApi.entities.Expense;
import br.com.vieiradev.financesApi.exceptions.BusinessException;
import br.com.vieiradev.financesApi.exceptions.ResourceNotFoundException;
import br.com.vieiradev.financesApi.repositories.ClosedMonthRepository;
import br.com.vieiradev.financesApi.repositories.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ClosedMonthRepository closedMonthRepository;

    public ExpenseService(
            ExpenseRepository expenseRepository,
            ClosedMonthRepository closedMonthRepository
    ) {
        this.expenseRepository = expenseRepository;
        this.closedMonthRepository = closedMonthRepository;
    }

    private ExpenseResponseDTO toResponse(Expense expense) {

        return new ExpenseResponseDTO(
                expense.getId(),
                expense.getName(),
                expense.getCategory(),
                expense.getPaymentMethod(),
                expense.getValue(),
                expense.getDate()
        );
    }

    private Expense toEntity(ExpenseRequestDTO dto) {

        Expense expense = new Expense();

        expense.setName(dto.name());
        expense.setCategory(dto.category());
        expense.setValue(dto.value());
        expense.setPaymentMethod(dto.paymentMethod());
        expense.setDate(dto.date());

        return expense;
    }

    private void validateClosedMonth(LocalDate date) {
        boolean monthClosed = closedMonthRepository.existsByMonthAndYear(date.getMonthValue(), date.getYear());

        if(monthClosed) {
            throw new BusinessException("Mês encerrado.");
        }
    }

    public ExpenseResponseDTO createExpense(ExpenseRequestDTO dto) {
        validateClosedMonth(dto.date());

        Expense expense = toEntity(dto);
        Expense saved = expenseRepository.save(expense);

        return toResponse(saved);
    }

    public List<ExpenseResponseDTO> getAll() {
        return expenseRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<ExpenseResponseDTO> getExpensesByMonth(Integer month, Integer year) {

        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        List<Expense> expenses = expenseRepository.findByDateBetween(start, end);

        return expenses.stream()
                .map(this::toResponse)
                .toList();
    }

    private Expense findExpenseOrThrow(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Despesa não encontrada."));
    }

    public ExpenseResponseDTO update(Long id, ExpenseRequestDTO dto) {
        Expense expense = findExpenseOrThrow(id);

        validateClosedMonth(expense.getDate());
        validateClosedMonth(dto.date());

        expense.setName(dto.name());
        expense.setCategory(dto.category());
        expense.setPaymentMethod(dto.paymentMethod());
        expense.setValue(dto.value());
        expense.setDate(dto.date());

        Expense updated = expenseRepository.save(expense);
        return toResponse(updated);
    }

    public void delete(Long id) {
        Expense expense = findExpenseOrThrow(id);
        validateClosedMonth(expense.getDate());
        expenseRepository.delete(expense);
    }

    public ExpenseResponseDTO getById(Long id) {
        Expense expense = findExpenseOrThrow(id);
        return toResponse(expense);
    }
}
