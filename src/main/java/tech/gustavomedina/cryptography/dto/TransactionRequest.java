package tech.gustavomedina.cryptography.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TransactionRequest(
        @NotBlank(message = "User document can't be null.")
        String userDocument,
        @NotBlank(message = "Credit card token can't be null.")
        String creditCardToken,
        @NotNull(message = "Value can't be null.")
        @Positive(message = "Value must be positive.")
        Long value
        ) {
}
