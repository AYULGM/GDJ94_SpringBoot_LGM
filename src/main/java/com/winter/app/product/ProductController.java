package com.winter.app.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product/*")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	//리스트 보기
	@GetMapping("list")
	public void list(Model model) throws Exception {
		List<ProductDTO> product =  productService.list();
		model.addAttribute("dto", product);
	}
	
	//상세 조회
	@GetMapping("detail")
	public void detail(ProductDTO productDTO , Model model) throws Exception {
		productDTO = productService.detail(productDTO);
		model.addAttribute("dto", productDTO);
	}
	
	//추가 페이지
	@GetMapping("add")
	public void add(Model model) throws Exception {
		model.addAttribute("kind", "Add");
}
	
	
	@PostMapping("add")
	public String add(ProductDTO productDTO , Model model) throws Exception {
		int result = productService.add(productDTO);
		String msg = "상품 등록 성공 ^^";
		if (result < 1) {
			msg = "상품 등록 실패 ㅠㅠ";
			model.addAttribute("msg", msg);
			model.addAttribute("url", "./list");
			return "commons/result";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", "./list");
		return "commons/result";
		
	}
	
	// 삭제
	@PostMapping("delete")
	public String delete(ProductDTO productDTO) throws Exception {
		productService.delete(productDTO);
		return "redirect:./list";
	}
	
	//수정
	@GetMapping("update")
	public String update(ProductDTO productDTO , Model model) throws Exception {
		productDTO = productService.detail(productDTO);
		model.addAttribute("dto", productDTO);
		model.addAttribute("kind", "Update");
		
		return "product/add";
	}
	
	@PostMapping("update")
	public String update2(ProductDTO productDTO , Model model) throws Exception {
		
		int result = productService.update(productDTO);
		String msg = "수정 성공!!";
		if (result == 0) {
			msg = "수정 실패 ㅠㅠ";
			model.addAttribute("msg", msg);
			model.addAttribute("url", "./list");
			return "commons/result";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", "./list");
		return "commons/result";
	}
	
}
