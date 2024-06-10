package com.example.LocalGoodies.api.business_management.model;

import com.example.LocalGoodies.api.business_management.model.DTO.BusinessResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class BusinessMapper {
    public static BusinessResponse mapEntityToResponse(Business entity) {
        return new BusinessResponse(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getType(),
                entity.getEmail(),
                entity.getPhoneNumber()
        );
    }

    public static List<BusinessResponse> mapEntitiesToResponse(List<Business> entities) {
        return entities.stream()
                .map(BusinessMapper::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    public static Page<BusinessResponse> mapEntitiesToResponse(Page<Business> entities) {
        return entities.map(BusinessMapper::mapEntityToResponse);
    }
}
