package org.example.reservas_psp_final.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "servicio-vuelos")
public interface VuelosClient {
    @PutMapping("/vuelos/{id}/reservar")
    ResponseEntity<String> actualizarPlazas(
            @PathVariable("id") Long vueloId,
            @RequestParam("plazasReservadas") int plazasReservadas,
            @RequestHeader("Authorization") String authHeader
    );
}