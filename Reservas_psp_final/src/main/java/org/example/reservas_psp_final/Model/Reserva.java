package org.example.reservas_psp_final.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idreserva", columnDefinition = "int UNSIGNED not null")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Column(name = "dni", nullable = false, length = 45)
    private String dni;

    @Column(name = "hotel", columnDefinition = "int UNSIGNED not null")
    @JsonProperty("hotel")
    private Long hotelId;

    @Column(name = "vuelo", columnDefinition = "int UNSIGNED not null")
    @JsonProperty("vuelo")
    private Long vueloId;

    // Campo temporal (no se guarda en la BD)
    @Transient
    private Integer plazasReservadas;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Long getVueloId() {
        return vueloId;
    }

    public void setVueloId(Long vueloId) {
        this.vueloId = vueloId;
    }

    public Integer getPlazasReservadas() {
        return plazasReservadas;
    }

    public void setPlazasReservadas(Integer plazasReservadas) {
        this.plazasReservadas = plazasReservadas;
    }
}