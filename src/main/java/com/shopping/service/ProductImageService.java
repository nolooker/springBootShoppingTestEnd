package com.shopping.service;

import com.shopping.entity.ProductImage;
import com.shopping.repository.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductImageService {
    @Value("${productImageLocation}")
    private String productImageLocation ; // 상품 이미지가 업로드 되는 경로


    private final ProductImageRepository productImageRepository ;
    private final FileService fileService ;

    // 상품 이미지 정보 저장
    public void saveProductImage(ProductImage productImage, MultipartFile uploadedFile) throws Exception {
        String oriImageName = uploadedFile.getOriginalFilename() ;
        String imageName = "" ;
        String imageUrl = "" ;

        System.out.println("productImageLocation : " + productImageLocation);

        // 파일 업로드
        if (!StringUtils.isEmpty(oriImageName)){  // 이름이 있으면 업로드 해
            imageName = fileService.uploadFile(productImageLocation, oriImageName, uploadedFile.getBytes()) ;
            imageUrl = "/images/product/" + imageName ;
            System.out.println("imageUrl : " + imageUrl);

        }

        productImage.updateProductImage(oriImageName, imageName, imageUrl);
        productImageRepository.save(productImage) ;
    }
}
