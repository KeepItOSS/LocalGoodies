package com.example.LocalGoodies.business_management.business_listing;

import com.example.LocalGoodies.api.business_management.business_listing.BusinessListingController;
import com.example.LocalGoodies.api.business_management.business_listing.BusinessListingService;
import com.example.LocalGoodies.api.business_management.model.Business;
import com.example.LocalGoodies.api.business_management.model.BusinessTypeEnum;
import com.example.LocalGoodies.api.business_management.model.DTO.BusinessRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BusinessListingController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class BusinessListingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BusinessListingService businessListingService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void whenGetRequestToFetchAllBusiness_thenReturnBusinessList() throws Exception {
        Business expectedBusiness = new Business.Builder("TEST", "DESCRIPTION", BusinessTypeEnum.HANDMADE).build();
        List<Business> businessList = List.of(expectedBusiness);
        Integer pageNumber = 0;
        when(businessListingService.getAllActiveBusinesses(pageNumber)).thenReturn(businessList);

        mockMvc.perform(get("/api/business-listing/search/active")
                        .param("page", pageNumber.toString()))
                .andExpect(status().isOk());
    }

    @Test
    public void whenGetRequestToFetchBusinessByType_thenReturnBusinessList() throws Exception {
        Business expectedBusiness = new Business.Builder("TEST", "DESCRIPTION", BusinessTypeEnum.HANDMADE).build();
        List<Business> businessList = List.of(expectedBusiness);
        Integer pageNumber = 0;
        when(businessListingService.getAllActiveBusinesses(pageNumber)).thenReturn(businessList);

        mockMvc.perform(get("/api/business-listing/search")
                        .param("page", pageNumber.toString())
                        .param("type", "HANDMADE"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenGetRequestToFetchBusinessByTypeWithWrongParam_thenThrowException() throws Exception {
        mockMvc.perform(get("/api/business-listing/search")
                        .param("type", "TESTWRONGPARAM"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenPostRequestToCreateAndValidRequest_thenReturnBusiness() throws Exception {
        BusinessRequestDTO businessRequestDTO = new BusinessRequestDTO(
                "TEST",
                "DESCRIPTION",
                BusinessTypeEnum.HANDMADE,
                null,
                null);
        Business expectedBusiness = new Business.Builder(
                "TEST",
                "DESCRIPTION",
                BusinessTypeEnum.HANDMADE).build();

        when(businessListingService.addNew(any())).thenReturn(expectedBusiness);

        mockMvc.perform(post("/api/business-listing/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(businessRequestDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void whenPostRequestToCreateAndInvalidRequest_thenThrowException() throws Exception {
        BusinessRequestDTO businessRequestDTO = new BusinessRequestDTO(
                null,
                null,
                null,
                null,
                null);
        mockMvc.perform(post("/api/business-listing/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(businessRequestDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenPutRequestToUpdateAndValidRequest_thenReturnBusiness() throws Exception {
        BusinessRequestDTO businessRequestDTO = new BusinessRequestDTO(
                "TEST",
                "DESCRIPTION",
                BusinessTypeEnum.HANDMADE,
                null,
                null);
        Business expectedBusiness = new Business.Builder(
                "TEST",
                "DESCRIPTION",
                BusinessTypeEnum.HANDMADE).build();

        when(businessListingService.update(any(), any())).thenReturn(expectedBusiness);

        mockMvc.perform(put("/api/business-listing/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(businessRequestDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void whenPutRequestToUpdateAndInvalidRequest_thenThrowException() throws Exception {
        BusinessRequestDTO businessRequestDTO = new BusinessRequestDTO(
                null,
                null,
                null,
                null,
                null);
        mockMvc.perform(put("/api/business-listing/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(businessRequestDTO)))
                .andExpect(status().isBadRequest());
    }
}
