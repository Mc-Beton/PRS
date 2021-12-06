package com.kodilla.prs.hand;

public class Paper implements Hand {

    public HandShape getShape() {
        return HandShape.PAPER;
    }

    public String toString() {
        return "Paper";
    }
}
