package com.miage.altea.battle_api.controller;

import com.miage.altea.battle_api.bo.Battle;
import com.miage.altea.battle_api.exceptions.BattleExceptionNoTrainerWithThisName;
import com.miage.altea.battle_api.exceptions.BattleExceptionNoUuid;
import com.miage.altea.battle_api.exceptions.BattleExceptionWinner;
import com.miage.altea.battle_api.exceptions.BattleExceptionWrongTurn;
import com.miage.altea.battle_api.service.BattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class BattleController {

    private BattleService battleService;

    @CrossOrigin
    @PostMapping("/battles")
    public UUID createBattle(@RequestParam String trainer, @RequestParam String opponent){
        return this.battleService.createBattle(trainer, opponent).getUuid();
    }

    @CrossOrigin
    @GetMapping("/battles")
    public List<Battle> getBattles(){
        return this.battleService.getBattles();
    }

    @CrossOrigin
    @GetMapping("/battles/{uuid}")
    public ResponseEntity getBattle(@PathVariable UUID uuid){
        try {
            return ResponseEntity.ok(this.battleService.getBattle(uuid));
        } catch (BattleExceptionNoUuid battleExceptionNoUuid) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(battleExceptionNoUuid.getMessage());
        }
    }

    @CrossOrigin
    @PostMapping("/battles/{uuid}/{trainerName}/attack")
    public ResponseEntity attack(@PathVariable UUID uuid, @PathVariable String trainerName){
        try {
            return ResponseEntity.ok(this.battleService.attack(uuid, trainerName));
        } catch (BattleExceptionNoUuid | BattleExceptionWrongTurn | BattleExceptionNoTrainerWithThisName | BattleExceptionWinner e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Autowired
    public void setBattleService(BattleService battleService){
        this.battleService = battleService;
    }

}
