package com.winter.app.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winter.app.util.Pager;

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
	
	// 댓글 리스트
	public List<ProductCommentDTO> commentList(ProductCommentDTO productCommentDTO, Pager pager) throws Exception{
		pager.setPerPage(5L);
		Map<String, Object> map = new HashMap<>();
		map.put("product", productCommentDTO);
		map.put("pager", pager);
		
		pager.pageing(productDAO.commentCount(productCommentDTO));
		
		return productDAO.commentList(map);
	}
	
	// 댓글 등록
	public int commentAdd(ProductCommentDTO productCommentDTO) throws Exception{
		// 일단 나중에 코드를 짬
		return productDAO.commentAdd(productCommentDTO);
	}
	
	
}
