package com.project.proiectspring.service;

import com.project.proiectspring.exception.AuthorNotFoundException;
import com.project.proiectspring.model.Author;
import com.project.proiectspring.repository.AuthorRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author create(Author author) { return authorRepository.save(author); }

    public Author update(Author oldAuthor, Author author) {
        Optional<Author> existingAuthor = authorRepository.findById(oldAuthor.getId());
        if (existingAuthor.isEmpty()) {
            throw new AuthorNotFoundException();
        }

        oldAuthor.setLastName(author.getLastName());
        oldAuthor.setFirstName(author.getFirstName());
        oldAuthor.setBiography(author.getBiography());

        return authorRepository.save(oldAuthor);
    }

    public List<Author> get(String lastName) {

        List<Author> authors = new ArrayList<>();

        if(lastName != null && !lastName.isEmpty()) {
            authors = authorRepository.findByLastName(lastName);
        } else {
            authors = authorRepository.findAll();
        }
        return authors;
    }

    public Author get(Long id) {

        if(id != null) {
            Optional<Author> author = authorRepository.findById(id);

            if (author.isPresent()) {
                return author.get();
            }
        }

        return null;
    }

    public List<Author> getSortedAuthors(Sort sort) {
        return authorRepository.findAll(sort);
    }
}
