package tech.gustavomedina.cryptography.dto;

import lombok.Builder;

@Builder
public record TransactionResponse(Long id, String userDocument, String creditCardToken, Long value) {
}
