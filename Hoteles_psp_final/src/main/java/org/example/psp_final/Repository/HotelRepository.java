package org.example.psp_final.Repository;

import org.example.psp_final.Models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    // Ejemplo de consultas personalizadas:

    // Buscar por nombre (usando convención de nombres de Spring Data)
    Hotel findByNombre(String nombre);

    // Buscar hoteles disponibles (disponible = 1)
    List<Hotel> findByDisponible(Short disponible);

    // Buscar por categoría y precio menor o igual
    List<Hotel> findByCategoriaAndPrecioLessThanEqual(Long categoria, Double precioMaximo);
}