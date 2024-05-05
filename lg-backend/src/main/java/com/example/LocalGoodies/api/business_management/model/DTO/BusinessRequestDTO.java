package com.example.LocalGoodies.api.business_management.model.DTO;

import com.example.LocalGoodies.api.business_management.model.BusinessTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record BusinessRequestDTO(
        @NotEmpty @NotBlank String name,
        @NotEmpty @NotBlank String description,
        BusinessTypeEnum type,
        String phoneNumber,
        String email
) {
}
