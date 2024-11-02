package com.gaw.dvdrental.service;

import com.gaw.dvdrental.model.dto.ActorDto;
import com.gaw.dvdrental.model.dto.response.ActorResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ActorService {
    ActorResponse findAllActors();

    ActorResponse findPaginated(int page, int size, String sortBy, String sortDir);
}
