package com.kodilla.prs;

public class Scissors implements Hand {
    public HandShape getShape() {
        return HandShape.SCISSORS;
    }

    public String toString() {
        return "Scissors";
    }
}
