package org.example.psp_final;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HotelesApplication {
    public static void main(String[] args) {
        SpringApplication.run(HotelesApplication.class, args);
    }
}