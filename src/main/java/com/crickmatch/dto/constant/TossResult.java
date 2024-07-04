package com.crickmatch.dto.constant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TossResult {
    HEAD(1), TAIL(2);
    public final Integer toss;
}
