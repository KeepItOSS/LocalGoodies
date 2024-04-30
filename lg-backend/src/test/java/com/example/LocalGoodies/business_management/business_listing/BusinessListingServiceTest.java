package com.example.LocalGoodies.business_management.business_listing;

import com.example.LocalGoodies.api.business_management.business_listing.BusinessListingRepository;
import com.example.LocalGoodies.api.business_management.business_listing.BusinessListingServiceImpl;
import com.example.LocalGoodies.api.business_management.model.Business;
import com.example.LocalGoodies.api.business_management.model.BusinessTypeEnum;
import com.example.LocalGoodies.api.business_management.model.DTO.BusinessRequestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.example.LocalGoodies.api.business_management.business_listing.BusinessSpecs.isActive;
import static com.example.LocalGoodies.api.business_management.business_listing.BusinessSpecs.isOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BusinessListingServiceTest {

    @InjectMocks
    BusinessListingServiceImpl businessListingService;

    @Mock
    BusinessListingRepository businessListingRepository;

    @Test
    void shouldFetchAllBusinesses() {
        // given
        Business B1 = new Business
                .Builder("Business1", "Test for B1", BusinessTypeEnum.HANDMADE)
                .email("handmade@email.com")
                .phoneNumber("123456789")
                .build();
        Business B2 = new Business
                .Builder("Business2", "Test for B2", BusinessTypeEnum.REPAIR)
                .email("repair@email.com")
                .phoneNumber("987654321")
                .build();
        List<Business> businesses = List.of(B1, B2);
        when(businessListingRepository.findAll(isActive())).thenReturn(businesses);
        // when
        List<Business> result = businessListingService.getAllBusinesses();
        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void shouldFetchHandmadeOnly() {
        // given
        BusinessTypeEnum type = BusinessTypeEnum.HANDMADE;
        Business B1 = new Business
                .Builder("Business1", "Test for B1", BusinessTypeEnum.HANDMADE)
                .email("handmade@email.com")
                .phoneNumber("123456789")
                .build();
        List<Business> businesses = List.of(B1);
        when(businessListingRepository.findAll(isOfType(any()).and(isActive()))).thenReturn(businesses);
        // when
        List<Business> result = businessListingService.getByType(type);
        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(BusinessTypeEnum.HANDMADE, result.getFirst().getType());
    }

    @Test
    void shouldCreateWhenEmptyEmailAndPhoneNumber() {
        // given
        BusinessRequestDTO businessRequestDTO = new BusinessRequestDTO();
        businessRequestDTO.setName("TEST");
        businessRequestDTO.setDescription("DESCRIPTION");
        businessRequestDTO.setType(BusinessTypeEnum.HANDMADE);
        Business expectedBusiness = new Business.Builder("TEST", "DESCRIPTION", BusinessTypeEnum.HANDMADE).build();

        when(businessListingRepository.save(any(Business.class))).thenReturn(expectedBusiness);

        // when
        Business result = businessListingService.addNew(businessRequestDTO);

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedBusiness.getName(), result.getName());
        Assertions.assertEquals("", result.getEmail());
    }

    @Test
    void shouldCreateWhenEmailProvided() {
        // given
        BusinessRequestDTO businessRequestDTO = new BusinessRequestDTO();
        businessRequestDTO.setName("TEST");
        businessRequestDTO.setDescription("DESCRIPTION");
        businessRequestDTO.setType(BusinessTypeEnum.HANDMADE);
        businessRequestDTO.setEmail("test@test.com");
        Business expectedBusiness = new Business.Builder("TEST", "DESCRIPTION", BusinessTypeEnum.HANDMADE).email("test@test.com").build();

        when(businessListingRepository.save(any(Business.class))).thenReturn(expectedBusiness);

        // when
        Business result = businessListingService.addNew(businessRequestDTO);

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedBusiness.getName(), result.getName());
        Assertions.assertEquals("test@test.com", result.getEmail());
    }

    @Test
    void shouldCreateWhenPhoneNumberProvided() {
        // given
        BusinessRequestDTO businessRequestDTO = new BusinessRequestDTO();
        businessRequestDTO.setName("TEST");
        businessRequestDTO.setDescription("DESCRIPTION");
        businessRequestDTO.setType(BusinessTypeEnum.HANDMADE);
        businessRequestDTO.setPhoneNumber("+48123123123");
        Business expectedBusiness = new Business.Builder("TEST", "DESCRIPTION", BusinessTypeEnum.HANDMADE).phoneNumber("+48123123123").build();

        when(businessListingRepository.save(any(Business.class))).thenReturn(expectedBusiness);

        // when
        Business result = businessListingService.addNew(businessRequestDTO);

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedBusiness.getName(), result.getName());
        Assertions.assertEquals("+48123123123", result.getPhoneNumber());
    }
}
