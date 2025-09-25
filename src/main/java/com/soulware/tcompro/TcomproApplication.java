package com.soulware.tcompro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TcomproApplication {

    public static void main(String[] args) {
        SpringApplication.run(TcomproApplication.class, args);
    }

}
