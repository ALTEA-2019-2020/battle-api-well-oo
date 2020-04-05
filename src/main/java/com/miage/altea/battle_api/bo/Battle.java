package com.miage.altea.battle_api.bo;

import com.miage.altea.battle_api.service.StatsCalculator;

import java.util.UUID;

public class Battle {

    private UUID uuid;
    private BattleTrainer trainer;
    private BattleTrainer opponent;

    public Battle(UUID uuid, BattleTrainer trainer, BattleTrainer opponent) {
        this.uuid = uuid;
        this.trainer = trainer;
        this.opponent = opponent;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public BattleTrainer getTrainer() {
        return trainer;
    }

    public void setTrainer(BattleTrainer trainer) {
        this.trainer = trainer;
    }

    public BattleTrainer getOpponent() {
        return opponent;
    }

    public void setOpponent(BattleTrainer opponent) {
        this.opponent = opponent;
    }

    public boolean checkTrainerByName(String trainerName){
        return this.trainer.getName().equals(trainerName) || this.opponent.getName().equals((trainerName));
    }

    public BattleTrainer getTrainerByName(String trainerName){
        return this.checkTrainerByName(trainerName) ? (this.trainer.getName().equals(trainerName) ? this.trainer : this.opponent) : null;
    }

    public BattleTrainer getOtherTrainer(BattleTrainer trainer){
        return this.trainer.getName().equals(trainer.getName()) ? this.opponent : this.trainer;
    }

    public void switchTurn(){
        this.trainer.setNextTurn(!trainer.isNextTurn());
        this.opponent.setNextTurn(!opponent.isNextTurn());
    }

    public boolean checkIfOneTrainerIsKo(){
        return this.trainer.getTeam().stream().allMatch(BattlePokemon::isKo) || this.opponent.getTeam().stream().allMatch(BattlePokemon::isKo);
    }


    public BattleTrainer getWinner(){
        if(this.checkIfOneTrainerIsKo()){
            if(this.trainer.getTeam().stream().allMatch(BattlePokemon::isKo)){
                return this.opponent;
            } else {
                return this.trainer;
            }
        } else {
            return null;
        }
    }

    public void attack(BattleTrainer attacker, BattleTrainer opponent){
        BattlePokemon attackerPokemon = attacker.getTeam().stream().filter(battlePokemon -> !battlePokemon.isKo()).findFirst().get();
        BattlePokemon opponentPokemon = opponent.getTeam().stream().filter(battlePokemon -> !battlePokemon.isKo()).findFirst().get();
        int damage = StatsCalculator.calculateDamage(attackerPokemon, opponentPokemon);
        opponentPokemon.setHp(opponentPokemon.getHp() - damage);
        if(opponentPokemon.getHp() <= 0){
            opponentPokemon.setHp(0);
            opponentPokemon.setKo(true);
        }
        this.switchTurn();
    }

}

