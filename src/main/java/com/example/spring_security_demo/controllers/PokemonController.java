package com.example.spring_security_demo.controllers;

import com.example.spring_security_demo.models.Pokemon;
import com.example.spring_security_demo.services.PokemonService;
import com.example.spring_security_demo.utils.CustomResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/pokemons")
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping()
    public Map<String,Object> getPokemons() {
        CustomResponse<List<Pokemon>> customResponse = new CustomResponse<List<Pokemon>>();
       return customResponse.getResponseMessage("success","Successfully Fetched",pokemonService.getPokemons());
    }

    @PostMapping()
    public Map<String,Object> createPokemon(@RequestBody Pokemon pokemon) {
        pokemonService.save(pokemon);
        CustomResponse<Pokemon> customResponse = new CustomResponse<Pokemon>();
        return customResponse.getResponseMessage("success","Successfully Created",pokemon);
    }

    @GetMapping("/{id}")
    public Map<String,Object> getPokemon(@PathVariable String id) {
        Pokemon pokemon = pokemonService.getPokemon(id);
        CustomResponse<Pokemon> customResponse = new CustomResponse<Pokemon>();
        return pokemon != null ?
                customResponse.getResponseMessage("success","Successfully Fetched",pokemon)
                :
                customResponse.getResponseMessage("fail","Failed to fetch",null);
    }

    @PutMapping("/{id}")
    public Map<String,Object> updatePokemon(@RequestBody Pokemon pokemon,@PathVariable String id) {
        Pokemon updatedPokemon = pokemonService.update(pokemon,id);
        CustomResponse<Pokemon> customResponse = new CustomResponse<Pokemon>();
        return updatedPokemon != null ?
                customResponse.getResponseMessage("success","Successfully Updated",updatedPokemon)
                :
                customResponse.getResponseMessage("fail","Failed to update",null);

    }

    @DeleteMapping("/{id}")
    public Map<String,Object> deletePokemon(@PathVariable String id) {
        pokemonService.delete(id);
        CustomResponse<Pokemon> customResponse = new CustomResponse<Pokemon>();
        return customResponse.getResponseMessage("success","Successfully Deleted",null);

    }
}
