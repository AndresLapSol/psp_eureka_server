package org.example.vuelos_psp_final.Repository;

import jakarta.transaction.Transactional;
import org.example.vuelos_psp_final.Model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VueloRepository extends JpaRepository<Vuelo, Long> {

    // Consultas básicas
    List<Vuelo> findByPlazasGreaterThanEqual(Long plazas);
    List<Vuelo> findByCompany(String company);

    // Consulta adicional para futuro uso
    List<Vuelo> findByFecha(String fecha);

    @Transactional
    @Modifying
    @Query("UPDATE Vuelo v SET v.plazas = v.plazas + :cantidad WHERE v.id = :id")
    int actualizarPlazas(@Param("id") Long id, @Param("cantidad") int cantidad);

    List<Vuelo> findByPlazasGreaterThanEqual(int plazasMinimas);
}