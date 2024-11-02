package com.gaw.dvdrental.controller;

import com.gaw.dvdrental.model.dto.response.ActorResponse;
import com.gaw.dvdrental.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ActorController {

  @Autowired private ActorService actorService;

  @GetMapping(value = {"/admin/actors", "/user/actors"})
  public String getListActors(Model model) {
    System.out.println("actors");
    return findPaginatedActorsAdmin(model, 2, 5, "actorId", "desc");
  }

  @GetMapping(
      value = {"/admin/actors", "/user/actors"},
      params = {"page", "size", "sortBy", "sortDir"})
  public String findPaginatedActorsAdmin(
      Model model,
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "size", defaultValue = "10") int size,
      @RequestParam(value = "sortBy", defaultValue = "actorId") String sortBy,
      @RequestParam(value = "sortDir", defaultValue = "desc") String sortDir) {
    System.out.println("~findPaginatedActorsAdmin");

    ActorResponse actorResponse = actorService.findPaginated(page, size, sortBy, sortDir);

    model.addAttribute("actors", actorResponse.getData());
    model.addAttribute("totalItems", actorResponse.getTotalElements());
    model.addAttribute("totalPages", actorResponse.getTotalPages());
    return "actor";
  }
}
