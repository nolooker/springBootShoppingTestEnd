package com.shopping.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shopping.constant.ProductStatus;
import com.shopping.dto.MainProductDto;
import com.shopping.dto.ProductSearchDto;
import com.shopping.dto.QMainProductDto;
import com.shopping.entity.Product;
import com.shopping.entity.QProduct;
import com.shopping.entity.QProductImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class ProductRepositoryCustomImpl implements ProductRepositoryCustom{
    private JPAQueryFactory queryFactory ;

    @Override
    public Page<MainProductDto> getMainProductPage(ProductSearchDto searchDto, Pageable pageable) {
        QProduct product = QProduct.product ;
        QProductImage productImage = QProductImage.productImage ;

        QueryResults<MainProductDto> results = this.queryFactory
                .select(
                        new QMainProductDto(
                                product.id,
                                product.name,
                                product.description,
                                productImage.imageUrl,
                                product.price
                        )
                )
                .from(productImage)
                .join(productImage.product, product)
                .where(productImage.repImageYesNo.eq("Y"))
                .where(likeCondition(searchDto.getSearchQuery()))
                .orderBy(product.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults() ;

        List<MainProductDto> content = results.getResults();
        long total = results.getTotal() ;

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression likeCondition(String searchQuery) {
        // 검색 키워드가 널이 아니면 like 연산을 수행합니다.
        return StringUtils.isEmpty(searchQuery) ? null : QProduct.product.name.like("%" + searchQuery + "%") ;
    }

    @Override
    public Page<Product> getAdminProductPage(ProductSearchDto searchDto, Pageable pageable) {
        QueryResults<Product> results = this.queryFactory
                .selectFrom(QProduct.product)
                .where(dateRange(searchDto.getSearchDateType()),
                        sellStatusCondition(searchDto.getProductStatus()),
                        searchByCondition(searchDto.getSearchBy(), searchDto.getSearchQuery()))
                .orderBy(QProduct.product.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Product> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }


    public ProductRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
    private BooleanExpression dateRange(String searchDateType) {
        // 특정 기간 내 조회 방식 : 1일, 1주, 1달
        LocalDateTime dateTime = LocalDateTime.now() ;

        if(StringUtils.equals("all", searchDateType) || searchDateType == null){
            return null ;
        }else if(StringUtils.equals("1d", searchDateType)){
            dateTime = dateTime.minusDays(1);

        }else if(StringUtils.equals("1w", searchDateType)){
            dateTime = dateTime.minusWeeks(1);

        }else if(StringUtils.equals("1m", searchDateType)){
            dateTime = dateTime.minusMonths(1) ;

        }else if(StringUtils.equals("6m", searchDateType)){
            dateTime = dateTime.minusMonths(6) ;
        }

        return QProduct.product.regDate.after(dateTime) ;
    }
    private BooleanExpression searchByCondition(String searchBy, String searchQuery) {
        if(StringUtils.equals("name", searchBy)){ // 상품 이름으로 검색
            return QProduct.product.name.like("%" + searchQuery + "%" ) ;

        }else if(StringUtils.equals("createdBy", searchBy)){ // 상품 등록자로 검색
            return QProduct.product.createdBy.like("%" + searchQuery + "%" ) ;
        }

        return null ;
    }
    private BooleanExpression sellStatusCondition(ProductStatus productStatus) {
        return productStatus == null ? null : QProduct.product.productStatus.eq(productStatus) ;
    }

}
