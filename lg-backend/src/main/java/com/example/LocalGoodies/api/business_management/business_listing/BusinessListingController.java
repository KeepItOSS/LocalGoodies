package com.example.LocalGoodies.api.business_management.business_listing;

import com.example.LocalGoodies.api.business_management.model.Business;
import com.example.LocalGoodies.api.business_management.model.DTO.BusinessRequestDTO;
import com.example.LocalGoodies.api.business_management.model.BusinessTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/business-listing")
public class BusinessListingController {

    private final BusinessListingService businessListingService;

    @Autowired
    BusinessListingController(
            BusinessListingService businessListingService) {
        this.businessListingService = businessListingService;
    }

    @GetMapping("search/all")
    public List<Business> getAll() {
        List<Business> businesses = businessListingService.getAllBusinesses();
        return businesses;
    }

    @GetMapping("/search")
    public List<Business> getByType(@RequestParam(name = "type") BusinessTypeEnum type) {
        List<Business> businesses = businessListingService.getByType(type);
        return businesses;
    }

    @PostMapping("/add")
    public Business addNewBusiness(@RequestBody BusinessRequestDTO businessRequestDTO) {
        Business business = businessListingService.addNew(businessRequestDTO);
        return business;
    }
}
