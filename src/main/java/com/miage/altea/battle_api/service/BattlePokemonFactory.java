package com.miage.altea.battle_api.service;

import com.miage.altea.battle_api.bo.BattlePokemon;
import com.miage.altea.battle_api.pokemonTypes.bo.PokemonType;

public class BattlePokemonFactory {

    public BattlePokemonFactory(){

    }

    private static BattlePokemonFactory factory = new BattlePokemonFactory();

    public static BattlePokemonFactory getBattlePokemonFactory(){
        return factory;
    }

    public BattlePokemon createBattlePokemon(PokemonType pokemonType, int level){
        int hp = StatsCalculator.calculateHP(pokemonType.getStats().getHp(), level);
        int attack = StatsCalculator.calculateStat(pokemonType.getStats().getAttack(), level);
        int defense = StatsCalculator.calculateStat(pokemonType.getStats().getDefense(), level);
        int speed = StatsCalculator.calculateStat(pokemonType.getStats().getSpeed(), level);
        return new BattlePokemon(hp, hp, attack, defense,speed, level,false, pokemonType);
    }

}
