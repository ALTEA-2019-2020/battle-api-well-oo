package com.miage.altea.battle_api.service;

import com.miage.altea.battle_api.bo.BattlePokemon;
import com.miage.altea.battle_api.pokemonTypes.bo.PokemonType;
import com.miage.altea.battle_api.pokemonTypes.bo.Stats;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BattlePokemonFactoryTest {

    @Test
    public void createBattlePokemonTest(){
        PokemonType pikachu = new PokemonType();
        pikachu.setName("pikachu");
        Stats pikachuStats = new Stats();
        pikachuStats.setAttack(55);
        pikachuStats.setDefense(40);
        pikachuStats.setHp(35);
        pikachuStats.setSpeed(90);
        pikachu.setStats(pikachuStats);
        BattlePokemon pika = BattlePokemonFactory.getBattlePokemonFactory().createBattlePokemon(pikachu, 18);
        assertEquals(pika.getLevel(), 18);
        assertEquals(pika.getHp(), 40);
        assertEquals(pika.getMaxHp(), 40);
        assertEquals(pika.getAttack(), 24);
        assertEquals(pika.getDefense(), 19);
        assertEquals(pika.getSpeed(), 37);
        assertEquals(pika.isKo(), false);
        assertEquals(pika.getType(), pikachu);
    }

}
