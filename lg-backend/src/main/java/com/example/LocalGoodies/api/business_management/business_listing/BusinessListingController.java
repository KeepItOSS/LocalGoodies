package com.example.LocalGoodies.api.business_management.business_listing;

import com.example.LocalGoodies.api.business_management.model.Business;
import com.example.LocalGoodies.api.business_management.model.BusinessTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/search")
public class BusinessListingController {

    private final BusinessListingService businessListingService;

    @Autowired
    BusinessListingController(
            BusinessListingService businessListingService) {
        this.businessListingService = businessListingService;
    }

    @GetMapping("/all")
    public List<Business> getAll() {
        List<Business> businesses = businessListingService.getAllBusinesses();
        return businesses;
    }

    @GetMapping()
    public List<Business> getByType(@RequestParam(name = "type") BusinessTypeEnum type) {
        List<Business> businesses = businessListingService.getByType(type);
        return businesses;
    }
}
