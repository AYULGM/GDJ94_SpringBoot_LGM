package com.winter.app.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//DB에서는 _으로 잇지만 자바에서는 카멜케이스로 적는다.
@Getter
@Setter
@ToString
public class ProductDTO {

    private Long productNum;        // 상품번호
    private String productName;     // 상품이름
    private String productContents; // 상품내용
    private String productCategory; // 상품종류
    private Double productRate;     // 상품이율
    private Integer productSale;    // 판매완료(1), 판매중(0)
}
