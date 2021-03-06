package com.n26;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.n26.*"})
@EnableScheduling
public class Application {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

}
