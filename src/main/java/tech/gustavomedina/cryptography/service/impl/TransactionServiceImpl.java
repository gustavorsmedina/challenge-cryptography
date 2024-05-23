package tech.gustavomedina.cryptography.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.gustavomedina.cryptography.dto.TransactionRequest;
import tech.gustavomedina.cryptography.dto.TransactionResponse;
import tech.gustavomedina.cryptography.entity.TransactionEntity;
import tech.gustavomedina.cryptography.exception.TransactionNotFoundException;
import tech.gustavomedina.cryptography.mapper.TransactionMapper;
import tech.gustavomedina.cryptography.repository.TransactionRepository;
import tech.gustavomedina.cryptography.service.TransactionService;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AES256TextEncryptor aes256TextEncryptor;

    @Override
    public TransactionResponse createTransaction(TransactionRequest transactionRequest){

        log.info("Encrypting and creating a new transaction...");

        var userDocument = aes256TextEncryptor.encrypt(transactionRequest.userDocument());
        var creditCardToken = aes256TextEncryptor.encrypt(transactionRequest.creditCardToken());

        var encryptedTransaction = TransactionEntity.builder()
                .userDocument(userDocument)
                .creditCardToken(creditCardToken)
                .value(transactionRequest.value())
                .build();

        var transaction = transactionRepository.save(encryptedTransaction);

        log.info("Transaction created with ID: {}", transaction.getId());

        return transactionMapper.toTransactionResponse(transaction);
    }

    @Override
    public Page<TransactionResponse> findAllTransactions(Pageable pageable){

        log.info("Returning all transactions...");

        return transactionRepository.findAll(pageable).map(transactionMapper::toTransactionResponse);
    }

    @Override
    public TransactionResponse findTransactionById(Long id){

        log.info("Searching transaction with ID: {}", id);

        var transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction ID doesn't exists."));

        log.info("Transaction found by ID: {}", id);

        return transactionMapper.toTransactionResponse(transaction);
    }

    @Override
    public void updateTransactionById(Long id, TransactionRequest transactionRequest){

        var transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction ID doesn't exists."));

        log.info("Updating transaction with ID: {}", id);

        transaction.setUserDocument(aes256TextEncryptor.encrypt(transactionRequest.userDocument()));
        transaction.setCreditCardToken(aes256TextEncryptor.encrypt(transactionRequest.creditCardToken()));
        transaction.setValue(transactionRequest.value());

        transactionRepository.save(transaction);

        log.info("Transaction updated with ID: {}", id);
    }

    @Override
    public void deleteTransactionById(Long id) {
        log.info("Deleting transaction with ID: {}", id);

        var transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction ID doesn't exists."));

        transactionRepository.delete(transaction);

        log.info("Transaction deleted with ID: {}", id);
    }

}
