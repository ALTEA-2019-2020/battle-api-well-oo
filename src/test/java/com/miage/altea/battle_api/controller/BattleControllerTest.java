package com.miage.altea.battle_api.controller;

import com.miage.altea.battle_api.bo.Battle;
import com.miage.altea.battle_api.customTrainers.TrainerPokemonTypeWithLevel;
import com.miage.altea.battle_api.exceptions.BattleExceptionNoTrainerWithThisName;
import com.miage.altea.battle_api.exceptions.BattleExceptionNoUuid;
import com.miage.altea.battle_api.exceptions.BattleExceptionWinner;
import com.miage.altea.battle_api.exceptions.BattleExceptionWrongTurn;
import com.miage.altea.battle_api.service.BattleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BattleControllerTest {

    private UUID uuid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");

    @Mock
    private BattleService battleService;


    @InjectMocks
    private BattleController battleController;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
        when(battleService.createBattle(any(String.class), any(String.class))).thenReturn(new Battle(this.uuid,null,null));
    }

    @Test
    void createBattle_shouldCallTheService(){
        battleController.createBattle("a", "b");
        verify(battleService).createBattle("a", "b");
    }

    @Test
    void getBattles_shouldCallTheService(){
        battleController.getBattles();
        verify(battleService).getBattles();
    }

    @Test
    void getBattle_shouldCallTheService() throws BattleExceptionNoUuid {
        battleController.getBattle(this.uuid);
        verify(battleService).getBattle(this.uuid);
    }

    @Test
    void attack_shouldCallTheService() throws BattleExceptionNoUuid, BattleExceptionNoTrainerWithThisName, BattleExceptionWrongTurn, BattleExceptionWinner {
        battleController.attack(this.uuid, "a");
        verify(battleService).attack(this.uuid, "a");
    }

    @Test
    void getBattles_shouldBeAnnotated() throws NoSuchMethodException {
        var getBattles =
                BattleController.class.getDeclaredMethod("getBattles");
        var getMapping = getBattles.getAnnotation(GetMapping.class);

        assertNotNull(getMapping);
        assertArrayEquals(new String[]{"/battles"}, getMapping.value());
    }

    @Test
    void getBattle_shouldBeAnnotated() throws NoSuchMethodException {
        var getBattle =
                BattleController.class.getDeclaredMethod("getBattle", UUID.class);
        var getMapping = getBattle.getAnnotation(GetMapping.class);

        var pathVariableAnnotation = getBattle.getParameters()[0].getAnnotation(PathVariable.class);

        assertNotNull(getMapping);
        assertArrayEquals(new String[]{"/battles/{uuid}"}, getMapping.value());
        assertNotNull(pathVariableAnnotation);
    }

    @Test
    void createBattle_shouldBeAnnotated() throws NoSuchMethodException{
        var createBattle = BattleController.class.getDeclaredMethod("createBattle", String.class, String.class);
        var postMapping = createBattle.getAnnotation(PostMapping.class);
        var requestParamAnnotation = createBattle.getParameters()[0].getAnnotation(RequestParam.class);
        var requestParamAnnotation1 = createBattle.getParameters()[1].getAnnotation(RequestParam.class);
        assertNotNull(postMapping);
        assertArrayEquals(new String[]{"/battles"}, postMapping.value());
        assertNotNull(requestParamAnnotation);
        assertNotNull(requestParamAnnotation1);
    }

    @Test
    void attack_shouldBeAnnotated() throws NoSuchMethodException{
        var attack = BattleController.class.getDeclaredMethod("attack", UUID.class, String.class);
        var postMapping = attack.getAnnotation(PostMapping.class);
        var pathVariableAnnotation = attack.getParameters()[0].getAnnotation(PathVariable.class);
        var pathVariableAnnotation1 = attack.getParameters()[1].getAnnotation(PathVariable.class);
        assertNotNull(postMapping);
        assertArrayEquals(new String[]{"/battles/{uuid}/{trainerName}/attack"}, postMapping.value());
        assertNotNull(pathVariableAnnotation);
        assertNotNull(pathVariableAnnotation1);
    }

}
