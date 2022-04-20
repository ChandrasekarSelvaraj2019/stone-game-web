package com.game.stonegame.service;

import com.game.stonegame.model.PitsAndStones;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
@Slf4j
public class StoneGameServiceImpl {

    public PitsAndStones moveStones(PitsAndStones pitsAndStones) {
        int totalStones = getTotalStonesFromSelectedPit(pitsAndStones);
        int inputPitPosition = pitsAndStones.getInputPitPosition();
        pitsAndStones.setNumberOfStonesToSow(totalStones);

        if (pitsAndStones.isUser1Playing()) {
            pitsAndStones.getUser1Pits()[inputPitPosition] = 0;
            pitsAndStones.setUser1Playing(!pitsAndStones.isUser1Playing());
            sowToNextPits(inputPitPosition, pitsAndStones.getUser1Pits(), pitsAndStones.getUser2Pits(), pitsAndStones);
        } else {
            pitsAndStones.getUser2Pits()[inputPitPosition] = 0;
            pitsAndStones.setUser1Playing(!pitsAndStones.isUser1Playing());
            sowToNextPits(inputPitPosition, pitsAndStones.getUser2Pits(), pitsAndStones.getUser1Pits(), pitsAndStones);
        }

        log.error("user 1 pits" + Arrays.toString(pitsAndStones.getUser1Pits()) + "\n" + "user 2 pits" + Arrays.toString(pitsAndStones.getUser2Pits()));
        return pitsAndStones;
    }

    int getTotalStonesFromSelectedPit(PitsAndStones pitsAndStones) {
        int inputPitPosition = pitsAndStones.getInputPitPosition();
        return pitsAndStones.isUser1Playing() ? pitsAndStones.getUser1Pits()[inputPitPosition] : pitsAndStones.getUser2Pits()[inputPitPosition];
    }

    private void sowToNextPits(int inputPitPosition, int[] user1Pits, int[] user2Pits, PitsAndStones pitsAndStones) {
        for (int i = inputPitPosition + 1; i < user1Pits.length; i++) {
            user1Pits[i] += 1;
            pitsAndStones.setNumberOfStonesToSow(pitsAndStones.getNumberOfStonesToSow() - 1);
            if (pitsAndStones.getNumberOfStonesToSow() == 0) {
                if (user1Pits[i] == 1 && i != user1Pits.length - 1) {
                    int oppositePit = user2Pits.length - 2 - i;
                    user1Pits[i] = user1Pits[i] + user2Pits[oppositePit];
                    user2Pits[oppositePit] = 0;
                } else if (i == user1Pits.length - 1) {
                    pitsAndStones.setUser1Playing(!pitsAndStones.isUser1Playing());
                }
                break;
            }
        }
        if (pitsAndStones.getNumberOfStonesToSow() > 0) {
            for (int i = 0; i < user2Pits.length - 1; i++) {
                user2Pits[i] += 1;
                pitsAndStones.setNumberOfStonesToSow(pitsAndStones.getNumberOfStonesToSow() - 1);
                if (pitsAndStones.getNumberOfStonesToSow() == 0) {
                    break;
                }
            }
        }
        while (pitsAndStones.getNumberOfStonesToSow() != 0) {
            sowToNextPits(0, user1Pits, user2Pits, pitsAndStones);
        }
    }
}
