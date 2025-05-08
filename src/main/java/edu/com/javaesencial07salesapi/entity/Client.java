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
public class Client {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idClient;

    @Column(nullable = false,length = 100)
    private String  clientFirstName;

    @Column(nullable = false,length = 100)
    private String clientLastName;

    @Column(nullable = false,length = 10, unique = true)
    private String clientCardId;

    @Column(nullable = false,length = 10)
    private String clientPhoneNumber;

    @Column(nullable = false,length = 10)
    private String clientEmail;

    @Column(nullable = false,length = 10)
    private String clientAddress;





}
