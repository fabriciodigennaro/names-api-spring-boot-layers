package com.example.demo.service;

import com.example.demo.model.Name;

import java.util.List;

public interface NamesService {

    List<String> getNames();

    Name getName(String name);

    void createName(Name name);

    void updateName(Name name);
}
