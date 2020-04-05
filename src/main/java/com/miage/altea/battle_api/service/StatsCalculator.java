package com.miage.altea.battle_api.service;

import com.miage.altea.battle_api.bo.BattlePokemon;

public class StatsCalculator {

    public static int calculateStat(int base, int level){
        return (int)(5 + (base * ((float)level/50)));
    }

    public static int calculateHP(int base, int level){
        return (int)(10 + level + (base * ((float)level/50)));
    }

    public static int calculateDamage(BattlePokemon attacker, BattlePokemon opponent){
        return (int)(((2*(float)attacker.getLevel())/5) + 2 * ((float)attacker.getAttack()/(float)opponent.getDefense())) + 2;
    }

}
