package com.example.LocalGoodies.api.business_management.business_listing;

import com.example.LocalGoodies.api.business_management.model.Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessListingServiceImpl implements BusinessListingService{

    private final BusinessListingRepository businessListingRepository;

    @Autowired
    BusinessListingServiceImpl(
            BusinessListingRepository businessListingRepository) {
        this.businessListingRepository = businessListingRepository;
    }

    @Override
    public List<Business> getAllBusinesses() {
        return businessListingRepository.findAll();
    }

}
