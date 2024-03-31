package com.project.proiectspring.ServicesTests;

import com.project.proiectspring.exception.PublisherNotFoundException;
import com.project.proiectspring.model.Publisher;
import com.project.proiectspring.repository.PublisherRepository;
import com.project.proiectspring.service.PublisherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PublisherServiceTest {

    @Mock
    private PublisherRepository publisherRepository;

    @InjectMocks
    private PublisherService publisherService;

    @Test
    void testCreate() {
        Publisher publisher = new Publisher();
        when(publisherRepository.save(publisher)).thenReturn(publisher);

        Publisher result = publisherService.create(publisher);
        assertEquals(publisher, result);
    }

    @Test
    void testUpdate() {
        Publisher existingPublisher = new Publisher();
        when(publisherRepository.findById(existingPublisher.getId())).thenReturn(Optional.of(existingPublisher));
        when(publisherRepository.save(existingPublisher)).thenReturn(existingPublisher);

        Publisher result = publisherService.update(existingPublisher);
        assertEquals(existingPublisher, result);
    }

    @Test
    void testPublisherNotFound() {
        Publisher nonExistingPublisher = new Publisher();
        when(publisherRepository.findById(nonExistingPublisher.getId())).thenReturn(Optional.empty());

        assertThrows(PublisherNotFoundException.class, () -> publisherService.update(nonExistingPublisher));
    }

    @Test
    void testGetByName() {
        String name = "abc";
        List<Publisher> expectedPublishers = new ArrayList<>();
        when(publisherRepository.findByNameContainingIgnoreCase(name)).thenReturn(expectedPublishers);

        List<Publisher> result = publisherService.get(name);
        assertEquals(expectedPublishers, result);
    }

    @Test
    void testGetAll() {
        List<Publisher> expectedPublishers = Collections.singletonList(new Publisher());
        when(publisherRepository.findAll()).thenReturn(expectedPublishers);

        List<Publisher> result = publisherService.get(null);
        assertEquals(expectedPublishers, result);
    }
}
