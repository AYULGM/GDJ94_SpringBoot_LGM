package com.winter.app.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.winter.app.users.UserDAO;

@Component
@EnableScheduling
public class ScheduleTest {
	
	@Autowired
	private UserDAO userDAO;
	
	@Scheduled(cron = "10 * * * * *")// 초(0~59), 분(0~59), 시(0~23), 일(1~31) ,월(1~12), 요일(Sun(0)~Sun(7))
	public void s3() {
		System.out.println("반복!");
	}
	
	// @Scheduled(fixedDelayString = "1000", initialDelay = 1000) // 1000ms , 서버가 실행되고 몇초뒤에 실행할거냐
	public void s1() {
		System.out.println("일정간격으로 반복");
	}
	
	// @Scheduled(fixedRate = 2000, initialDelayString = "1000")
	public void s2() {
		System.out.println("고정간격으로 반복");
	}
}
