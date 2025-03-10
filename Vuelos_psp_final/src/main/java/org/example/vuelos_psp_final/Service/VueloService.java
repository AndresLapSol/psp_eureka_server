package org.example.vuelos_psp_final.Service;

import org.example.vuelos_psp_final.Model.Vuelo;
import org.example.vuelos_psp_final.Repository.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VueloService {

    @Autowired
    private VueloRepository vueloRepository;

    // Obtener todos los vuelos
    public List<Vuelo> obtenerTodosVuelos() {
        return vueloRepository.findAll();
    }

    // Obtener vuelos con plazas disponibles
    public List<Vuelo> obtenerVuelosDisponibles(int plazasMinimas) {
        return vueloRepository.findByPlazasGreaterThanEqual(plazasMinimas);
    }

    // Guardar un vuelo (crear o actualizar)
    public Vuelo guardarVuelo(Vuelo vuelo) {
        return vueloRepository.save(vuelo);
    }

    @Transactional
    public void actualizarPlazas(Long idVuelo, int cantidad) {
        Vuelo vuelo = vueloRepository.findById(idVuelo)
                .orElseThrow(() -> new RuntimeException("Vuelo no encontrado"));

        int nuevasPlazas = vuelo.getPlazas() + cantidad;

        if (nuevasPlazas < 0) {
            throw new RuntimeException("No hay suficientes plazas disponibles");
        }

        vuelo.setPlazas(nuevasPlazas);
        vueloRepository.save(vuelo);
    }

    // Buscar vuelos por compañía
    public List<Vuelo> buscarPorCompania(String compania) {
        return vueloRepository.findByCompany(compania);
    }

    // Obtener el número de plazas disponibles para un vuelo específico
    public int obtenerPlazasDisponibles(Long idVuelo) {
        return vueloRepository.findById(idVuelo)
                .map(Vuelo::getPlazas)
                .orElseThrow(() -> new RuntimeException("Vuelo no encontrado"));
    }

    public void eliminarVuelo(Long id) {
        if (!vueloRepository.existsById(id)) {
            throw new RuntimeException("Vuelo no encontrado.");
        }
        vueloRepository.deleteById(id);
    }
}
