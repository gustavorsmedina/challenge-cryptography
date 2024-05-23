package tech.gustavomedina.cryptography.mapper;

import org.springframework.stereotype.Component;
import tech.gustavomedina.cryptography.dto.TransactionResponse;
import tech.gustavomedina.cryptography.entity.TransactionEntity;

@Component
public class TransactionMapper {

    public TransactionResponse toTransactionResponse(TransactionEntity transactionEntity){

        return TransactionResponse.builder()
                .id(transactionEntity.getId())
                .userDocument(transactionEntity.getUserDocument())
                .creditCardToken(transactionEntity.getCreditCardToken())
                .value(transactionEntity.getValue())
                .build();
    }

}
