package com.example.LocalGoodies.api.business_management.model;

import com.example.LocalGoodies.api.business_management.model.DTO.BusinessResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class BusinessMapper {
    public static BusinessResponseDTO mapEntityToResponseDto(Business entity) {
        return new BusinessResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getType(),
                entity.getEmail(),
                entity.getPhoneNumber()
        );
    }

    public static List<BusinessResponseDTO> mapEntitiesToResponseDtos(List<Business> entities) {
        return entities.stream()
                .map(BusinessMapper::mapEntityToResponseDto)
                .collect(Collectors.toList());
    }

    public static Page<BusinessResponseDTO> mapEntitiesToResponseDtos(Page<Business> entities) {
        return entities.map(BusinessMapper::mapEntityToResponseDto);
    }
}
