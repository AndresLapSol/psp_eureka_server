package org.example.reservas_psp_final.Repository;

import org.example.reservas_psp_final.Model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    // Consulta personalizada para buscar por DNI
    List<Reserva> findByDni(String dni);
}