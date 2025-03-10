package org.example.vuelos_psp_final.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "vuelos")
public class Vuelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idvuelo", columnDefinition = "int UNSIGNED not null")
    private Long id;

    @Column(name = "company", nullable = false, length = 45)
    private String company;

    @Column(name = "fecha", nullable = false, length = 45)
    private String fecha;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "plazas", columnDefinition = "int UNSIGNED not null")
    private int plazas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getPlazas() {
        return plazas;
    }

    public void setPlazas(int plazas) {
        this.plazas = plazas;
    }

}