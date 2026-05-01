package com.dbproject.olozano.alm0.Model.Repository;

import org.springframework.data.repository.CrudRepository;

import com.dbproject.olozano.alm0.Model.Entity.Author;

public interface AuthorRepository extends CrudRepository <Author, Long>{
    
}
