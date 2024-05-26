package com.example.LocalGoodies.api.business_management.business_listing;

import com.example.LocalGoodies.api.business_management.model.Business;
import com.example.LocalGoodies.api.business_management.model.BusinessTypeEnum;
import com.example.LocalGoodies.api.business_management.model.DTO.BusinessRequestDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

import static com.example.LocalGoodies.api.business_management.business_listing.BusinessSpecs.isActive;
import static com.example.LocalGoodies.api.business_management.business_listing.BusinessSpecs.isOfType;

@Service
public class BusinessListingServiceImpl implements BusinessListingService {

    private final static Pattern EMAIL_PATTERN = Pattern.compile("^(.+)@(.+)$");
    //  phone number regex taken from https://github.com/skotniczny/phonePL
    private final static Pattern PHONE_NUM_PATTERN = Pattern.compile("(?:(?:(?:\\+|00)?48)|(?:\\(\\+?48\\)))?(?:1[2-8]|2[2-69]|3[2-49]|4[1-8]|5[0-9]|6[0-35-9]|[7-8][1-9]|9[145])\\d{7}");
    private final static Integer UNIFORM_PAGE_SIZE = 5;

    private final BusinessListingRepository businessListingRepository;

    @Autowired
    BusinessListingServiceImpl(
            BusinessListingRepository businessListingRepository) {
        this.businessListingRepository = businessListingRepository;
    }

    @Override
    public Page<Business> getPaged(Pageable pageable){
        return businessListingRepository.findAll(isActive(), pageable);
    }

    @Override
    public Page<Business> getPagedByType(Pageable pageable, BusinessTypeEnum type){
        return businessListingRepository.findAll(isOfType(type).and(isActive()), pageable);
    }

    @Override
    public List<Business> getByNameStartsWith(String name) {
        return businessListingRepository.findAll(BusinessSpecs.nameStartsWith(name).and(isActive()));
    }

    @Override
    public Business addNew(BusinessRequestDTO businessRequestDTO) {
        Business business = createBusinessInstance(businessRequestDTO);
        return businessListingRepository.save(business);
    }

    @Override
    public Business update(Long id, BusinessRequestDTO businessRequestDTO) {
        Business existingBusiness = getExistingBusiness(id);
        Business updatedBusiness = updateBusiness(businessRequestDTO, existingBusiness);
        return businessListingRepository.save(updatedBusiness);
    }

    private Business updateBusiness(BusinessRequestDTO businessRequestDTO, Business existingBusiness) {
        existingBusiness.update(businessRequestDTO);
        addEmailIfValid(businessRequestDTO.email(), existingBusiness);
        addPhoneNumberIfValid(businessRequestDTO.phoneNumber(), existingBusiness);
        return existingBusiness;
    }

    private Business getExistingBusiness(Long id) {
        return businessListingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Business with id " + id + " not found"));
    }

    private Business createBusinessInstance(BusinessRequestDTO businessRequestDTO) {
        Business business = new Business.Builder(
                businessRequestDTO.name(),
                businessRequestDTO.description(),
                businessRequestDTO.type())
                .build();
        addEmailIfValid(businessRequestDTO.email(), business);
        addPhoneNumberIfValid(businessRequestDTO.phoneNumber(), business);
        return business;
    }

    private void addEmailIfValid(String email, Business business) {
        if (email != null && validateEmail(email)) {
            business.setEmail(email);
        }
    }

    private void addPhoneNumberIfValid(String phoneNumber, Business business) {
        if (phoneNumber != null && validatePhoneNumber(phoneNumber)) {
            business.setPhoneNumber(phoneNumber);
        }
    }

    private Boolean validateEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    private Boolean validatePhoneNumber(String phoneNumber) {
        return PHONE_NUM_PATTERN.matcher(phoneNumber).matches();
    }
}
