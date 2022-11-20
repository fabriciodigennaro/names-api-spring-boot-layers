package com.example.demo.repository;

import com.example.demo.model.Name;

import java.util.List;

public interface NamesRepository {

    List<Name> getAll();

    Name getName(String name);

    void createName(Name name);

    void updateName(Name name);
}
