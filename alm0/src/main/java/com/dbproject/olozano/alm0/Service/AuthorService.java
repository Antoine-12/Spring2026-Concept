package com.dbproject.olozano.alm0.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.dbproject.olozano.alm0.Model.Entity.Author;
import com.dbproject.olozano.alm0.Model.Repository.AuthorRepository;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository)
    {
        this.authorRepository=authorRepository;
    }
    public List<Author> getAllAuthors()
    {
        return (List<Author>) authorRepository.findAll();
    }
    public Optional<Author> findById(Long par_id) {
        return authorRepository.findById(par_id);
    }
    public Author save(Author par_author) {
        return authorRepository.save(par_author);
    }
    public void delete(Author par_author) {
         authorRepository.delete(par_author);
        }
}