package com.example.LocalGoodies.api.business_management.business_listing;

import com.example.LocalGoodies.api.business_management.model.Business;
import com.example.LocalGoodies.api.business_management.model.BusinessMapper;
import com.example.LocalGoodies.api.business_management.model.BusinessTypeEnum;
import com.example.LocalGoodies.api.business_management.model.DTO.BusinessRequest;
import com.example.LocalGoodies.api.business_management.model.DTO.BusinessResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.LocalGoodies.api.business_management.model.BusinessMapper.mapEntitiesToResponse;
import static com.example.LocalGoodies.api.business_management.model.BusinessMapper.mapEntityToResponse;

@RestController
@RequestMapping("api/business-listing")
public class BusinessListingController {

    private final BusinessListingService businessListingService;

    @Autowired
    BusinessListingController(
            BusinessListingService businessListingService) {
        this.businessListingService = businessListingService;
    }

    @GetMapping("/search/active")
    public Page<BusinessResponse> getPagedBusinesses(
            @PageableDefault(size = 5) Pageable pageable
            ) {
        Page<Business> businesses = businessListingService.getPaged(pageable);
        return BusinessMapper.mapEntitiesToResponse(businesses);
    }

    @GetMapping("/search")
    public Page<BusinessResponse> getPagedBusinessesByType(
            @PageableDefault(size = 5) Pageable pageable,
            @RequestParam(name = "type") BusinessTypeEnum type) {
        Page<Business> businesses = businessListingService.getPagedByType(pageable, type);
        return BusinessMapper.mapEntitiesToResponse(businesses);
    }

    @GetMapping("/search/name")
    public List<BusinessResponse> getByName(
            @RequestParam(name = "name") String name) {
        List<Business> businesses = businessListingService.getByNameStartsWith(name);
        return mapEntitiesToResponse(businesses);
    }

    @PostMapping("/add")
    public ResponseEntity<BusinessResponse> addNewBusiness(
            @Valid @RequestBody BusinessRequest businessRequest) {
        Business business = businessListingService.addNew(businessRequest);
        BusinessResponse response = mapEntityToResponse(business);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BusinessResponse> updateBusiness(
            @PathVariable Long id,
            @Valid @RequestBody BusinessRequest businessRequest) {
        Business business = businessListingService.update(id, businessRequest);
        BusinessResponse response = mapEntityToResponse(business);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
