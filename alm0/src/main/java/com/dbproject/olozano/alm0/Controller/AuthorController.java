package com.dbproject.olozano.alm0.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbproject.olozano.alm0.Model.Entity.Author;
import com.dbproject.olozano.alm0.Service.AuthorService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class AuthorController {
    @Autowired
    private AuthorService authorService;

   // Aquí se incluyen los métodos para el CRUD
   
    @GetMapping("api/list_authors") // READ (lectura de la tabla)
    public List<Author> getAllAuthors()
    {
        return authorService.getAllAuthors();
    }
    
    @GetMapping("api/list_authors_id") // READ (lectura de registro por id con RequestParam)
    public Author getAuthorByIdRequestParam(@RequestParam("par_id") Long authorId)
    {
    // Lógica para obtener el autor por su ID utilizando el servicio AuthorService
    Optional<Author> authorOptional = authorService.findById(authorId);
    return authorOptional.orElse(null);
    }
    @GetMapping("api/list_authors_id/{par_id}") // READ (lectura de registro por id con PathVariable)
    public Author getAuthorByIdPathVariable(@PathVariable("par_id") Long authorId)
    {
        // Lógica para obtener el autor por su ID utilizando el servicio AuthorService
        Optional<Author> authorOptional = authorService.findById(authorId);
        return authorOptional.orElse(null);
    }
     @PostMapping("api/create_authors") //Insertar un nuevo registro
    public Author createAuthor(@RequestBody Author par_Author) {
        // Lógica para crear un nuevo autor utilizando el servicio AuthorService

        // Verificar si ya existe un autor con el mismo id
       
        if (par_Author.getId()!=null && authorService.findById(par_Author.getId()).isPresent())
        {
            return null;
        }
        else
        {  
            return authorService.save(par_Author);                
        }
    }
    // PutMapping // UPDATE (actualiza un registro existente)
    @PutMapping("api/update_authors/{par_id}")
    public Author updateAuthor(@PathVariable("par_id") Long authorId, @RequestBody Author updatedAuthor) {
        // Lógica para buscar y actualizar un autor existente utilizando el servicio AuthorService
        Optional<Author> authorOptional = authorService.findById(authorId);
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            author.setName(updatedAuthor.getName());
            author.setBiography_info(updatedAuthor.getBiography_info());
            return authorService.save(author);
        } else {
            return null;
        }
    }
       @PatchMapping("api/update_partial_authors/{par_id}")
public Author partialUpdateAuthor(@PathVariable Long par_id, @RequestBody Map<String, Object> updates) {
    Optional<Author> authorOptional = authorService.findById(par_id);
   
    if (authorOptional.isPresent()) {
        Author author = authorOptional.get();
       
        updates.forEach((key, value) -> {
            switch (key) {
                case "name": author.setName((String) value); break;
                case "biography_info": author.setBiography_info((String) value);
                break;
            }
        });

        return authorService.save(author);
    }
    else
    {
        return null;
    }
}
 @DeleteMapping("api/delete_authors/{id}")
    public void deleteAuthor(@PathVariable("id") Long authorId) {
    // Lógica para eliminar un autor existente utilizando el servicio AuthorService
    Optional<Author> authorOptional = authorService.findById(authorId);
    authorOptional.ifPresent(authorService::delete);
}
}
