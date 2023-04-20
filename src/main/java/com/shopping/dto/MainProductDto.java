package com.shopping.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MainProductDto {

    private Long id ;
    private String name ;
    private String description ;
    private String imageUrl ;
    private Integer price ;

    @QueryProjection // Projection은 테이블의 특정 컬럼 정보를 조회하는 동작을 말함
    // 해당 조회 결과를 dto에 대입해 줍니다.
    public MainProductDto(Long id, String name, String description, String imageUrl, Integer price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
    }
}
