package br.com.vieiradev.financesApi.controllers;

import br.com.vieiradev.financesApi.dtos.ExpenseRequestDTO;
import br.com.vieiradev.financesApi.dtos.ExpenseResponseDTO;
import br.com.vieiradev.financesApi.services.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponseDTO>> getAll() {
        return ResponseEntity.ok(expenseService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.getById(id));
    }

    @GetMapping("/month")
    public ResponseEntity<List<ExpenseResponseDTO>> getExpensesByMonth(@RequestParam Integer month, @RequestParam Integer year) {
        return ResponseEntity.ok(expenseService.getExpensesByMonth(month, year));
    }

    @PostMapping
    public ResponseEntity<ExpenseResponseDTO> createExpense(@Valid @RequestBody ExpenseRequestDTO dto) {
        ExpenseResponseDTO response = expenseService.createExpense(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> update(@Valid @RequestBody ExpenseRequestDTO dto, @PathVariable Long id) {
        return ResponseEntity.ok(expenseService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        expenseService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
