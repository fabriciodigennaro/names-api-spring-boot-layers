package com.example.demo.service;

import com.example.demo.model.Name;
import com.example.demo.repository.NamesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NamesServiceImpl implements NamesService {

    private NamesRepository namesRepository;

    public NamesServiceImpl(NamesRepository namesRepository) {
        this.namesRepository = namesRepository;
    }

    @Override
    public List<String> getNames() {
        List<Name> names = namesRepository.getAll();
        List<String> namesList = new ArrayList<>();
        for (Name name : names){
            namesList.add(name.getName());
        }
        return namesList;
    }

    @Override
    public Name getName(String name) {
        return namesRepository.getName(name);
    }

    @Override
    public void createName(Name name) {
        namesRepository.createName(name);
    }

    @Override
    public void updateName(Name name) {
        namesRepository.updateName(name);
    }
}
