package edu.com.javaesencial07salesapi.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Ingress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idIngress;

    @Column(name = "ingress_datetime", nullable = false)
    private LocalDateTime ingressDateTime;

    @Column(name = "ingress_total",nullable = false, precision = 10, scale = 2)
    private BigDecimal ingressTotal;

    @Column(name = "ingress_tax",nullable = false, precision = 10, scale = 2)
    private BigDecimal ingressTax;

    @Column(name = "ingress_serial_number", nullable = false)
    private String ingressSerialNumber;

    @Column(name = "ingress_enabled", nullable = false)
    private boolean ingressEnabled;

    // FKS

    @ManyToOne
    @JoinColumn(name = "id_provider", nullable = false, foreignKey = @ForeignKey(name = "FK_INGRESS_PROVIDER"))
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "FK_INGRESS_USER"))
    private User user;


}
