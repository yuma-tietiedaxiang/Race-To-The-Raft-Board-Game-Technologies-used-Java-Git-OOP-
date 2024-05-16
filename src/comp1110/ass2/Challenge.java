package comp1110.ass2;

import java.util.Random;

import static comp1110.ass2.Cat.addCats;
import static comp1110.ass2.FireTile.addFire;
import static comp1110.ass2.Raft.addRaft;

// author: Weiqi Huang

public class Challenge {
    private final int difficulty;
    private final String challenge;

    public Challenge(int difficulty){
        this.difficulty = difficulty;
        this.challenge = setChallenge();

    }

    public String setChallenge() {
        assert difficulty >= 0 && difficulty <= 5;
        int max =0;
        int min =0;
        if(difficulty== 0){
            max =3;
        }
        else if (difficulty==1) {
            max =7;
            min =4;
        }
        else if (difficulty==2) {
            max =15;
            min =8;
        }
        else if (difficulty==3) {
            max =23;
            min =16;
        }
        else if (difficulty==4) {
            max =31;
            min =24;
        }
        else {
            max =38;
            min =32;
        }

        Random random = new Random();
        int num= random.nextInt((max - min) + 1) + min;
//        System.out.println(num);
        return Utility.CHALLENGES[num];

    }

    public static String initialiseChallenge(String challengeString) {
//        String challengeString = "LNSNLASA F000300060012001503030903 C112033060340009 R01215";
        // find substrings for different parts
        String islandSubstring = challengeString.substring(0, challengeString.indexOf('F'));
        String fireSubstring = challengeString.substring(challengeString.indexOf('F') + 1, challengeString.indexOf('C'));
        String catSubstring = challengeString.substring(challengeString.indexOf('C') + 1, challengeString.indexOf('R'));
        String raftSubstring = challengeString.substring(challengeString.indexOf('R') + 1);

        TheBoard theBoard = new TheBoard();

        Square[][] board = theBoard.formBoard(islandSubstring);
        theBoard.setSquares(board);
//        System.out.println("检查raceToTheRaft新建板子"+'\n'+theBoard.boardToString());

        addFire(board, fireSubstring);

        addCats(board, catSubstring);

        addRaft(board, raftSubstring);

        System.out.println("Namaste Mummy and Papa!! " + theBoard.boardToString());

        return theBoard.boardToString();

    }

    public String getChallenge(){
        return challenge;
    }
    //    //get form the 4 island boards
//    int maxRow;
//    int maxColumn;
//    Location catLocation;
//    Location raftLocation;
//
//    Square[][] fireArea = new Square[maxRow][maxColumn];// has locations, colour 'f'

//    public boolean isLose(){
//        return false;
//    }// loosing conditions


}
