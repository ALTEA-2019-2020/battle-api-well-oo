package com.miage.altea.battle_api.trainers.service;


import com.miage.altea.battle_api.customTrainers.PokemonTypeWithLevel;
import com.miage.altea.battle_api.customTrainers.TrainerPokemonTypeWithLevel;
import com.miage.altea.battle_api.pokemonTypes.bo.PokemonType;
import com.miage.altea.battle_api.pokemonTypes.service.PokemonTypeService;
import com.miage.altea.battle_api.trainers.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainerServiceImpl implements TrainerService {

    private RestTemplate restTemplate;
    private String url;
    private PokemonTypeService pokemonTypeService;

    @Override
    public TrainerPokemonTypeWithLevel getTrainer(String name) {
        Trainer trainer =  this.restTemplate.getForObject(this.url + "/trainers/" + name, Trainer.class);
        List<PokemonTypeWithLevel> team = trainer.getTeam().stream().map(pokemon -> {
            PokemonType pokemonType = pokemonTypeService.getPokemonType(pokemon.getPokemonTypeId());
            return new PokemonTypeWithLevel(pokemon.getPokemonTypeId(),
                                            pokemon.getLevel(),
                                            pokemonType.getBaseExperience(),
                                            pokemonType.getHeight(),
                                            pokemonType.getName(),
                                            pokemonType.getSprites(),
                                            pokemonType.getStats(),
                                            pokemonType.getWeight(),
                                            pokemonType.getTypes());
        }).collect(Collectors.toList());

        return new TrainerPokemonTypeWithLevel(trainer, team);
    }

    @Qualifier("trainerApiRestTemplate")
    @Autowired
    void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    void setPokemonTypeService(PokemonTypeService pokemonTypeService) {
        this.pokemonTypeService = pokemonTypeService;
    }

    @Value("${trainers.service.url}")
    void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        this.url = pokemonServiceUrl;
    }

}
