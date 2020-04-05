package com.miage.altea.battle_api.service;

import com.miage.altea.battle_api.bo.BattlePokemon;
import com.miage.altea.battle_api.pokemonTypes.bo.PokemonType;
import com.miage.altea.battle_api.pokemonTypes.bo.Stats;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatsCalculatorTest {

    @Test
    public void calculateStatTest(){
        assertEquals(StatsCalculator.calculateStat(55, 18), 24);
    }

    @Test
    public void calculateHpTest(){
        assertEquals(StatsCalculator.calculateHP(35, 18), 40);
    }

    @Test
    public void calculeDamageTest(){
        PokemonType pikachu = new PokemonType();
        pikachu.setName("pikachu");
        Stats pikachuStats = new Stats();
        pikachuStats.setAttack(55);
        pikachuStats.setDefense(40);
        pikachuStats.setHp(35);
        pikachuStats.setSpeed(90);
        pikachu.setStats(pikachuStats);

        PokemonType stari = new PokemonType();
        stari.setName("stari");
        Stats stariStats = new Stats();
        stariStats.setAttack(45);
        stariStats.setDefense(55);
        stariStats.setHp(30);
        stariStats.setSpeed(85);
        stari.setStats(stariStats);

        BattlePokemon pika = BattlePokemonFactory.getBattlePokemonFactory().createBattlePokemon(pikachu, 18);
        BattlePokemon star = BattlePokemonFactory.getBattlePokemonFactory().createBattlePokemon(stari, 18);
        assertEquals(StatsCalculator.calculateDamage(pika, star), 11);
    }
}
