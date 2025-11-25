package com.winter.app.board;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class BoardDAOTest { // 접근지정자가 없다면 default 접근지정자 인것(생략가능).
// Test 어노테이션만 주석처리를 하면 테스트 대상에서 제외.
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Test
	void testUpdateMap()throws Exception{
		Map<String, Object> map = new HashMap<>();
		map.put("t", "update T!");
		map.put("c", "update C~~");
		map.put("n", 73L);
		int result = boardDAO.update(map);
		assertEquals(1, result);
	}
	
	// @Test
	void testUpdate() throws Exception {
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setNum(72L);
		boardDTO.setTitle("72번테스트");
		boardDTO.setContents("글내용테스트!");
		
		int result = boardDAO.update(boardDTO);
		
		assertEquals(1, result);
	}
	
	//@Test
	void testDelete() throws Exception {
		//BoardDTO boardDTO = new BoardDTO();
		//boardDTO.setNum(88L);
		
		int result = boardDAO.delete(89L);
		
		assertEquals(1, result);
	}
	
	// @Test
	void testList() throws Exception {
		List<BoardDTO> ar = boardDAO.list();
		// log.info("{}" , ar.getClass()); // 무슨타입인지 알 수 있다.
		assertNotEquals(0, ar.size()); // size가 0이 아니면 성공!
		
	}
	
	// @Test
	void testAdd() throws Exception{
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setTitle("제목목");
		boardDTO.setWriter("작가가");
		boardDTO.setContents("내용용");
		
		int result = boardDAO.add(boardDTO);
		
		// (희망값, 실제값)
		assertEquals(1, result);
	}
	
	// @Test
	void testDetail() throws Exception {
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setNum(88L); // Long타입인 Num이 88인게 존재하니까.
		
		boardDTO = boardDAO.detail(boardDTO);
		// log.info(boardDTO.toString());
		
		// “이 객체가 null 이 아니어야 테스트가 성공한다” 개발자스러운 코드
		// → 즉, null이면 테스트 실패 (JUnit탭에 Failures가 1추가됨),
		// → null이 아니면 테스트 성공(JUnit탭에 Failures가 0인데 Errors가 추가되면 Exception이 발생했다는뜻)
		assertNotNull(boardDTO); //NotNull을 희망한다.
		// assertNull(boardDTO); //Null을 희망한다.
		
//		int age=10;
//		double ki = 18.23;
		// 기본이 info라서 info보다 큰 loglevel은 출력 X
//		log.debug("age : {} , ki : {}", age, ki); //자바 스크립트에서 비슷한게 있다고함
	}

}
