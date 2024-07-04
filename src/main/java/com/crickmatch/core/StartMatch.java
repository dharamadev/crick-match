package com.crickmatch.core;

import com.crickmatch.dto.*;
import com.crickmatch.dto.constant.BowlType;
import com.crickmatch.dto.constant.PlayerType;
import com.crickmatch.dto.constant.ShotType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StartMatch {

    private final Team firstTeam;
    private final Team secondTeam;
    private final Random random;

    public StartMatch(Team firstTeam, Team secondTeam) {
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.random = new Random();
    }

    public void play(Inning inning, MatchOutcome outcome) {

        Team battingTeam = inning.getBatting();
        Team bowlingTeam = inning.getBowling();
        inning.setRuns(0);
        outcome.setOverOutcomes(new ArrayList<>());

        int overs = inning.getOvers();
        List<Player> battingTeamPlayers = battingTeam.getPlayers();
        List<Player> bowlingTeamPlayers = bowlingTeam.getPlayers();
        SelectPlayer bowlerSelector = new SelectPlayer(bowlingTeam);
        SelectPlayer batsmanSelector = new SelectPlayer(battingTeam);

        List<Player> batsmanList = battingTeamPlayers.stream()
                .filter(player -> player.getPlayerType().equals(PlayerType.BATSMAN) || player.getPlayerType().equals(PlayerType.ALL_ROUNDER)).toList();
        List<Player> bowlerList = bowlingTeamPlayers.stream()
                .filter(player -> player.getPlayerType().equals(PlayerType.BATSMAN) || player.getPlayerType().equals(PlayerType.ALL_ROUNDER)).toList();

        Player batsman = batsmanSelector.batsMan(batsmanList.size());
        Player bowler = bowlerSelector.bowler(11 - bowlerList.size());

        int batsmanNumber = 0;
        int bowlerNumber = 0;
        List<BowlOutcome> overBowlOutcome = new ArrayList<>();
        List<OverOutcome> overOutcomes = new ArrayList<>();
        while (overs > 0) {
            OverOutcome overOutcome = new OverOutcome(1, new ArrayList<>());
            overOutcome.setBowlOutcomes(new ArrayList<>());
            for (int bowls = 1; bowls <= 6; bowls++) {
                StartBatting batting = new StartBatting(batsman);
                StartBowling bowling = new StartBowling(bowler);
                Bowl bowl = bowling.play();
                BowlOutcome bowlOutcome = new BowlOutcome();
                if (bowl.getBowl().equals(BowlType.WIDE)) {
                    bowlOutcome.setBowl(new Bowl(bowler, BowlType.WIDE));
                    bowlOutcome.setShot(new Shot(batsman, ShotType.MISS));
                    inning.setRuns(inning.getRuns() + 1);
                    bowls -= 1;
                } else {
                    Shot shot = batting.play();
                    switch (shot.getShot()) {
                        case ShotType.OUT -> {
                            bowlOutcome.setShot(new Shot(batsman, ShotType.OUT));
                            bowlOutcome.setBowl(new Bowl(bowler, bowl.getBowl()));
                            if (batsmanNumber < batsmanList.size() - 1) {
                                batsman = battingTeamPlayers.get(++batsmanNumber);
                            }
                        }
                        case ShotType.FOUR -> {
                            bowlOutcome.setShot(new Shot(batsman, ShotType.FOUR));
                            bowlOutcome.setBowl(new Bowl(bowler, bowl.getBowl()));
                            inning.setRuns(inning.getRuns() + 4);
                        }

                        case ShotType.SIX -> {
                            bowlOutcome.setShot(new Shot(batsman, ShotType.SIX));
                            bowlOutcome.setBowl(new Bowl(bowler, bowl.getBowl()));
                            inning.setRuns(inning.getRuns() + 6);
                        }

                        case ShotType.RUN -> {
                            bowlOutcome.setShot(new Shot(batsman, ShotType.RUN));
                            bowlOutcome.setBowl(new Bowl(bowler, bowl.getBowl()));
                            Integer run = random.nextInt(0, 7);
                            inning.setRuns(inning.getRuns() + run);
                        }
                        default -> {
                            bowlOutcome.setShot(new Shot(batsman, ShotType.MISS));
                            bowlOutcome.setBowl(new Bowl(bowler, bowl.getBowl()));
                        }
                    }
                }
                overBowlOutcome.add(bowlOutcome);
            }
            overOutcomes.add(new OverOutcome((6 - overs), overBowlOutcome));
            if (bowlerNumber >= bowlerList.size()) {
                bowler = bowlerList.getFirst();
            } else {
                bowler = bowlerList.get(++bowlerNumber);
            }
            overs -= 1;
        }
        outcome.getOverOutcomes().addAll(overOutcomes);
    }

    public void tossWinner(Inning inning) {
        int toss = random.nextInt(1, 3);
        if (toss == 1) {
            inning.setBatting(firstTeam);
            inning.setBowling(secondTeam);
        } else {
            inning.setBatting(secondTeam);
            inning.setBowling(firstTeam);
        }
    }
}
