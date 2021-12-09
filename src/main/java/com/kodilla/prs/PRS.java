package com.kodilla.prs;

import com.kodilla.prs.hand.*;
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
        } catch (WrongKeyException e) {
            System.out.println("Wrong value, please try again");
            whatToDo();
        }
    }

    private static void whatToDo() throws WrongKeyException {
        System.out.println("What would you like to do?");
        String choice = (new Scanner(System.in)).next();
        if (choice.equals("n") || choice.equals("x")) {
            if (choice.equals("n")) {
                newGameCommand();
            } else if (choice.equals("x")) {
                exitGameCommand();
            }
        } else {
            throw new WrongKeyException();
        }
    }

    private static void exitGameCommand() throws WrongKeyException {
        System.out.println("Are you sure you want to leave the game? y/n");
        String choice = (new Scanner(System.in)).next();
        if (choice.equals("y") || choice.equals("n")) {
            if (choice.equals("y")) {
                System.exit(0);
            } else {
                whatToDo();
            }
        } else {
            throw new WrongKeyException();
        }
    }

    private static void newGameCommand() throws WrongKeyException {
        System.out.println("Are you sure you want to start a new game? y/n");
        String choice = (new Scanner(System.in)).next();
        if (choice.equals("y") || choice.equals("n")) {
            if (choice.equals("y")) {
                startAGame();
            } else {
                whatToDo();
            }
        } else {
            throw new WrongKeyException();
        }
    }

    private static void startAGame() throws WrongKeyException {
        System.out.println("Please type in how many wins shall determine the winner of this battle: ");
        Scanner rounds = new Scanner(System.in);
        if (rounds.hasNextInt()) {
            int r = rounds.nextInt();
            System.out.println("Well then, let the battle begin till someone wins " + r + " rounds!");
            int comWin = 0;
            int plaWin = 0;

            while (comWin < r && plaWin < r) {
                System.out.println("Choose your shape" + comWin + plaWin + r);
                Scanner pick = new Scanner(System.in);
                Hand plaShape = getPlayerShape(pick);
                Hand comShape = getComputerShape();
                System.out.println(plaShape + "  :  " + comShape);
                Winner winner = getWinner(plaShape, comShape);
                if (winner == Winner.HUMAN)
                    plaWin++;
                else if (winner == Winner.COMPUTER)
                    comWin++;
                else {
                    plaWin++;
                    comWin++;
                }
                displayPartialResult(winner, plaWin, comWin);
                System.out.println(" ");
            }
            System.out.println(plaWin == r ? "Player wins the game!" : "Computer wins the game!");
            whatToDo();
        } else {
            throw new WrongKeyException();
        }
    }

    private static void displayPartialResult(Winner winner, int plaWin, int comWin) {
        printOutTheResult(winner);
        System.out.println("Computer:Player");
        System.out.println(comWin + "     :     " + plaWin);
    }

    private static void printOutTheResult(Winner winner) {
        if (winner != Winner.DRAW) {
            System.out.println(winner.equals(Winner.HUMAN) ? "Player won this round" : "Computer won this round");
        } else {
            System.out.println("It's a draw");
        }
    }

    private static Winner getWinner(Hand plaShape, Hand comShape) {
        Winner win = Winner.DRAW;
        if (plaShape.getShape().equals(comShape.getShape())) {
            return win;
        } else if (plaShape.getShape().equals(HandShape.ROCK)) {
            win = comShape.getShape().equals(HandShape.PAPER) ? Winner.COMPUTER : Winner.HUMAN;
        } else if (plaShape.getShape().equals(HandShape.PAPER)) {
            win = comShape.getShape().equals(HandShape.SCISSORS) ? Winner.COMPUTER : Winner.HUMAN;
        } else {
            win = comShape.getShape().equals(HandShape.ROCK) ? Winner.COMPUTER : Winner.HUMAN;
        }
        return win;
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
            String letter = pick.next();
            if (letter.equals("n")) {
                newGameCommand();
            } else if (letter.equals("x")) {
                exitGameCommand();
            }
        }
        return plaShape;
    }
}
