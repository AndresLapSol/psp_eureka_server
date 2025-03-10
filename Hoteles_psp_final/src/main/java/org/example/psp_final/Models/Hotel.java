package org.example.psp_final.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "hoteles")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHotel", columnDefinition = "int UNSIGNED not null")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Column(name = "categoria", columnDefinition = "int UNSIGNED not null")
    private Long categoria;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "disponible", columnDefinition = "tinyint UNSIGNED not null")
    private Short disponible;

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

    public Long getCategoria() {
        return categoria;
    }

    public void setCategoria(Long categoria) {
        this.categoria = categoria;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Short getDisponible() {
        return disponible;
    }

    public void setDisponible(Short disponible) {
        this.disponible = disponible;
    }
}