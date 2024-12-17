package com.siesquen.infraccionservice.entity;

import java.util.Date;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "infracciones")
public class Infraccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; 

    @Column(nullable = false, length = 8)
    private String dni;

    @Column(nullable = false, name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha; 

    @Column(name = "tipo_infraccion", nullable = false, length = 20)
    private String tipoInfraccion; 

    @Column(nullable = false, length = 200)
    private String ubicacion; 

    @Column(length = 255)
    private String descripcion; 

    @Column(name = "monto_multa", nullable = false, precision = 8, scale = 2)
    private BigDecimal montoMulta;

    @Column(nullable = false, length = 20)
    private String estado;
}
