package com.example.LocalGoodies.api.business_management.business_listing;

import com.example.LocalGoodies.api.business_management.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessListingRepository extends
        JpaRepository<Business, Long>,
        JpaSpecificationExecutor<Business> {
}
