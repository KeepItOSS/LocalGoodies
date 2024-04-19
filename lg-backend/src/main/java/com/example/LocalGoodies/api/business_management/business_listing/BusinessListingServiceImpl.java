package com.example.LocalGoodies.api.business_management.business_listing;

import com.example.LocalGoodies.api.business_management.model.Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.LocalGoodies.api.business_management.business_listing.BusinessSpecs.isActive;
import static com.example.LocalGoodies.api.business_management.business_listing.BusinessSpecs.isOfType;

@Service
public class BusinessListingServiceImpl implements BusinessListingService {

    private final BusinessListingRepository businessListingRepository;

    @Autowired
    BusinessListingServiceImpl(
            BusinessListingRepository businessListingRepository) {
        this.businessListingRepository = businessListingRepository;
    }

    @Override
    public List<Business> getAllBusinesses() {
        return businessListingRepository.findAll(isActive());
    }

    @Override
    public List<Business> getByType(String type) {
        return businessListingRepository.findAll(isOfType(type).and(isActive()));
    }
}
