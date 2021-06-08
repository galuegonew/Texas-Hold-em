package user;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Profile {
    /*
     * 
     * Mostly statistics for player file i/o, also has game chips, handsplayed,
     * handswon BIG POINTS: uses file io for stats, do we want it for username as
     * well?
     * 
     */
    private int chips;
    private String UserName;
    private int CareerChips;
    private int CareerHands;
    private int CareerWonHands;
    private int gameChips = 10000;
    private int gameHands;
    private int gameWonHands;
    private static ArrayList<Integer> CareerStats = new ArrayList<Integer>();

    public int getGameChips() {
        return gameChips;
    }

    public void updateTurn(int change, int won) {
        gameChips = gameChips + change;
        gameHands++;
        gameWonHands = gameWonHands + won;
    }

    public int getGameHands() {
        return gameHands;
    }

    public int getCareerChips() {
        return CareerChips;
    }

    public int getCareerHands() {
        return CareerHands;
    }

    public int getCareerHandsWon() {
        return CareerWonHands;
    }

    public int getGameWonHands() {
        return gameWonHands;
    }

    public ArrayList<Integer> getCareerStats() {
        return CareerStats;
    };

    public Profile() {
        File playerStats = new File("stats/PlayerStats.txt");

        if (playerStats.exists()) {
            try {
                Scanner in = new Scanner(playerStats);
                String input = in.nextLine();
                System.out.println(input);
                int firstVal = input.indexOf(",");
                CareerChips = Integer.valueOf(input.substring(0, input.indexOf(",")));
                CareerHands = Integer.valueOf(input.substring(firstVal + 1, input.indexOf(",", firstVal + 1)));
                int secondVal = input.indexOf(",", firstVal + 1);
                CareerWonHands = Integer.valueOf(input.substring(secondVal + 1, input.indexOf(",", secondVal + 1)));
                System.out.println(CareerChips);
                System.out.println(CareerHands);
                System.out.println(CareerWonHands);
                CareerStats.add(CareerChips);
                CareerStats.add(CareerHands);
                CareerStats.add(CareerWonHands);
                in.close();
            } catch (Exception ex) {
                System.out.print("");
            }
        } else {
            try {
                playerStats.createNewFile();
                PrintWriter p = new PrintWriter(playerStats);
                p.println("0," + "0," + "0,");
                CareerChips = 0;
                CareerHands = 0;
                CareerWonHands = 0;
                CareerStats.add(CareerChips);
                CareerStats.add(CareerHands);
                CareerStats.add(CareerWonHands);
                p.close();
            } catch (Exception e) {
                System.out.println("Exception FILE IO error 2");
            }
        }
    }

    public void updateCareerStats(int a, int b, int c) {
        CareerChips = CareerChips + a;
        CareerHands = CareerHands + b;
        CareerWonHands = CareerWonHands + c;
        CareerStats.set(0, CareerChips);
        CareerStats.set(1, CareerHands);
        CareerStats.set(2, CareerWonHands);
        writeStats();
    }

    private void writeStats() {
        try {
            PrintWriter p = new PrintWriter("PlayerStats.txt");
            p.print(CareerChips + "," + CareerHands + "," + CareerWonHands + ",");
            p.close();
        } catch (Exception e) {
            System.out.println("writeStats unsuccessful");
        }
    }

    public ArrayList<Integer> getStats() {
        return CareerStats;
    }

    // Test function incase of errors
    public static void main(String[] args) {

        Profile p = new Profile();
        p.updateCareerStats(11100, 11, 21);
    }
}