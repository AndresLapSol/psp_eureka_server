package org.example.psp_final.Service;

import org.example.psp_final.Models.Hotel;
import org.example.psp_final.Repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    // Obtener todos los hoteles
    public List<Hotel> obtenerHoteles() {
        return hotelRepository.findAll();
    }

    // Guardar un nuevo hotel
    public Hotel guardarHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    // Actualizar hotel
    public Hotel actualizarHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    // Eliminar hotel por id
    public void eliminarHotel(Integer id) {
        hotelRepository.deleteById(Long.valueOf(id));
    }
}
