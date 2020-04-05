package com.miage.altea.battle_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class Battle {
    public static void main(String... args) {
        SpringApplication.run(Battle.class, args);
    }
}
