package com.example.LocalGoodies.api.business_management.business_listing;

import com.example.LocalGoodies.api.business_management.model.Business;
import com.example.LocalGoodies.api.business_management.model.BusinessTypeEnum;
import com.example.LocalGoodies.api.business_management.model.DTO.BusinessRequestDTO;

import java.util.List;

public interface BusinessListingService {
    List<Business> getAllActiveBusinesses();
    List<Business> getByType(BusinessTypeEnum type);
    Business addNew(BusinessRequestDTO businessRequestDTO);
    Business update(Long id, BusinessRequestDTO businessRequestDTO);
}
