package com.gaw.dvdrental.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gaw.dvdrental.model.dto.ActorDto;
import com.gaw.dvdrental.model.dto.response.ActorResponse;
import com.gaw.dvdrental.model.entity.Actor;
import com.gaw.dvdrental.repository.ActorRepository;
import com.gaw.dvdrental.service.ActorService;
import com.gaw.dvdrental.util.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    Utils utils;


    private final ActorRepository actorRepository;

    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public ActorResponse findAllActors() {
        ActorResponse response = new ActorResponse();
        response.setData(actorRepository.findAll().stream().map(actor -> convertToActorResponsesDto(actor, response)).collect(Collectors.toList()));
        return response;
    }

    @Override
    public ActorResponse findPaginated(int page, int size, String sortBy, String sortDir) {
        Sort sort = Sort.by(sortBy).ascending().and(Sort.by("lastName").descending());
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Actor> pageActor = actorRepository.findAll(pageable);

        utils.printJson(pageActor);


        return mapActorResponse(pageActor);
    }


    private ActorResponse mapActorResponse(Page<Actor> page){
        ActorResponse response = new ActorResponse();
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setCurrentPage(page.getNumber());
        response.setSize(page.getSize());
        response.setFirst(page.isFirst());
        response.setLast(page.isLast());
        response.setNumberOfElements(page.getNumberOfElements());
        response.setEmpty(page.isEmpty());

        response.setData(page
                .getContent()
                .stream()
                .map(actor -> convertToActorResponsesDto(actor, response))
                .collect(Collectors.toList()));

        utils.printJson(response);

        return response;
    }

    private ActorResponse.Actor convertToActorResponsesDto(Actor actor, ActorResponse response) {
        ActorResponse.Actor dto  = response.new Actor();

        dto.setActorId(String.valueOf(actor.getActorId()));
        dto.setFirstName(actor.getFirstName());
        dto.setLastName(actor.getLastName());
        dto.setLastUpdate(actor.getLastUpdate());

//        postDto.setSubmissionDate(post.getSubmissionDate(),
//                userService.getCurrentUser().getPreference().getTimezone());
        return dto;
    }

    private Actor convertToEntity(ActorDto dto) {
        Actor actor = modelMapper.map(dto, Actor.class);
//        post.setSubmissionDate(postDto.getSubmissionDateConverted(
//                userService.getCurrentUser().getPreference().getTimezone()));
//
//        if (postDto.getId() != null) {
//            Post oldPost = postService.getPostById(postDto.getId());
//            post.setRedditID(oldPost.getRedditID());
//            post.setSent(oldPost.isSent());
//        }
        return actor;
    }

}
