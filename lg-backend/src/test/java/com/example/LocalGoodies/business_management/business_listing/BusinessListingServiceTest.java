package com.example.LocalGoodies.business_management.business_listing;

import com.example.LocalGoodies.api.business_management.business_listing.BusinessListingRepository;
import com.example.LocalGoodies.api.business_management.business_listing.BusinessListingServiceImpl;
import com.example.LocalGoodies.api.business_management.model.Business;
import com.example.LocalGoodies.api.business_management.model.BusinessTypeEnum;
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
        //given
        BusinessTypeEnum type = BusinessTypeEnum.HANDMADE;
        Business B1 = new Business
                .Builder("Business1", "Test for B1", BusinessTypeEnum.HANDMADE)
                .email("handmade@email.com")
                .phoneNumber("123456789")
                .build();
        List<Business> businesses = List.of(B1);
        when(businessListingRepository.findAll(isOfType(any()).and(isActive()))).thenReturn(businesses);
        //when
        List<Business> result = businessListingService.getByType(type);
        //then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(BusinessTypeEnum.HANDMADE, result.getFirst().getType());
    }
}
