package com.example.LocalGoodies.api.business_management.business_listing;

import com.example.LocalGoodies.api.business_management.model.Business;
import com.example.LocalGoodies.api.business_management.model.DTO.BusinessRequestDTO;
import com.example.LocalGoodies.api.business_management.model.BusinessTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

import static com.example.LocalGoodies.api.business_management.business_listing.BusinessSpecs.isActive;
import static com.example.LocalGoodies.api.business_management.business_listing.BusinessSpecs.isOfType;

@Service
public class BusinessListingServiceImpl implements BusinessListingService {

    private final Pattern EMAIL_PATTERN = Pattern.compile("^(.+)@(.+)$");
    //  phone number regex taken from https://github.com/skotniczny/phonePL
    private final Pattern PHONE_NUM_PATTERN = Pattern.compile("(?:(?:(?:\\+|00)?48)|(?:\\(\\+?48\\)))?(?:1[2-8]|2[2-69]|3[2-49]|4[1-8]|5[0-9]|6[0-35-9]|[7-8][1-9]|9[145])\\d{7}");
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
    public List<Business> getByType(BusinessTypeEnum type) {
        return businessListingRepository.findAll(isOfType(type).and(isActive()));
    }

    @Override
    public Business addNew(BusinessRequestDTO businessRequestDTO) {
        Business.Builder businessBuilder = createBusinessBuilderWithRequired(businessRequestDTO);
        addEmailIfValid(businessRequestDTO.getEmail(), businessBuilder);
        addPhoneNumberIfValid(businessRequestDTO.getPhoneNumber(), businessBuilder);
        Business business = businessBuilder.build();
        return businessListingRepository.save(business);
    }

    private Business.Builder createBusinessBuilderWithRequired(BusinessRequestDTO businessRequestDTO) {
        return new Business.Builder(
                businessRequestDTO.getName(),
                businessRequestDTO.getDescription(),
                businessRequestDTO.getType());
    }

    private void addEmailIfValid(String email, Business.Builder businessBuilder) {
        if (email != null && validateEmail(email)) {
            businessBuilder.email(email);
        }
    }

    private void addPhoneNumberIfValid(String phoneNumber, Business.Builder businessBuilder) {
        if (phoneNumber != null && validatePhoneNumber(phoneNumber)) {
            businessBuilder.phoneNumber(phoneNumber);
        }
    }

    private Boolean validateEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    private Boolean validatePhoneNumber(String phoneNumber) {
        return PHONE_NUM_PATTERN.matcher(phoneNumber).matches();
    }
}
