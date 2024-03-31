package com.project.proiectspring.service;

import com.project.proiectspring.exception.PublisherNotFoundException;
import com.project.proiectspring.model.Publisher;
import com.project.proiectspring.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public Publisher create(Publisher publisher) { return publisherRepository.save(publisher); }

    public Publisher update(Publisher publisher) {
        Optional<Publisher> existingPublisher = publisherRepository.findById(publisher.getId());
        if (existingPublisher.isEmpty()) {
            throw new PublisherNotFoundException();
        }
        return publisherRepository.save(publisher);
    }

    public List<Publisher> get(String name) {
        List<Publisher> publishers = new ArrayList<>();

        if (name != null) {
            publishers = publisherRepository.findByNameContainingIgnoreCase(name);
        } else {
            publishers = publisherRepository.findAll();
        }

        return publishers;
    }
}
