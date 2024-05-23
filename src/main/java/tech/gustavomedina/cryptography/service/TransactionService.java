package tech.gustavomedina.cryptography.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tech.gustavomedina.cryptography.dto.TransactionRequest;
import tech.gustavomedina.cryptography.dto.TransactionResponse;

public interface TransactionService {

    TransactionResponse createTransaction(TransactionRequest transactionRequest);
    Page<TransactionResponse> findAllTransactions(Pageable pageable);
    TransactionResponse findTransactionById(Long id);
    void updateTransactionById(Long id, TransactionRequest transactionRequest);
    void deleteTransactionById(Long id);

}
