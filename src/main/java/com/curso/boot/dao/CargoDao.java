package com.curso.boot.dao;

import java.util.List;

import com.curso.boot.model.Cargo;
import com.curso.boot.util.PaginacaoUtil;

public interface CargoDao {

    void save(Cargo cargo );

    void update(Cargo cargo);

    void delete(Long id);

    Cargo findById(Long id);

    List<Cargo> findAll();
    
    public PaginacaoUtil<Cargo> buscaPaginada(int pagina, String direcao);
}
