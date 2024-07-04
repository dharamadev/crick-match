package com.crickmatch.core;

import com.crickmatch.dto.Team;

import java.util.*;

public class SelectPlayer {

    private final List<com.crickmatch.dto.Player> player;
    private static List<Integer> nums;
    private final Integer teamSize;

    public SelectPlayer(Team team) {
        this.player = team.getPlayers();
        this.teamSize = team.getPlayers().size();
        nums = new ArrayList<>();
    }

    public com.crickmatch.dto.Player bowler(Integer bowlerLineup) {
        return player.get(randomNum(bowlerLineup, 11));
    }

    public com.crickmatch.dto.Player batsMan(Integer batsman) {
        return player.get(randomNum(0, batsman));
    }

    private Integer randomNum(Integer start, Integer end) {
        if (end >= teamSize && start <= 0) throw new RuntimeException("No bowler");
        Random random = new Random();
        while (true) {
            Integer num = random.nextInt(end-1) + start;
            if (!nums.contains(num)) {
                nums.add(num);
                return num;
            }
        }
    }
}
