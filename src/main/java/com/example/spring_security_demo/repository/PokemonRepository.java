package com.example.spring_security_demo.repository;

import com.example.spring_security_demo.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon,String> {

    List<Pokemon> findAll();
}
