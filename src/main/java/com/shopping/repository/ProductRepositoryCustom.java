package com.shopping.repository;

import com.shopping.dto.MainProductDto;
import com.shopping.dto.ProductSearchDto;
import com.shopping.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {
    Page<Product> getAdminProductPage(ProductSearchDto searchDto, Pageable pageable);

    Page<MainProductDto> getMainProductPage(ProductSearchDto searchDto, Pageable pageable);
}
