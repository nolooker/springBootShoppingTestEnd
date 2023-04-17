package com.shopping.service;

import com.shopping.dto.ProductFormDto;
import com.shopping.entity.Product;
import com.shopping.entity.ProductImage;
import com.shopping.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository ;
    private final ProductImageService productImageService ;

    // 상품 등록
    public Long saveProduct(ProductFormDto dto, List<MultipartFile> uploadedFile) throws Exception {

        Product product= dto.createProduct() ;
        productRepository.save(product) ;

        // 상품에 들어가는 각 이미지들
        for (int i = 0; i < uploadedFile.size(); i++) {
            ProductImage productImage = new ProductImage() ;
            productImage.setProduct(product);

            if (i == 0) {
                productImage.setRepImageYesNo("YES");
            }else {
                productImage.setRepImageYesNo("No");
            }

            productImageService.saveProductImage(productImage, uploadedFile.get(i));
        }

        return product.getId().longValue() ;
    }
}
