package com.crickmatch;

import com.crickmatch.core.StartMatch;
import com.crickmatch.dto.*;
import com.crickmatch.dto.constant.PlayerType;

import java.util.ArrayList;
import java.util.List;

public class MatchSimulator {
    public static void main(String[] args) {
        Team team1 = new Team();
        team1.setName("India");
        List<Player> team1Players = new ArrayList<>();
        team1Players.add(new Player("Sourav", PlayerType.CAPTAIN));
        team1Players.add(new Player("Rohit", PlayerType.BATSMAN));
        team1Players.add(new Player("Rahul", PlayerType.BATSMAN));
        team1Players.add(new Player("Akshar", PlayerType.BATSMAN));
        team1Players.add(new Player("Sachin", PlayerType.BATSMAN));
        team1Players.add(new Player("Sourav", PlayerType.WICKET_KEEPER));
        team1Players.add(new Player("Sourav", PlayerType.BATSMAN));
        team1Players.add(new Player("Irfan", PlayerType.ALL_ROUNDER));
        team1Players.add(new Player("Kaif", PlayerType.ALL_ROUNDER));
        team1Players.add(new Player("Jaheer", PlayerType.BOWLER));
        team1Players.add(new Player("Harbhajan", PlayerType.BOWLER));
        team1Players.add(new Player("Bombrah", PlayerType.BOWLER));
        team1Players.add(new Player("Kumble", PlayerType.BOWLER));
        team1.setPlayers(team1Players);

        Team team2 = new Team();
        team2.setName("England");
        List<Player> team2Players = new ArrayList<>();
        team2Players.add(new Player("John", PlayerType.CAPTAIN));
        team2Players.add(new Player("John", PlayerType.BATSMAN));
        team2Players.add(new Player("Smith", PlayerType.BATSMAN));
        team2Players.add(new Player("Gail", PlayerType.BATSMAN));
        team2Players.add(new Player("Mathew", PlayerType.BATSMAN));
        team2Players.add(new Player("Roman", PlayerType.BATSMAN));
        team2Players.add(new Player("Ronny", PlayerType.WICKET_KEEPER));
        team2Players.add(new Player("Ronny", PlayerType.BATSMAN));
        team2Players.add(new Player("Johnney", PlayerType.ALL_ROUNDER));
        team2Players.add(new Player("Gorden", PlayerType.ALL_ROUNDER));
        team2Players.add(new Player("Jhonson", PlayerType.BOWLER));
        team2Players.add(new Player("Willy", PlayerType.BOWLER));
        team2Players.add(new Player("Germei", PlayerType.BOWLER));
        team2.setPlayers(team2Players);
        Inning inning1 = new Inning();
        inning1.setOvers(5);
        Inning inning2 = new Inning();
        inning2.setOvers(5);
        MatchOutcome outcome = new MatchOutcome();
        outcome.setFirstInning(inning1);
        StartMatch match = new StartMatch(team1, team2);
        match.tossWinner(inning1);
        match.play(inning1, outcome);
        inning2.setBatting(inning1.getBowling());
        inning2.setBowling(inning1.getBatting());
        outcome.setSecondInning(inning2);
        match.play(inning2, outcome);
        outcome.setWinner(inning1.getRuns() > inning2.getRuns() ? inning1.getBatting().getName() : inning2.getBatting().getName());

        System.out.println();
        System.out.println("Match b/w " + outcome.getFirstInning().getBatting().getName() + "(" + outcome.getFirstInning().getRuns() + ")"
                + " and " + outcome.getFirstInning().getBowling().getName() + "(" + outcome.getSecondInning().getRuns() + ")");

        System.out.println(outcome.getWinner() + " Won the match");
        System.out.println();

        System.out.println("------------- Score Card --------------------");
        List<OverOutcome> scoreCard = outcome.getOverOutcomes();
        for (OverOutcome overOutcome : scoreCard) {
            for (BowlOutcome bowl : overOutcome.getBowlOutcomes()) {
                System.out.println(bowl.getBowl().getPlayer().getName() + " bowls to "
                        + bowl.getShot().getPlayer().getName() + " a " + bowl.getBowl().getBowl().toString()
                        + " it's a " + bowl.getShot().getShot().toString());
            }
        }
        System.gc();
    }
}