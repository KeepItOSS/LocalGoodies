package com.example.LocalGoodies.api.business_management.business_listing;

import com.example.LocalGoodies.api.business_management.model.Business;
import com.example.LocalGoodies.api.business_management.model.BusinessTypeEnum;
import com.example.LocalGoodies.api.business_management.model.DTO.BusinessRequestDTO;
import com.example.LocalGoodies.api.business_management.model.DTO.BusinessResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.LocalGoodies.api.business_management.model.BusinessMapper.mapEntitiesToResponseDtos;
import static com.example.LocalGoodies.api.business_management.model.BusinessMapper.mapEntityToResponseDto;

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
    public List<BusinessResponseDTO> getAllActive() {
        List<Business> businesses = businessListingService.getAllActiveBusinesses();
        return mapEntitiesToResponseDtos(businesses);
    }

    @GetMapping("/search")
    public List<BusinessResponseDTO> getByType(
            @RequestParam(name = "type") BusinessTypeEnum type) {
        List<Business> businesses = businessListingService.getByType(type);
        return mapEntitiesToResponseDtos(businesses);
    }

    @PostMapping("/add")
    public ResponseEntity<BusinessResponseDTO> addNewBusiness(
            @Valid @RequestBody BusinessRequestDTO businessRequestDTO) {
        Business business = businessListingService.addNew(businessRequestDTO);
        BusinessResponseDTO dto = mapEntityToResponseDto(business);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BusinessResponseDTO> updateBusiness(
            @PathVariable Long id,
            @Valid @RequestBody BusinessRequestDTO businessRequestDTO) {
        Business business = businessListingService.update(id, businessRequestDTO);
        BusinessResponseDTO dto = mapEntityToResponseDto(business);
        return new ResponseEntity<>(dto, HttpStatus.OK);
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
