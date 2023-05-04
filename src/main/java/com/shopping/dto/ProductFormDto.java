package com.shopping.dto;

import com.shopping.constant.ProductStatus;
import com.shopping.entity.Product;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductFormDto {
    private Long id ;

    @NotBlank(message = "상품명은(는) 필수 입력 값입니다.")
    private String name ;

    @NotNull(message = "가격은(는) 필수 입력 값입니다.")
    private Integer price ;

    @NotBlank(message = "상품 설명은(는) 필수 입력 값입니다.")
    private String description ;

    @NotNull(message = "재고은(는) 필수 입력 값입니다.")
    private Integer stock ;

    private ProductStatus productStatus ;

    // 상품 1개에 최대 5개까지의 이미지가 들어갈 수 있습니다.
    private List<ProductImageDto> productImageDtoList = new ArrayList<>() ;

    // 이미지들의 id를 저장할 컬렉션(이미지 수정시 필요함)
    private List<Long> productImageIds = new ArrayList<>() ;

    private static ModelMapper modelMapper = new ModelMapper();

    public Product createProduct(){
        return modelMapper.map(this, Product.class) ;
    }

    public static ProductFormDto of(Product product){
        return modelMapper.map(product, ProductFormDto.class) ;
    }
}
