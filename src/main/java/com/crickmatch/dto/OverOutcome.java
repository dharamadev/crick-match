package com.crickmatch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OverOutcome {
    private Integer over;
    private List<BowlOutcome> bowlOutcomes;
}
