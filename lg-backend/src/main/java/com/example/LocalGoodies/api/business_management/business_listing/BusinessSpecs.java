package com.example.LocalGoodies.api.business_management.business_listing;

import com.example.LocalGoodies.api.business_management.model.Business;
import com.example.LocalGoodies.api.business_management.model.BusinessTypeEnum;
import org.springframework.data.jpa.domain.Specification;


public class BusinessSpecs {
    public static Specification<Business> isActive() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("active"), true);
        };
    }
    public static Specification<Business> isOfType(String type) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("type"), type.toUpperCase());
        };
    }
}
