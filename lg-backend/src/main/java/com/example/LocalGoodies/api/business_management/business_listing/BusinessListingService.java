package com.example.LocalGoodies.api.business_management.business_listing;

import com.example.LocalGoodies.api.business_management.model.Business;
import com.example.LocalGoodies.api.business_management.model.BusinessTypeEnum;
import com.example.LocalGoodies.api.business_management.model.DTO.BusinessRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BusinessListingService {
    Page<Business> getPagedByType(Pageable pageable, BusinessTypeEnum type);
    Page<Business> getPaged(Pageable pageable);

    List<Business> getByNameStartsWith(String name);
    Business addNew(BusinessRequestDTO businessRequestDTO);
    Business update(Long id, BusinessRequestDTO businessRequestDTO);
}
