package com.example.r2g;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class R2gApplication {

    public static void main(String[] args) {
        SpringApplication.run(R2gApplication.class, args);
    }

}
