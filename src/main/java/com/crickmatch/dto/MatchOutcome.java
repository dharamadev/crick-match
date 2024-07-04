package com.crickmatch.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class MatchOutcome {
    private Inning firstInning;
    private Inning secondInning;
    private List<OverOutcome> overOutcomes;
    private String winner;
}
