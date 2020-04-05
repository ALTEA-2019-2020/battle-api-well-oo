package com.miage.altea.battle_api.trainers.service;


import com.miage.altea.battle_api.customTrainers.TrainerPokemonTypeWithLevel;

public interface TrainerService {
    TrainerPokemonTypeWithLevel getTrainer(String name);
}
