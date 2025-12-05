package com.winter.app.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductDAO {
	// 상품 리스트
	public List<ProductDTO> list() throws Exception;
	// 상품 조회
	public ProductDTO detail(ProductDTO productDTO) throws Exception;
	// 상품 등록
	public int add(ProductDTO productDTO) throws Exception;
	// 상품 삭제
	public int delete(ProductDTO productDTO) throws Exception;
	// 상품 수정
	public int update(ProductDTO productDTO) throws Exception;
	
	// ---------------- 댓글목록
	public List<ProductCommentDTO> commentList(Map<String, Object> map) throws Exception;
	
	public int commentAdd(ProductCommentDTO productCommentDTO) throws Exception;
}
