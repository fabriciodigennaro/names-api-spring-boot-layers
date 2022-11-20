package com.example.demo.controller;

import com.example.demo.dto.NameDTO;
import com.example.demo.model.Name;
import org.springframework.stereotype.Component;

@Component
public class NamesMapper {

    public NameDTO nameToNameDTO(Name name) {
        NameDTO nameDTO = new NameDTO();
        nameDTO.setName(name.getName());
        nameDTO.setGender(name.getGender());
        nameDTO.setOrigin(name.getOrigin());
        nameDTO.setMeaning(name.getMeaning());

        return nameDTO;
    }

    public Name nameDTOToName(NameDTO nameDTO) {
        Name name = new Name();
        name.setName(nameDTO.getName());
        name.setGender(nameDTO.getGender());
        name.setOrigin(nameDTO.getOrigin());
        name.setMeaning(nameDTO.getMeaning());
        return name;
    }
}
