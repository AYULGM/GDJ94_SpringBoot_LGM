package com.winter.app.ajax;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AjaxTest1 {

	public void t3() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON); // 이런방법도 있고. 노션에 있음
		headers.add("Content-type", "application/json; charset=UTF-8"); // 이 방법도 있고. JSONplaceholder에 guide누르면 있음
		
//		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
//		// 넣는건 순서가 없다.
//		params.add("title", "title");
//		params.add("body", "body");
//		params.add("userId", 5);
		
		PostDTO postDTO = new PostDTO();
		postDTO.setTitle("title");
		postDTO.setBody("body");
		postDTO.setUserId(6);
		
		HttpEntity<PostDTO> req = new HttpEntity<PostDTO>(postDTO, headers);
		
//		HttpEntity<MultiValueMap<String, Object>> req = new HttpEntity<MultiValueMap<String,Object>>(params, headers);
		
		postDTO = restTemplate.postForObject("https://jsonplaceholder.typicode.com/posts", req, PostDTO.class);
		log.info("{}", postDTO);
	}
	
	public void t2() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		
		List<PostDTO> res = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", List.class);
		
		log.info("{}", res.get(5)); //여러개의 데이터 중에 데이터 하나를 뽑아보자.
	}
	
	// 1. RestTemplate
	public void t1() throws Exception { // Entity를 쓸건지 , Object를 쓸건지는 본인 선택 
		// 1. RestTemplate 객체 생성
		RestTemplate restTemplate = new RestTemplate();
		// 2. Header설정 (없으면 건너 뜀)
		
		// 3. parameter설정(없으면 건너 뜀)
//		ResponseEntity<String> res = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts/1", String.class);
//		String result = res.getBody();
		// ResponseEntity는 응답 상태코드가 주어지기 때문에 어떤 문제인지(400번대 클라, 500번대 서버)를 알 수 있다.
//		log.info(result);
		
		
//		String res = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/2", String.class);
		// 반면에 얘는 분기처리 할 때 어떤 문제가 있는지 모름
//		log.info(res);
		
		
//		PostDTO postDTO = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/2", PostDTO.class);
//		log.info("{}",postDTO);
		
		
//		ResponseEntity<PostDTO> res = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts/1", PostDTO.class);
//		PostDTO postDTO = res.getBody();
//		log.info("{}",postDTO);
		
	}
	
}
