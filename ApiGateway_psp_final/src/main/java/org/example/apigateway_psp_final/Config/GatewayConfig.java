package org.example.apigateway_psp_final.Config; // Ajusta el paquete según tu proyecto

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;

@Configuration
public class GatewayConfig {

    @Bean
    public GlobalFilter customFilter() {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest().mutate()
                    .header("Authorization", exchange.getRequest().getHeaders().getFirst("Authorization"))
                    .build();
            return chain.filter(exchange.mutate().request(request).build());
        };
    }
}