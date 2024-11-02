package com.gaw.dvdrental.service;

import com.gaw.dvdrental.model.dto.response.ActorResponse;

public interface ActorService {
  ActorResponse findAllActors();

  ActorResponse findPaginated(int page, int size, String sortBy, String sortDir);
}
