package com.winter.app.ajax;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AjaxTest1Test {

	@Autowired
	private AjaxTest1 test1;
	
	@Autowired
	private AjaxTest2 test2;
	
	@Test
	void test() throws Exception {
		test2.t3();
	}

}
