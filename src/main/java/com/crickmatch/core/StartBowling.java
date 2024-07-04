package com.crickmatch.core;

import com.crickmatch.dto.Bowl;
import com.crickmatch.dto.Player;
import com.crickmatch.dto.constant.BowlType;

import java.util.Random;

public class StartBowling {

    private final Player player;

    public StartBowling(Player player) {
        this.player = player;
    }

    public Bowl play() {
        return new Bowl(player, getType());
    }

    private BowlType getType() {
        Random random = new Random();
        BowlType[] types = BowlType.values();
        return types[random.nextInt(0, types.length-1)];
    }
}
