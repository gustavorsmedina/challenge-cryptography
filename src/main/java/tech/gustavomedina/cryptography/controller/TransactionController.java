package tech.gustavomedina.cryptography.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.gustavomedina.cryptography.dto.TransactionRequest;
import tech.gustavomedina.cryptography.dto.TransactionResponse;
import tech.gustavomedina.cryptography.service.TransactionService;

@RestController
@RequestMapping("/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody @Valid TransactionRequest transactionRequest){
        var transaction = transactionService.createTransaction(transactionRequest);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(transaction)
                .toUri())
                .build();
    }

    @GetMapping
    public ResponseEntity<Page<TransactionResponse>> findAllTransactions(Pageable pageable){
        var transactions = transactionService.findAllTransactions(pageable);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> findTransactionById(@PathVariable("id") Long id){
        var transaction = transactionService.findTransactionById(id);
        return ResponseEntity.ok(transaction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTransactionById(@PathVariable("id") Long id, @RequestBody @Valid TransactionRequest transactionRequest){
        transactionService.updateTransactionById(id, transactionRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransactionById(@PathVariable("id") Long id){
        transactionService.deleteTransactionById(id);
        return ResponseEntity.noContent().build();
    }

}
