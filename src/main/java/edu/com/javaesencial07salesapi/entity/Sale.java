package edu.com.javaesencial07salesapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idSale;


    @Column(nullable = false)
    private LocalDateTime saleDateTime;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal saleTotal;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal saleTax;

    @Column(nullable = false)
    private boolean saleEnabled;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false, foreignKey = @ForeignKey(name = "FK_SALE_CLIENT"))
    private Client client;


    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "FK_SALE_USER"))
    private User user;


    // una venta tiene muchos detalles
    // todo lo que pase en cabezera pasa en detalle

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<SaleDetail> details;
}
