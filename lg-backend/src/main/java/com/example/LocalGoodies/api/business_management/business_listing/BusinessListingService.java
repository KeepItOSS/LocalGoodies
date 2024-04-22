package com.example.LocalGoodies.api.business_management.business_listing;

import com.example.LocalGoodies.api.business_management.model.Business;
import com.example.LocalGoodies.api.business_management.model.BusinessTypeEnum;

import java.util.List;

public interface BusinessListingService {
    List<Business> getAllBusinesses();
    List<Business> getByType(BusinessTypeEnum type);
}
