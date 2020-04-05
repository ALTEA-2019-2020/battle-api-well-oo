package com.miage.altea.battle_api.service;

import com.miage.altea.battle_api.bo.Battle;
import com.miage.altea.battle_api.exceptions.BattleExceptionNoTrainerWithThisName;
import com.miage.altea.battle_api.exceptions.BattleExceptionNoUuid;
import com.miage.altea.battle_api.exceptions.BattleExceptionWinner;
import com.miage.altea.battle_api.exceptions.BattleExceptionWrongTurn;

import java.util.List;
import java.util.UUID;

public interface BattleService {

    Battle createBattle(String trainer,String opponent);

    List<Battle> getBattles();

    Battle getBattle(UUID uuid) throws BattleExceptionNoUuid;

    Battle attack(UUID uuid, String trainerName) throws BattleExceptionNoUuid, BattleExceptionWrongTurn, BattleExceptionNoTrainerWithThisName, BattleExceptionWinner;

}
