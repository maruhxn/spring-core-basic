package com.study.springcore.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작 시 호출
    public void connect() {
        System.out.println("Connect: " + url);
    }

    public void call(String message) {
        System.out.println("Call: " + url + " message = " + message);
    }

    // 서비스 종료 시 호출
    public void disconnect() {
        System.out.println("Close: " + url);
    }

    @PostConstruct
    public void init() {
        // 의존관계 주입이 끝나면 호출
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        // 종료 시 호출
        disconnect();
    }
}
