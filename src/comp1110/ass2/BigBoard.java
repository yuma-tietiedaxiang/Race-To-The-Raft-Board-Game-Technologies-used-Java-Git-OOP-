package comp1110.ass2;
import comp1110.ass2.Square;
import comp1110.ass2.Colour;
import comp1110.ass2.Location;

// Yu Ma
public class BigBoard {
    /*
    this is the big board formed by 4 island board.
    It records states of current game
     */

    int maxRow;//should be determined by challenge
    int maxColumn;//should be determined by challenge
    Location[][] location = new Location[maxRow][maxColumn];
    Square[][] squares = new Square[maxRow][maxColumn];//2D

    //square shape island
    IslandBoard s11 = new IslandBoard("fffgygbyrfffgygpbyfffrrbrgpfffbgypbrfffpbrpgyfffyrygypfffgbbrpbfffpggbygfffpypgrr",1);
    IslandBoard s12 = new IslandBoard("gbygygbyrbrpgygpbyygbrrbrgpbypbgypbrgprpbrpgyrbgyrygypybygbbrpbygrpggbygbbypypgrr",2);
    IslandBoard s21 = new IslandBoard("fffyggybpfffpyyrgyfffrgbpgbfffbrgyrpfffppbgrbfffbyrpbyfffbygbrpfffprgrygfffybyppy",3);
    IslandBoard s22 = new IslandBoard("gbbyggybprbgpyyrgyygyrgbpgbbprbrgyrpgbrppbgrbpygbyrpbygbybygbrppygprgryggbrybyppy",4);
    IslandBoard s31 = new IslandBoard("fffpybygrfffrpgbybfffbrbgyrfffgpypbgfffrbgpryfffpyrygbfffrggbpgfffpbyrybfffgybrgy",5);
    IslandBoard s32 = new IslandBoard("grgpybygrgyprpgbybbrbbrbgyrgypgpypbgpbrrbgpryygppyrygbbpyrggbpgbrypbyrybgybgybrgy",6);
    IslandBoard s41 = new IslandBoard("fffpgyyrbfffpbbrypfffbgypgbfffyybyrgfffbgrggyfffgpgbgpfffbgybpbfffpyrgygfffgppbrr",7);
    IslandBoard s42 = new IslandBoard("rrbpgyyrbygypbbrypbprbgypgbrgbyybyrgpypbgrggyygygpgbgpbprbgybpbrybpyrgygbgygppbrr",8);

    //rectangle shape island
    IslandBoard r11 = new IslandBoard("fffbrgprgfffrbpybyfffpgybrrfffbybgypfffbrgyggfffybpbry",9);
    IslandBoard r12 = new IslandBoard("yypbrgprgbpgrbpybygbypgybrrgrgbybgyprypbrgyggbpgybpbry",10);
    IslandBoard r21 = new IslandBoard("fffgrybbrfffybgrypfffbpbyggfffyprypyfffrygpbbfffbpygyr",11);
    IslandBoard r22 = new IslandBoard("bbggrybbrgrpybgryppgrbpbygggpbyprypybygrygpbbgrybpygyr",12);
    IslandBoard r31 = new IslandBoard("fffygybgyfffgyrpbgfffpbyyrpfffgbrggrfffpgbypbfffgrpryb",13);
    IslandBoard r32 = new IslandBoard("brpygybgygrbgyrpbggyppbyyrpybygbrggrgyrpgbypbpbbgrpryb",14);
    IslandBoard r41 = new IslandBoard("fffbgpgpbfffyrgbyrfffgybpgyfffbrrbgbfffpygybpfffgpbyry",15);
    IslandBoard r42 = new IslandBoard("gyybgpgpbprbyrgbyryprgybpgyggybrrbgbbrgpygybpbprgpbyry",16);


    public BigBoard(int maxRow, int maxColumn){
        //initialization from challenge
    }

    public void changeState(Location location,Colour colour, boolean hasCat, boolean hasFire){
//        squares[location.getRow()][location.getColumn()].colour = colour;
    }


}
