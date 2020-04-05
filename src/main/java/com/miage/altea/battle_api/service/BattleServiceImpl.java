package com.miage.altea.battle_api.service;

import com.miage.altea.battle_api.bo.Battle;
import com.miage.altea.battle_api.bo.BattleTrainer;
import com.miage.altea.battle_api.customTrainers.TrainerPokemonTypeWithLevel;
import com.miage.altea.battle_api.exceptions.BattleExceptionNoTrainerWithThisName;
import com.miage.altea.battle_api.exceptions.BattleExceptionNoUuid;
import com.miage.altea.battle_api.exceptions.BattleExceptionWinner;
import com.miage.altea.battle_api.exceptions.BattleExceptionWrongTurn;
import com.miage.altea.battle_api.pokemonTypes.service.PokemonTypeService;
import com.miage.altea.battle_api.trainers.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BattleServiceImpl implements BattleService {

    private TrainerService trainerService;

    private PokemonTypeService pokemonTypeService;

    private BattlePokemonFactory battlePokemonFactory;

    private HashMap<UUID, Battle> battles;

    @Autowired
    public BattleServiceImpl(TrainerService trainerService, PokemonTypeService pokemonTypeService){
        this.pokemonTypeService = pokemonTypeService;
        this.trainerService = trainerService;
        this.battlePokemonFactory = BattlePokemonFactory.getBattlePokemonFactory();
        this.battles = new HashMap<>();
    }


    @Override
    public Battle createBattle(String trainer, String opponent) {
        TrainerPokemonTypeWithLevel trainer1 = trainerService.getTrainer(trainer);
        TrainerPokemonTypeWithLevel opponent1 = trainerService.getTrainer(opponent);
        BattleTrainer battleTrainer1 = new BattleTrainer(trainer1.getTrainer().getName(), false, trainer1.getTeam().stream().map(
                pokemonTypeWithLevel -> this.battlePokemonFactory.createBattlePokemon(this.pokemonTypeService.getPokemonType(pokemonTypeWithLevel.getId()), pokemonTypeWithLevel.getLevel())
        ).collect(Collectors.toList()));

        BattleTrainer battleTrainer2 = new BattleTrainer(opponent1.getTrainer().getName(), false, opponent1.getTeam().stream().map(
                pokemonTypeWithLevel -> this.battlePokemonFactory.createBattlePokemon(this.pokemonTypeService.getPokemonType(pokemonTypeWithLevel.getId()), pokemonTypeWithLevel.getLevel())
        ).collect(Collectors.toList()));

        if(battleTrainer1.getTeam().get(0).getSpeed() >= battleTrainer2.getTeam().get(0).getSpeed()){
            battleTrainer1.setNextTurn(true);
        } else {
            battleTrainer2.setNextTurn(true);
        }

        Battle battle = new Battle(UUID.randomUUID(), battleTrainer1, battleTrainer2);
        this.battles.put(battle.getUuid(), battle);
        return battle;
    }

    @Override
    public List<Battle> getBattles() {
        return new ArrayList<>(this.battles.values());
    }

    @Override
    public Battle getBattle(UUID uuid) throws BattleExceptionNoUuid {
        if(!this.battles.containsKey(uuid)){
            throw new BattleExceptionNoUuid("No battle with this uuid : " +uuid);
        }
        return this.battles.get(uuid);
    }

    @Override
    public Battle attack(UUID uuid, String trainerName) throws BattleExceptionNoUuid, BattleExceptionWrongTurn, BattleExceptionNoTrainerWithThisName, BattleExceptionWinner {
        Battle myBattle = this.getBattle(uuid);
        if(!myBattle.checkTrainerByName(trainerName)){
            throw new BattleExceptionNoTrainerWithThisName("No trainer with the name " + trainerName + "in the battle " + myBattle.getUuid());
        } else {
            BattleTrainer attacker = myBattle.getTrainerByName(trainerName);
            if(!attacker.isNextTurn()){
                throw new BattleExceptionWrongTurn("It's not the turn of " + attacker.getName());
            } else {
                if(!myBattle.checkIfOneTrainerIsKo()) {
                    BattleTrainer opponent = myBattle.getOtherTrainer(attacker);
                    myBattle.attack(attacker, opponent);
                } else {
                    BattleTrainer winner = myBattle.getWinner();
                    throw new BattleExceptionWinner(winner.getName() + " has win the battle");
                }
            }
        }
        return myBattle;
    }
}
