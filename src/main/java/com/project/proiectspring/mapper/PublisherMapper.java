package com.project.proiectspring.mapper;

import com.project.proiectspring.dto.CreatePublisherDto;
import com.project.proiectspring.dto.UpdatePublisherDto;
import com.project.proiectspring.model.Publisher;
import org.springframework.stereotype.Component;

@Component
public class PublisherMapper {

    public Publisher createPublisherDtoToPublisher(CreatePublisherDto createPublisherDto)
    {
        return new Publisher(
                createPublisherDto.getName(),
                createPublisherDto.getLocation(),
                createPublisherDto.getWebsite()
        );
    }

    public Publisher updatePublisherDtoToPublisher(UpdatePublisherDto updatePublisherDto)
    {
        return new Publisher(
                updatePublisherDto.getId(),
                updatePublisherDto.getName(),
                updatePublisherDto.getLocation(),
                updatePublisherDto.getWebsite()
        );
    }

}
