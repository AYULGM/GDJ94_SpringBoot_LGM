package com.winter.app.board.qna;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QnaDAOTest {

	@Autowired
	private QnaDAO qnaDAO;
	
	@Test
	void testadd() throws Exception {
		for(int i=0;i<120;i++) {
			QnaDTO qnaDTO = new QnaDTO();
			qnaDTO.setBoardTitle("title"+i);
			qnaDTO.setBoardWriter("writer"+i);
			qnaDTO.setBoardContents("contents"+i);
			qnaDAO.add(qnaDTO);
			if(i%10 == 0) {
				Thread.sleep(500);
			}
		}
	}

}
