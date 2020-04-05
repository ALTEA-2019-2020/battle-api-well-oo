package com.miage.altea.battle_api.customTrainers;



import com.miage.altea.battle_api.pokemonTypes.bo.Sprites;
import com.miage.altea.battle_api.pokemonTypes.bo.Stats;

import java.util.List;

public class PokemonTypeWithLevel {

    private int id;
    private int level;
    private int baseExperience;
    private int height;
    private String name;
    private Sprites sprites;
    private Stats stats;
    private int weight;
    private List<String> types;

    public PokemonTypeWithLevel(int id, int level, int baseExperience, int height, String name, Sprites sprites, Stats stats, int weight, List<String> types) {
        this.id = id;
        this.level = level;
        this.baseExperience = baseExperience;
        this.height = height;
        this.name = name;
        this.sprites = sprites;
        this.stats = stats;
        this.weight = weight;
        this.types = types;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(int baseExperience) {
        this.baseExperience = baseExperience;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
