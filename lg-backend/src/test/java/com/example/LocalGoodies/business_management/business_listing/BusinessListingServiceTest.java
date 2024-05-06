package com.example.LocalGoodies.business_management.business_listing;

import com.example.LocalGoodies.api.business_management.business_listing.BusinessListingRepository;
import com.example.LocalGoodies.api.business_management.business_listing.BusinessListingServiceImpl;
import com.example.LocalGoodies.api.business_management.model.Business;
import com.example.LocalGoodies.api.business_management.model.BusinessTypeEnum;
import com.example.LocalGoodies.api.business_management.model.DTO.BusinessRequestDTO;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.example.LocalGoodies.api.business_management.business_listing.BusinessSpecs.isActive;
import static com.example.LocalGoodies.api.business_management.business_listing.BusinessSpecs.isOfType;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        List<Business> result = businessListingService.getAllActiveBusinesses();
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
        BusinessRequestDTO businessRequestDTO = new BusinessRequestDTO(
                "TEST",
                "DESCRIPTION",
                BusinessTypeEnum.HANDMADE,
                null,
                null);
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
        BusinessRequestDTO businessRequestDTO = new BusinessRequestDTO(
                "TEST",
                "DESCRIPTION",
                BusinessTypeEnum.HANDMADE,
                null,
                "test@test.com");
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
        BusinessRequestDTO businessRequestDTO = new BusinessRequestDTO(
                "TEST",
                "DESCRIPTION",
                BusinessTypeEnum.HANDMADE,
                "+48123456789",
                null);
        Business expectedBusiness = new Business.Builder("TEST", "DESCRIPTION", BusinessTypeEnum.HANDMADE).phoneNumber("+48123123123").build();

        when(businessListingRepository.save(any(Business.class))).thenReturn(expectedBusiness);

        // when
        Business result = businessListingService.addNew(businessRequestDTO);

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedBusiness.getName(), result.getName());
        Assertions.assertEquals("+48123123123", result.getPhoneNumber());
    }

    @Test
    void shouldUpdateBusinessWhenValidIdAndRequestProvided() {
        // given
        Long id = 1L;
        BusinessRequestDTO businessRequestDTO = new BusinessRequestDTO(
                "Updated Business",
                "Updated Description",
                BusinessTypeEnum.HANDMADE,
                "updated@email.com",
                "987654321");
        Business existingBusiness = new Business.Builder("Existing Business", "Existing Description", BusinessTypeEnum.HANDMADE).build();
        Business expectedBusiness = new Business.Builder("Updated Business", "Updated Description", BusinessTypeEnum.HANDMADE).email("updated@email.com").phoneNumber("987654321").build();

        when(businessListingRepository.findById(id)).thenReturn(Optional.of(existingBusiness));
        when(businessListingRepository.save(any(Business.class))).thenReturn(expectedBusiness);

        // when
        Business result = businessListingService.update(id, businessRequestDTO);

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedBusiness.getName(), result.getName());
        Assertions.assertEquals(expectedBusiness.getDescription(), result.getDescription());
        Assertions.assertEquals(expectedBusiness.getType(), result.getType());
        Assertions.assertEquals(expectedBusiness.getEmail(), result.getEmail());
        Assertions.assertEquals(expectedBusiness.getPhoneNumber(), result.getPhoneNumber());
    }

    @Test
    void shouldThrowExceptionWhenInvalidIdProvided() {
        // given
        Long id = 1L;
        BusinessRequestDTO businessRequestDTO = new BusinessRequestDTO(
                "Updated Business",
                "Updated Description",
                BusinessTypeEnum.HANDMADE,
                "updated@email.com",
                "987654321");

        when(businessListingRepository.findById(id)).thenReturn(Optional.empty());

        // when
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            businessListingService.update(id, businessRequestDTO);
        });

        // then
        String expectedMessage = "Business with id " + id + " not found";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldNotChangeEmailWhenInvalidEmailProvided() {
        // given
        BusinessRequestDTO businessRequestDTO = new BusinessRequestDTO(
                "TEST",
                "DESCRIPTION",
                BusinessTypeEnum.HANDMADE,
                "invalid-email",
                null);
        Business expectedBusiness = new Business.Builder("TEST", "DESCRIPTION", BusinessTypeEnum.HANDMADE).email("mail@mail.com").build();

        when(businessListingRepository.save(any(Business.class))).thenReturn(expectedBusiness);

        // when
        Business result = businessListingService.addNew(businessRequestDTO);

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedBusiness.getName(), result.getName());
        Assertions.assertEquals("mail@mail.com", result.getEmail());
    }
}
