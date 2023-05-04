package com.shopping.dto;

import com.shopping.constant.ProductStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductSearchDto {
    private String searchDateType;
    private ProductStatus productStatus ;
    private String searchBy;
    private String searchQuery;
}
