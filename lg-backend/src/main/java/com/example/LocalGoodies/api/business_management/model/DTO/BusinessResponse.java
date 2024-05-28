package com.example.LocalGoodies.api.business_management.model.DTO;

import com.example.LocalGoodies.api.business_management.model.BusinessTypeEnum;

public record BusinessResponse(
        Long id,
        String name,
        String description,
        BusinessTypeEnum type,
        String phoneNumber,
        String email
) {

}
