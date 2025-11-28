package com.pasteleria.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ApiKeyService {

    @Value("${app.admin.api-key}")
    private String adminApiKey;

    /**
     * Valida que la API key enviada en el header sea la correcta.
     * Si no lo es, lanza 403 FORBIDDEN.
     */
    public void validate(String providedApiKey) {
        if (providedApiKey == null || !providedApiKey.equals(adminApiKey)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "API key inválida o ausente"
            );
        }
    }
}
