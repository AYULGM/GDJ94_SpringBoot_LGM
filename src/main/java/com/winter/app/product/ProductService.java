package com.winter.app.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	
	@Autowired
	private ProductDAO productDAO;
	
	// 리스트 보기
	public List<ProductDTO> list() throws Exception {
		return productDAO.list();
	}
	
	// 상세 조회
	public ProductDTO detail(ProductDTO productDTO) throws Exception {
		return productDAO.detail(productDTO);
	}
	
	// 상품 추가
	public int add(ProductDTO productDTO) throws Exception {
		return productDAO.add(productDTO);
	}
	
	
	// 상품 삭제
	public int delete(ProductDTO productDTO) throws Exception {
		return productDAO.delete(productDTO);
	}
	
	// 상품 수정
	public int update(ProductDTO productDTO) throws Exception {
		
		return productDAO.update(productDTO);
	}
	
	
	
	
}
