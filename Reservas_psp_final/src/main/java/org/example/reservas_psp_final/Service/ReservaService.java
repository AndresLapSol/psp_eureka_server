package org.example.reservas_psp_final.Service;

import feign.FeignException;
import org.example.reservas_psp_final.Model.Reserva;
import org.example.reservas_psp_final.Repository.ReservaRepository;
import org.example.reservas_psp_final.client.VuelosClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final WebClient webClient;
    private final CircuitBreakerFactory<?, ?> circuitBreakerFactory;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository,
                          WebClient.Builder webClientBuilder,
                          CircuitBreakerFactory<?, ?> circuitBreakerFactory,
                          VuelosClient vuelosClient) {
        this.reservaRepository = reservaRepository;
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
        this.circuitBreakerFactory = circuitBreakerFactory;
    }



    public List<Reserva> obtenerReservas() {
        return reservaRepository.findAll();
    }

    public List<Reserva> obtenerReservasPorDni(String dni) {
        return reservaRepository.findByDni(dni);
    }

    @Transactional
    public Reserva realizarReserva(Reserva reserva, String authHeader) {
        // 1. Actualizar plazas en el servicio de vuelos
        actualizarPlazasEnVuelo(reserva.getVueloId(), reserva.getPlazasReservadas(), authHeader);

        // 2. Guardar la reserva
        return reservaRepository.save(reserva);
    }

    private void actualizarPlazasEnVuelo(Long vueloId, int plazasReservadas, String authHeader) {
        circuitBreakerFactory.create("vuelosCircuitBreaker").run(
                () -> {
                    // Pasa el authHeader al Feign Client
                    //vuelosClient.actualizarPlazas(vueloId, plazasReservadas, authHeader);
                    return ResponseEntity.ok().build();
                },
                throwable -> {
                    throw new ResponseStatusException(
                            HttpStatus.SERVICE_UNAVAILABLE,
                            "Servicio de vuelos no disponible. Reserva no realizada."
                    );
                }
        );
    }


    @Transactional
    public void eliminarReserva(Long id) {
        reservaRepository.deleteById(id);
    }

    // Método alternativo si necesitas compensación al eliminar
    @Transactional
    public void cancelarReserva(Long reservaId, String authHeader) {
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva no encontrada"));

        // Compensación: devolver plazas al vuelo
        actualizarPlazasEnVuelo(reserva.getVueloId(), -reserva.getPlazasReservadas(), authHeader);

        reservaRepository.delete(reserva);
    }

}
