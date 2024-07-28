package com.ljh.listener;

import com.ljh.App;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class InitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 애플리케이션이 시작될 때 실행할 코드
        App.init();
        // 데이터베이스 연결, 리소스 로딩 등의 초기화 작업 수행
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 애플리케이션이 종료될 때 실행할 코드
        System.out.println("애플리케이션이 종료되었습니다.");
        // 리소스 해제, 정리 작업 수행
    }
}