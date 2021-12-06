package com.kodilla.prs;

public class EmptyHand implements Hand {

    public HandShape getShape() {
        return HandShape.EMPTY;
    }

    public String toString() {
        return "Empty";
    }
}
