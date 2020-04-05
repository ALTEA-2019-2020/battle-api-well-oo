package com.miage.altea.battle_api.pokemonTypes.service;


import com.miage.altea.battle_api.pokemonTypes.bo.PokemonType;

import java.util.List;

public interface PokemonTypeService {

    List<PokemonType> listPokemonsTypes();

    PokemonType getPokemonType(int id);

}