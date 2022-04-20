package com.game.stonegame.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PitsAndStones {

    @JsonProperty
    private boolean user1Playing;

    @JsonProperty
    private int[] user1Pits;

    @JsonProperty
    private int[] user2Pits;

    @JsonProperty
    private int inputPitPosition;

    @JsonIgnore
    private int numberOfStonesToSow;
}
