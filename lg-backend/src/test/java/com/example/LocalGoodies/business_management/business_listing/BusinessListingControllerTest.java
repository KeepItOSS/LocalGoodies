package com.example.LocalGoodies.business_management.business_listing;

import com.example.LocalGoodies.api.business_management.business_listing.BusinessListingController;
import com.example.LocalGoodies.api.business_management.business_listing.BusinessListingService;
import com.example.LocalGoodies.api.business_management.model.Business;
import com.example.LocalGoodies.api.business_management.model.BusinessTypeEnum;
import com.example.LocalGoodies.api.business_management.model.DTO.BusinessRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    public void whenGetRequestToFetchAllBusiness_noParams_thenReturnBusinessList() throws Exception {
        // given
        Business expectedBusiness = new Business
                .Builder("TEST", "DESCRIPTION", BusinessTypeEnum.HANDMADE)
                .build();
        List<Business> pageContent = List.of(expectedBusiness);
        Pageable pageInfo = Pageable.ofSize(1).withPage(0);
        long totalElements = 1;
        Page<Business> page = new PageImpl<>(pageContent, pageInfo, totalElements);

        when(businessListingService.getPaged(any()))
                .thenReturn(page);

        // when / then
        mockMvc.perform(get("/api/business-listing/search/active"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.content[0].name").value(expectedBusiness.getName()))
                .andExpect(jsonPath("$.content[0].description").value(expectedBusiness.getDescription()))
                .andExpect(jsonPath("$.content[0].type").value(expectedBusiness.getType().toString()))
                .andExpect(jsonPath("$.content[0].phoneNumber").value(expectedBusiness.getPhoneNumber()))

                .andExpect(jsonPath("$.pageable.pageSize").value(pageInfo.getPageSize()))
                .andExpect(jsonPath("$.pageable.pageNumber").value(pageInfo.getPageNumber()))
                .andExpect(jsonPath("$.totalElements").value(totalElements));
    }

    @Test
    public void whenGetRequestToFetchAllBusiness_withPage_thenReturnBusinessList() throws Exception {
        // given
        Business expectedBusiness = new Business
                .Builder("TEST", "DESCRIPTION", BusinessTypeEnum.HANDMADE)
                .build();
        List<Business> pageContent = List.of(expectedBusiness);
        Pageable pageInfo = Pageable.ofSize(1).withPage(0);
        long totalElements = 1;
        Page<Business> page = new PageImpl<>(pageContent, pageInfo, totalElements);

        when(businessListingService.getPaged(any()))
                .thenReturn(page);

        // when / then
        mockMvc.perform(get("/api/business-listing/search/active")
                        .param("page", String.valueOf(pageInfo.getPageNumber())))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.content[0].name").value(expectedBusiness.getName()))
                .andExpect(jsonPath("$.content[0].description").value(expectedBusiness.getDescription()))
                .andExpect(jsonPath("$.content[0].type").value(expectedBusiness.getType().toString()))
                .andExpect(jsonPath("$.content[0].phoneNumber").value(expectedBusiness.getPhoneNumber()))

                .andExpect(jsonPath("$.pageable.pageSize").value(pageInfo.getPageSize()))
                .andExpect(jsonPath("$.pageable.pageNumber").value(pageInfo.getPageNumber()))
                .andExpect(jsonPath("$.totalElements").value(totalElements));
    }

    @Test
    public void whenGetRequestToFetchAllBusiness_withPageAndSizeParam_thenReturnBusinessList() throws Exception {
        // given
        Business expectedBusiness = new Business
                .Builder("TEST", "DESCRIPTION", BusinessTypeEnum.HANDMADE)
                .build();
        List<Business> pageContent = List.of(expectedBusiness);
        Pageable pageInfo = Pageable.ofSize(1).withPage(0);
        long totalElements = 1;
        Page<Business> page = new PageImpl<>(pageContent, pageInfo, totalElements);

        when(businessListingService.getPaged(any()))
                .thenReturn(page);

        // when / then
        mockMvc.perform(get("/api/business-listing/search/active")
                        .param("page", String.valueOf(pageInfo.getPageNumber()))
                        .param("size", String.valueOf(pageInfo.getPageSize())))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.content[0].name").value(expectedBusiness.getName()))
                .andExpect(jsonPath("$.content[0].description").value(expectedBusiness.getDescription()))
                .andExpect(jsonPath("$.content[0].type").value(expectedBusiness.getType().toString()))
                .andExpect(jsonPath("$.content[0].phoneNumber").value(expectedBusiness.getPhoneNumber()))

                .andExpect(jsonPath("$.pageable.pageSize").value(pageInfo.getPageSize()))
                .andExpect(jsonPath("$.pageable.pageNumber").value(pageInfo.getPageNumber()))
                .andExpect(jsonPath("$.totalElements").value(totalElements));
    }

    @Test
    public void whenGetRequestToFetchAllBusiness_withRandomStringSize_thenReturnBusinessList() throws Exception {
        // given
        Business expectedBusiness = new Business
                .Builder("TEST", "DESCRIPTION", BusinessTypeEnum.HANDMADE)
                .build();
        List<Business> pageContent = List.of(expectedBusiness);
        Pageable pageInfo = Pageable.ofSize(1).withPage(0);
        long totalElements = 1;
        Page<Business> page = new PageImpl<>(pageContent, pageInfo, totalElements);

        when(businessListingService.getPaged(any()))
                .thenReturn(page);

        // when / then
        mockMvc.perform(get("/api/business-listing/search/active")
                        .param("size", "I am random invalid size value"))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.content[0].name").value(expectedBusiness.getName()))
                .andExpect(jsonPath("$.content[0].description").value(expectedBusiness.getDescription()))
                .andExpect(jsonPath("$.content[0].type").value(expectedBusiness.getType().toString()))
                .andExpect(jsonPath("$.content[0].phoneNumber").value(expectedBusiness.getPhoneNumber()))

                .andExpect(jsonPath("$.pageable.pageSize").value(pageInfo.getPageSize()))
                .andExpect(jsonPath("$.pageable.pageNumber").value(pageInfo.getPageNumber()))
                .andExpect(jsonPath("$.totalElements").value(totalElements));
    }

    @Test
    public void whenGetRequestToFetchAllBusiness_withRandomStringPage_thenReturnBusinessList() throws Exception {
        // given
        Business expectedBusiness = new Business
                .Builder("TEST", "DESCRIPTION", BusinessTypeEnum.HANDMADE)
                .build();
        List<Business> pageContent = List.of(expectedBusiness);
        Pageable pageInfo = Pageable.ofSize(1).withPage(0);
        long totalElements = 1;
        Page<Business> page = new PageImpl<>(pageContent, pageInfo, totalElements);

        when(businessListingService.getPaged(any()))
                .thenReturn(page);

        // when / then
        mockMvc.perform(get("/api/business-listing/search/active")
                        .param("page", "I am invalid page value"))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.content[0].name").value(expectedBusiness.getName()))
                .andExpect(jsonPath("$.content[0].description").value(expectedBusiness.getDescription()))
                .andExpect(jsonPath("$.content[0].type").value(expectedBusiness.getType().toString()))
                .andExpect(jsonPath("$.content[0].phoneNumber").value(expectedBusiness.getPhoneNumber()))

                .andExpect(jsonPath("$.pageable.pageSize").value(pageInfo.getPageSize()))
                .andExpect(jsonPath("$.pageable.pageNumber").value(pageInfo.getPageNumber()))
                .andExpect(jsonPath("$.totalElements").value(totalElements));
    }

    @Test
    public void whenGetRequestToFetchBusinessByType_withType_thenReturnBusinessList() throws Exception {
        // given
        Business expectedBusiness = new Business
                .Builder("TEST", "DESCRIPTION", BusinessTypeEnum.HANDMADE)
                .build();

        List<Business> pageContent = List.of(expectedBusiness);
        Pageable pageInfo = Pageable.ofSize(1).withPage(0);
        long totalElements = 1;
        Page<Business> page = new PageImpl<>(pageContent, pageInfo, totalElements);

        when(businessListingService.getPagedByType(any(), any())).thenReturn(page);

        // when / then
        mockMvc.perform(get("/api/business-listing/search")
                        .param("type", expectedBusiness.getType().toString()))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.content[0].name").value(expectedBusiness.getName()))
                .andExpect(jsonPath("$.content[0].description").value(expectedBusiness.getDescription()))
                .andExpect(jsonPath("$.content[0].type").value(expectedBusiness.getType().toString()))
                .andExpect(jsonPath("$.content[0].phoneNumber").value(expectedBusiness.getPhoneNumber()))

                .andExpect(jsonPath("$.pageable.pageSize").value(pageInfo.getPageSize()))
                .andExpect(jsonPath("$.pageable.pageNumber").value(pageInfo.getPageNumber()))
                .andExpect(jsonPath("$.totalElements").value(totalElements));
    }

    @Test
    public void whenGetRequestToFetchBusinessByType_withTypeAndPage_thenReturnBusinessList() throws Exception {
        // given
        Business expectedBusiness = new Business
                .Builder("TEST", "DESCRIPTION", BusinessTypeEnum.HANDMADE)
                .build();

        List<Business> pageContent = List.of(expectedBusiness);
        Pageable pageInfo = Pageable.ofSize(1).withPage(0);
        long totalElements = 1;
        Page<Business> page = new PageImpl<>(pageContent, pageInfo, totalElements);

        when(businessListingService.getPagedByType(any(), any())).thenReturn(page);

        // when / then
        mockMvc.perform(get("/api/business-listing/search")
                        .param("type", expectedBusiness.getType().toString())
                        .param("page", String.valueOf(pageInfo.getPageNumber())))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.content[0].name").value(expectedBusiness.getName()))
                .andExpect(jsonPath("$.content[0].description").value(expectedBusiness.getDescription()))
                .andExpect(jsonPath("$.content[0].type").value(expectedBusiness.getType().toString()))
                .andExpect(jsonPath("$.content[0].phoneNumber").value(expectedBusiness.getPhoneNumber()))

                .andExpect(jsonPath("$.pageable.pageSize").value(pageInfo.getPageSize()))
                .andExpect(jsonPath("$.pageable.pageNumber").value(pageInfo.getPageNumber()))
                .andExpect(jsonPath("$.totalElements").value(totalElements));
    }

    @Test
    public void whenGetRequestToFetchBusinessByType_withTypeAndPageAndSize_thenReturnBusinessList() throws Exception {
        // given
        Business expectedBusiness = new Business
                .Builder("TEST", "DESCRIPTION", BusinessTypeEnum.HANDMADE)
                .build();

        List<Business> pageContent = List.of(expectedBusiness);
        Pageable pageInfo = Pageable.ofSize(1).withPage(0);
        long totalElements = 1;
        Page<Business> page = new PageImpl<>(pageContent, pageInfo, totalElements);

        when(businessListingService.getPagedByType(any(), any())).thenReturn(page);

        // when / then
        mockMvc.perform(get("/api/business-listing/search")
                        .param("type", expectedBusiness.getType().toString())
                        .param("page", String.valueOf(pageInfo.getPageNumber()))
                        .param("size", String.valueOf(pageInfo.getPageSize())))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.content[0].name").value(expectedBusiness.getName()))
                .andExpect(jsonPath("$.content[0].description").value(expectedBusiness.getDescription()))
                .andExpect(jsonPath("$.content[0].type").value(expectedBusiness.getType().toString()))
                .andExpect(jsonPath("$.content[0].phoneNumber").value(expectedBusiness.getPhoneNumber()))

                .andExpect(jsonPath("$.pageable.pageSize").value(pageInfo.getPageSize()))
                .andExpect(jsonPath("$.pageable.pageNumber").value(pageInfo.getPageNumber()))
                .andExpect(jsonPath("$.totalElements").value(totalElements));
    }

    @Test
    public void whenGetRequestToFetchBusinessByTypeWithWrongParam_thenThrowException() throws Exception {
        mockMvc.perform(get("/api/business-listing/search")
                        .param("type", "TESTWRONGPARAM"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenPostRequestToCreateAndValidRequest_thenReturnBusiness() throws Exception {
        BusinessRequest businessRequest = new BusinessRequest(
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
                        .content(objectMapper.writeValueAsString(businessRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    public void whenPostRequestToCreateAndInvalidRequest_thenThrowException() throws Exception {
        BusinessRequest businessRequest = new BusinessRequest(
                null,
                null,
                null,
                null,
                null);
        mockMvc.perform(post("/api/business-listing/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(businessRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenPutRequestToUpdateAndValidRequest_thenReturnBusiness() throws Exception {
        BusinessRequest businessRequest = new BusinessRequest(
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
                        .content(objectMapper.writeValueAsString(businessRequest)))
                .andExpect(status().isOk());
    }

    @Test
    public void whenPutRequestToUpdateAndInvalidRequest_thenThrowException() throws Exception {
        BusinessRequest businessRequest = new BusinessRequest(
                null,
                null,
                null,
                null,
                null);
        mockMvc.perform(put("/api/business-listing/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(businessRequest)))
                .andExpect(status().isBadRequest());
    }
}
