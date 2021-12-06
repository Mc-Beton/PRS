package com.kodilla.prs;

import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.Scanner;

public class PRS {

    public static void main(String[] args) throws java.lang.Exception {

        System.out.println("Welcome to this magical game of Paper, rock, and scissors!");
        System.out.println("All you need to know how to start: \n" +
                "n - start new game \n" +
                "x - finish actual game \n" +
                "1 - choose Rock \n" +
                "2 - choose Paper \n" +
                "3 - choose Scissors");
        try {
            whatToDo();
        } catch ( WrongKeyException e) {
            System.out.println("Wrong Key, try again");
        }
    }

    private static void whatToDo() throws WrongKeyException {
        System.out.println("What would you like to do?");
        String choice = (new Scanner(System.in)).next();
        if (choice.equals("n")) {
            newGameCommand();
        } else if (choice.equals("x")) {
            exitGameCommand();
        }
        else whatToDo();
    }

    private static void exitGameCommand() throws WrongKeyException {
        System.out.println("Are you sure you want to leave the game? y/n");
        String choice = (new Scanner(System.in)).next();
        if (choice.equals("y")) {
            System.exit(0);
        } else {
            whatToDo();
        }
    }

    private static void newGameCommand() throws WrongKeyException {
        System.out.println("Are you sure you want to start a new game? y/n");
        String choice = (new Scanner(System.in)).next();
        if (choice.equals("y")) {
            startAGame();
        } else {
            whatToDo();
        }
    }

    private static void startAGame() throws WrongKeyException {
        System.out.println("Please type in how many wins shall determine the winner of this battle: ");
        Scanner rounds = new Scanner(System.in);
        int r = rounds.nextInt();
        System.out.println("Well then, let the battle begin till someone wins " + r + " rounds!");
        int comWin = 0;
        int plaWin = 0;

        while (comWin != r || plaWin != r) {
            System.out.println("Choose your shape");
            Scanner pick = new Scanner(System.in);
            Hand plaShape = getPlayerShape(pick);
            Hand comShape = getComputerShape();
            System.out.println(plaShape + "  :  " + comShape);
            if (plaShape.getShape().equals(comShape.getShape())) {
                System.out.println("It's a tie!");
                System.out.println(comWin + "  :  " + plaWin);
            } else if (plaShape.getShape().equals(HandShape.ROCK)) {
                System.out.println(comShape.getShape().equals(HandShape.PAPER) ? "Computer Scores!" : "Player Scores!");
                System.out.println(comShape.getShape().equals(HandShape.PAPER) ? comWin++ + " : " + plaWin : comWin + " : " + plaWin++);
            } else if (plaShape.getShape().equals(HandShape.PAPER)) {
                System.out.println(comShape.getShape().equals(HandShape.SCISSORS) ? "Computer Scores!" : "Player Scores!");
                System.out.println(comShape.getShape().equals(HandShape.SCISSORS) ? comWin++ + " : " + plaWin : comWin + " : " + plaWin++);
            } else if (plaShape.getShape().equals(HandShape.EMPTY)) {
                System.out.println("Try again");
            } else {
                System.out.println(comShape.getShape().equals(HandShape.ROCK) ? "Computer Scores!" : "Player Scores!");
                System.out.println(comShape.getShape().equals(HandShape.ROCK) ? comWin++ + " : " + plaWin : comWin + " : " + plaWin++);
            }
            System.out.println(" ");
        }
        System.out.println(plaWin == r ? "Player wins the game!" : "Computer wins the game!");
        whatToDo();
    }

    private static Hand getComputerShape() {
        Hand comShape = new EmptyHand();
        Random random = new Random();
        int input = random.nextInt(3);
        if (input == 0) {
            comShape = new Rock();
        } else if (input == 1) {
            comShape = new Paper();
        } else if (input == 2) {
            comShape = new Scissors();
        }
        return comShape;
    }

    private static Hand getPlayerShape(@NotNull Scanner pick) throws WrongKeyException {
        Hand plaShape = new EmptyHand();
        if (pick.hasNextInt()) {
            int number = pick.nextInt();
            if (number == 1) {
                plaShape = new Rock();
            } else if (number == 2) {
                plaShape = new Paper();
            } else if (number == 3) {
                plaShape = new Scissors();
            }

        } else {
                if (pick.next().equals("n")) {
                    newGameCommand();
                } else if (pick.next().equals("x")) {
                    exitGameCommand();
                }
            }
        return plaShape;
    }
}
