package edu.com.javaesencial07salesapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "provider")
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idProvider;

    @Column(nullable = false,length = 50)
    private String providerName;

    @Column(nullable = false,length = 150)
    private String providerAddress;

    @Column(nullable = false)
    private boolean providerEnabled;


}
