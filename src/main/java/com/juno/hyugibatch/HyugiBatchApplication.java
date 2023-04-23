package com.juno.hyugibatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HyugiBatchApplication {

    public static void main(String[] args) {
        System.exit(
            SpringApplication.exit(
                SpringApplication.run(HyugiBatchApplication.class, args)
            )
        );
    }

}
