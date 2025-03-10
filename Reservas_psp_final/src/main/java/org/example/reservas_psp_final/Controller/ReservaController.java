package org.example.reservas_psp_final.Controller;

import org.example.reservas_psp_final.Model.Reserva;
import org.example.reservas_psp_final.Repository.ReservaRepository;
import org.example.reservas_psp_final.Service.ReservaService;
import org.example.reservas_psp_final.client.VuelosClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    private VuelosClient vuelosClient;

    @Autowired
    private ReservaRepository reservaRepository;

    // Obtener todas las reservas
    @GetMapping
    public ResponseEntity<List<Reserva>> listarTodas(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        return ResponseEntity.ok(reservaService.obtenerReservas());
    }

    // Buscar reservas por DNI
    @GetMapping("/{dni}")
    public ResponseEntity<List<Reserva>> buscarPorDni(@PathVariable String dni) {
        return ResponseEntity.ok(reservaService.obtenerReservasPorDni(dni));
    }

    // Crear nueva reserva
    @PostMapping
    public ResponseEntity<?> crearReserva(
            @RequestBody Reserva reserva,
            @RequestHeader("Authorization") String authHeader) {
        try {
            Reserva nuevaReserva = reservaService.realizarReserva(reserva, authHeader);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReserva);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno");
        }
    }


    // Eliminar reserva
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable Long id) {
        try {
            reservaService.eliminarReserva(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}