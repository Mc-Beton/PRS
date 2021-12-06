package com.kodilla.prs.hand;

public class Rock implements Hand {
    public HandShape getShape() {
        return HandShape.ROCK;
    }

    public String toString() {
        return "Rock";
    }
}
