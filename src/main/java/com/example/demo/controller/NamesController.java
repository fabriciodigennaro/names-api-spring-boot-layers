package com.example.demo.controller;

import com.example.demo.dto.NameDTO;
import com.example.demo.model.Name;
import com.example.demo.service.NamesService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("names")
public class NamesController {

    private NamesService namesService;
    private NamesMapper namesMapper;

    public NamesController(NamesService namesService, NamesMapper namesMapper) {
        this.namesService = namesService;
        this.namesMapper = namesMapper;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<String> getNames() {
        return namesService.getNames();
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public NameDTO getName(@PathVariable String name) {
        Name nameResponse = namesService.getName(name);
        return namesMapper.nameToNameDTO(nameResponse);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NameDTO createName(@RequestBody NameDTO request) {
        Name name = namesMapper.nameDTOToName(request);
        namesService.createName(name);
        return request;
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public NameDTO updateName(@PathVariable String name, @RequestBody NameDTO request) {
        Name nameRequest = namesMapper.nameDTOToName(request);
        namesService.updateName(nameRequest);
        return request;
    }

}
