package com.example.LocalGoodies.api.business_management.model.DTO;

import com.example.LocalGoodies.api.business_management.model.BusinessTypeEnum;

public record BusinessResponseDTO(
        String name, String description,
        BusinessTypeEnum type, String phoneNumber,
        String email) {

}
