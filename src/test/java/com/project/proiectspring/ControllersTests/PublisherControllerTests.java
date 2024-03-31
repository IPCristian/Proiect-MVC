package com.project.proiectspring.ControllersTests;

import com.project.proiectspring.controller.PublisherController;
import com.project.proiectspring.dto.CreatePublisherDto;
import com.project.proiectspring.mapper.PublisherMapper;
import com.project.proiectspring.model.Publisher;
import com.project.proiectspring.service.PublisherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PublisherControllerTest {

    @Mock
    private PublisherService publisherService;

    @Mock
    private PublisherMapper publisherMapper;

    @InjectMocks
    private PublisherController publisherController;

    @Test
    void testGet() {
        when(publisherService.get(null)).thenReturn(Collections.singletonList(new Publisher()));
        List<Publisher> result = publisherController.get(null);

        assertEquals(1, result.size());
    }

    @Test
    void testCreate() {
        CreatePublisherDto createPublisherDto = new CreatePublisherDto();
        Publisher publisher = new Publisher();
        when(publisherMapper.createPublisherDtoToPublisher(createPublisherDto)).thenReturn(publisher);
        when(publisherService.create(publisher)).thenReturn(publisher);

        ResponseEntity<Publisher> responseEntity = publisherController.create(createPublisherDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
}
