package dev.sorokin.eventmanager.core.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ErrorMessageResponse(
        String message,
        String detailedMessage,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dateTime
) {
    public ErrorMessageResponse(String message, String detailedMessage) {
        this(message, detailedMessage, LocalDateTime.now());
    }
}
