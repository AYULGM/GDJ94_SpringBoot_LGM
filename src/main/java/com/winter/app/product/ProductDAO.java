package com.winter.app.product;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.winter.app.util.Pager;

@Mapper
public interface ProductDAO {

	public Long count(Pager pager) throws Exception; // 매개변수 건네줄게 없음
	
	public List<ProductDTO> list(Pager pager) throws Exception;
	
	public ProductDTO detail(ProductDTO productDTO) throws Exception;
	
	public int add(ProductDTO productDTO) throws Exception;
	
	public int delete(ProductDTO productDTO) throws Exception;
	
	public int update(ProductDTO productDTO) throws Exception;
}
