package org.example.reservas_psp_final;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "org.example.reservas_psp_final.client")
public class ReservasApplication {
	public static void main(String[] args) {
		SpringApplication.run(ReservasApplication.class, args);
	}
}