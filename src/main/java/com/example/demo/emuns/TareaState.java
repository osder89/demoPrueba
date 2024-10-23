package com.example.demo.emuns;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum TareaState {
    PENDIENTE("PENDIENTE"),
    FINALIZADA("FINALIZADA");
    private final String state;

    TareaState(String state) {
        this.state = state;
    }
}
