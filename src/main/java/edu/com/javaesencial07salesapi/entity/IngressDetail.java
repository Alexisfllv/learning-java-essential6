package edu.com.javaesencial07salesapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(IngressDetailPK.class)
public class IngressDetail {

    // llave primaria compuesta
    @Id
    private Ingress ingress;

    @Id
    private Product product;

    @Column (nullable = false)
    private short quantity;

    @Column (nullable = false, precision = 8, scale = 2)
    private BigDecimal cost;
}
