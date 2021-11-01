package com.ssafy.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class WsFramework8Application {

	public static void main(String[] args) {
		SpringApplication.run(WsFramework8Application.class, args);
	}

}
