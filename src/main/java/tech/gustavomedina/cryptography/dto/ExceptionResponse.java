package tech.gustavomedina.cryptography.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ExceptionResponse(LocalDateTime date, String method, int status, String message, String path) {
}
