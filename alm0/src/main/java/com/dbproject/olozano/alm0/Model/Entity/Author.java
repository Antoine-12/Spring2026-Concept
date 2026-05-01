package com.dbproject.olozano.alm0.Model.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "author")
@Entity
@Data
//automaticmaente genera los setters y getters
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_authors")
    @SequenceGenerator(name = "sequence_authors", sequenceName = "sequence_authors", allocationSize = 1)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String biography_info;
}
