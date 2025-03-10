package org.example.psp_final.Controller;

import org.example.psp_final.Models.Hotel;
import org.example.psp_final.Repository.HotelRepository;
import org.example.psp_final.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hoteles")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    // Endpoint para obtener todos los hoteles
    @GetMapping
    public List<Hotel> listarHoteles(@RequestHeader("Authorization") String authHeader) {
        System.out.println("Authorization Header: " + authHeader); // Log para verificar
        return hotelService.obtenerHoteles();
    }

    // Endpoint para agregar un nuevo hotel
    @PostMapping
    public Hotel crearHotel(@RequestBody Hotel hotel) {
        return hotelService.guardarHotel(hotel);
    }

    // Endpoint para actualizar un hotel
    @PutMapping("/{id}")
    public Hotel actualizarHotel(@PathVariable Integer id, @RequestBody Hotel hotel) {
        hotel.setId(Long.valueOf(id));
        return hotelService.actualizarHotel(hotel);
    }

    // Endpoint para eliminar un hotel
    @DeleteMapping("/{id}")
    public void borrarHotel(@PathVariable Integer id) {
        hotelService.eliminarHotel(id);
    }

}
