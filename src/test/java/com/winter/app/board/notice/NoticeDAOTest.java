package com.winter.app.board.notice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional //이것만 주면 무조건 롤백
class NoticeDAOTest {

	@Autowired
	private NoticeDAO noticeDAO;
	
	// @Test
	void testDetail() throws Exception {
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setBoardNum(1L);
		
//		noticeDTO = noticeDAO.detail(noticeDTO);
		
		assertNotNull(noticeDTO);
	}

	// @Test
	void testList() throws Exception{
//		List<NoticeDTO> ar = noticeDAO.list(null);
		
//		assertNotEquals(0, ar.size());
	}
	
	// @Test
	@Rollback(false) //얘만 롤백 안하겠다.
	void testAdd() throws Exception{
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setBoardTitle("안녕 테스트야");
		noticeDTO.setBoardWriter("테스트 글쓴이");
		noticeDTO.setBoardContents("테스트 컨텐츠");
		
		int result = noticeDAO.add(noticeDTO);
		
		assertEquals(1, result);
	}
	
	// @Test
	void testAdd1() throws Exception{
		for(int i=0;i<120;i++) {
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setBoardTitle("title"+i);
		noticeDTO.setBoardWriter("writer"+i);
		noticeDTO.setBoardContents("contents"+i);
		noticeDAO.add(noticeDTO);
		if(i%10 == 0) {
		Thread.sleep(500);
		}
	  }
	}
	
	// @Test
	void testDelete() throws Exception {
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setBoardNum(3L);
		
		int result = noticeDAO.delete(noticeDTO);
		assertEquals(1, result);
	}
	
	// @Test
	void testUpdate() throws Exception {
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setBoardTitle("qqqeeqee");
		noticeDTO.setBoardContents("qqqqeee4444~~~~");
		noticeDTO.setBoardNum(3L);
		
		int result = noticeDAO.update(noticeDTO);
		assertEquals(0, result);
	}
}
