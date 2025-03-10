package org.example.vuelos_psp_final.Controller;

import org.example.vuelos_psp_final.Model.Vuelo;
import org.example.vuelos_psp_final.Service.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import java.util.List;

@RestController
@RequestMapping("/vuelos")
public class VueloController {

    @Autowired
    private VueloService vueloService;


    @GetMapping
    public ResponseEntity<List<Vuelo>> listarVuelos(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        System.out.println("Authorization Header recibido: " + authHeader);
        return new ResponseEntity<>(vueloService.obtenerTodosVuelos(), HttpStatus.OK);
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Vuelo>> listarVuelosDisponibles(@RequestParam Long plazasMinimas) {
        return new ResponseEntity<>(vueloService.obtenerVuelosDisponibles(Math.toIntExact(plazasMinimas)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Vuelo> crearVuelo(@RequestBody Vuelo vuelo) {
        return new ResponseEntity<>(vueloService.guardarVuelo(vuelo), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/plazas")
    public ResponseEntity<String> actualizarPlazas(@PathVariable Long id, @RequestParam Long cantidad) {
        try {
            vueloService.actualizarPlazas(id, Math.toIntExact(cantidad));
            return ResponseEntity.ok("Plazas actualizadas correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Vuelo no encontrado.");
        }
    }

    @GetMapping("/compania/{compania}")
    public ResponseEntity<List<Vuelo>> buscarPorCompania(@PathVariable String compania) {
        return new ResponseEntity<>(vueloService.buscarPorCompania(compania), HttpStatus.OK);
    }

    @GetMapping("/{id}/plazas")
    public ResponseEntity<Integer> obtenerPlazas(@PathVariable Long id) {
        Integer plazas = vueloService.obtenerPlazasDisponibles(id);
        return ResponseEntity.ok(plazas);
    }

    @PutMapping("/{id}/reservar")
    public ResponseEntity<String> reservarPlazas(
            @PathVariable Long id,
            @RequestParam(name = "plazasReservadas") int plazasReservadas
    ) {
        try {
            vueloService.actualizarPlazas(id, -plazasReservadas);
            return ResponseEntity.ok("Plazas reservadas correctamente");
        } catch (RestClientException e) {
            System.err.println("Error de conexión con otro microservicio: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Servicio de vuelos no disponible. Reserva no realizada.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Vuelo no encontrado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado. Intente nuevamente.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarVuelo(@PathVariable Long id) {
        try {
            vueloService.eliminarVuelo(id);
            return ResponseEntity.ok("Vuelo eliminado correctamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Vuelo no encontrado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado.");
        }
    }

}
