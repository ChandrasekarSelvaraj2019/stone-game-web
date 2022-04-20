package com.game.stonegame.controller;

import com.game.stonegame.model.PitsAndStones;
import com.game.stonegame.service.StoneGameServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/stone-game")
@AllArgsConstructor
public class StoneGameController {

    private final StoneGameServiceImpl stoneGameService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PitsAndStones moveStones(@RequestBody PitsAndStones pitsAndStones) {
        return stoneGameService.moveStones(pitsAndStones);
    }
}
