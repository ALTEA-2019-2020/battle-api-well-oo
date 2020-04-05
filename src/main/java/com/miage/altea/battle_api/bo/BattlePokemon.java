package com.miage.altea.battle_api.bo;

import com.miage.altea.battle_api.pokemonTypes.bo.PokemonType;

public class BattlePokemon {

    private int hp;
    private int maxHp;
    private int attack;
    private int defense;
    private int speed;
    private int level;
    private boolean ko;
    private PokemonType type;

    public BattlePokemon(int hp, int maxHp, int attack, int defense, int speed, int level, boolean ko, PokemonType pokemonType) {
        this.hp = hp;
        this.maxHp = maxHp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.level = level;
        this.ko = ko;
        this.type = pokemonType;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isKo() {
        return ko;
    }

    public void setKo(boolean ko) {
        this.ko = ko;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public PokemonType getType() {
        return type;
    }

    public void setType(PokemonType type) {
        this.type = type;
    }
}
