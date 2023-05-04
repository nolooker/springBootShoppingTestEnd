package com.shopping.dto;

import com.shopping.entity.ProductImage;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter
public class ProductImageDto {

    private Long id ;

    private String imageName ; // 이미지 이름(uuid)

    private String oriImageName ;  // 원본 이미지 이름

    private String imageUrl ; // 이미지 조회 경로

    private String repImageYesNo ; // 대표 이미지 여부

    private static ModelMapper modelMapper = new ModelMapper() ;

    public static ProductImageDto of(ProductImage productImage) {
        // 입력되는 Entity 정보를 입력하여 dto 객체에 매핑
        return modelMapper.map(productImage, ProductImageDto.class) ;

    }
}
