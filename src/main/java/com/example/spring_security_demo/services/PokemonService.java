package com.example.spring_security_demo.services;

import com.example.spring_security_demo.models.Pokemon;
import com.example.spring_security_demo.repository.PokemonRepository;
import com.example.spring_security_demo.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {

    @Autowired
    private final PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public List<Pokemon> getPokemons() {
        return pokemonRepository.findAll();
    }

    public void save(Pokemon pokemon) {
        pokemon.setId(IdGenerator.generateId());
        pokemonRepository.save(pokemon);
    }

    public Pokemon getPokemon(String pokemonId) {
        return pokemonRepository.findById(pokemonId).orElse(null);
    }

    public Pokemon update(Pokemon pokemon,String pokemonId) {
        Pokemon pokemonToUpdate = pokemonRepository.findById(pokemonId).orElse(null);

        if(pokemonToUpdate != null) {
            pokemonToUpdate.setAttribute(pokemon.getAttribute());
            pokemonToUpdate.setName(pokemon.getName());
            pokemonToUpdate.setSkill(pokemon.getSkill());
            pokemonToUpdate.setDescription(pokemon.getDescription());
            pokemonRepository.save(pokemonToUpdate);
        }
        return pokemonToUpdate;
    }

    public void delete(String pokemonId) {
        pokemonRepository.deleteById(pokemonId);
    }

}
