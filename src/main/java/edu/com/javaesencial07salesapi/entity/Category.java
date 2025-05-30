package edu.com.javaesencial07salesapi.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idCategory;

    @Column(name = "category_name",nullable = false, length = 50)
    private String categoryName;

    @Column(name = "category_description",nullable = false,length = 250)
    private String categoryDescription;

    @Column(name = "category_enabled",nullable = false)
    private boolean categoryEnabled;


}
