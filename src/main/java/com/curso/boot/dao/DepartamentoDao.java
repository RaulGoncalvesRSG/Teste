package com.curso.boot.dao;

import java.util.List;

import com.curso.boot.model.Departamento;

public interface DepartamentoDao {

    void save(Departamento departamento);

    void update(Departamento departamento);

    void delete(Long id);

    Departamento findById(Long id);

    List<Departamento> findAll();
}
