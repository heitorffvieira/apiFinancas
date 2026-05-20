package br.com.vieiradev.financesApi.controllers;

import br.com.vieiradev.financesApi.dtos.ClosedMonthRequest;
import br.com.vieiradev.financesApi.entities.ClosedMonth;
import br.com.vieiradev.financesApi.services.ClosedMonthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/months")
public class ClosedMonthController {

    private final ClosedMonthService closedMonthService;

    public ClosedMonthController(ClosedMonthService closedMonthService) {
        this.closedMonthService = closedMonthService;
    }

    @GetMapping
    public ResponseEntity<List<ClosedMonth>> getHistory() {
        return ResponseEntity.ok(closedMonthService.getHistory());
    }

    @PostMapping
    public ResponseEntity<ClosedMonth> create(@Valid @RequestBody ClosedMonthRequest dto) {
        ClosedMonth created = closedMonthService.create(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClosedMonth> getById(@PathVariable Long id) {
        return ResponseEntity.ok(closedMonthService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        closedMonthService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
