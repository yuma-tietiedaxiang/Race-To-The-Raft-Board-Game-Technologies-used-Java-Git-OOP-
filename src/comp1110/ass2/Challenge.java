package comp1110.ass2;

import java.util.Random;

// Weiqi Huang

public class Challenge {
    private int difficulty;
    private String challenge;

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
            min =0;
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
        else if (difficulty==5) {
            max =38;
            min =32;
        }

        Random random = new Random();
        int num= random.nextInt((max - min) + 1) + min;
//        System.out.println(num);
        return Utility.CHALLENGES[num];

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
