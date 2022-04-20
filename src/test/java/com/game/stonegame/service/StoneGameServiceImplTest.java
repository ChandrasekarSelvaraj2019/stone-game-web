package com.game.stonegame.service;

import com.game.stonegame.model.PitsAndStones;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoneGameServiceImplTest {

    StoneGameServiceImpl stoneGameService = new StoneGameServiceImpl();

    @Test
    void moveStones_lastStoneOnBigPit_userTurnContinued() {
        PitsAndStones pitsAndStones = PitsAndStones.builder()
                .user1Pits(new int[]{6, 6, 6, 6, 6, 6, 0})
                .user2Pits(new int[]{6, 6, 6, 6, 6, 6, 0})
                .inputPitPosition(0)
                .user1Playing(true)
                .build();

        stoneGameService.moveStones(pitsAndStones);
        assertArrayEquals(new int[]{0, 7, 7, 7, 7, 7, 1}, pitsAndStones.getUser1Pits());
        assertArrayEquals(new int[]{6, 6, 6, 6, 6, 6, 0}, pitsAndStones.getUser2Pits());
        assertTrue(pitsAndStones.isUser1Playing());
    }

    @Test
    void moveStones_lastStoneOnEmptyPit_stoneCollected() {
        PitsAndStones pitsAndStones = PitsAndStones.builder()
                .user1Pits(new int[]{6, 6, 6, 6, 1, 0, 0})
                .user2Pits(new int[]{6, 6, 6, 6, 6, 6, 0})
                .inputPitPosition(4)
                .user1Playing(true)
                .build();

        stoneGameService.moveStones(pitsAndStones);
        assertArrayEquals(new int[]{6, 6, 6, 6, 0, 7, 0}, pitsAndStones.getUser1Pits());
        assertArrayEquals(new int[]{0, 6, 6, 6, 6, 6, 0}, pitsAndStones.getUser2Pits());
        assertFalse(pitsAndStones.isUser1Playing());
    }

    @Test
    void getTotalStonesFromSelectedPit_user1Playing_user1PitStoneReturned() {
        PitsAndStones pitsAndStones = PitsAndStones.builder()
                .user1Pits(new int[]{6, 6, 6, 6, 6, 6, 6})
                .user2Pits(new int[]{1, 1, 1, 1, 1, 1, 1})
                .inputPitPosition(0)
                .user1Playing(true)
                .build();

        int totalStonesFromSelectedPit = stoneGameService.getTotalStonesFromSelectedPit(pitsAndStones);
        assertEquals(6, totalStonesFromSelectedPit);
    }

}