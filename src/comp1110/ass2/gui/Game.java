package comp1110.ass2.gui;

import comp1110.ass2.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static comp1110.ass2.Cat.addCats;
import static comp1110.ass2.FireTile.addFire;
import static comp1110.ass2.Raft.addRaft;

public class Game extends Application {

    private final Group root = new Group();
    private static final int WINDOW_WIDTH = 1100;
    private static final int WINDOW_HEIGHT = 650;

    private final Group controls = new Group();

    private final String initialChallenge = "0";

    // FIXME TASK 11 Basic game
    // FIXME TASK 13 Fully working game

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(this.root, WINDOW_WIDTH, WINDOW_HEIGHT);
        makeControls();
        root.getChildren().add(controls);
        String boardState = setupChallengeAndReturnBoardState(initialChallenge);
        displayState(boardState);
        makeControls();
        stage.setScene(scene);
        stage.show();
    }


    void makeControls() {
        // Create a ComboBox (dropdown menu)
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("0","1", "2", "3", "4", "5");

//        comboBox.setPromptText("Select the Difficulty Level");
        comboBox.setValue(initialChallenge);

        Label selectedOptionLabel = new Label();

        // Event handler to the ComboBox
        comboBox.setOnAction(event -> {
            // Get the selected item
            String selectedItem = comboBox.getSelectionModel().getSelectedItem();
            selectedOptionLabel.setText("Difficulty Level Selected: " + selectedItem);

            String boardState = setupChallengeAndReturnBoardState(selectedItem);

            root.getChildren().clear();
            root.getChildren().add(controls);

            displayState(boardState);

        });

        // Create a VBox to hold the ComboBox and Label
        VBox vbox = new VBox(comboBox, selectedOptionLabel);

        // Set spacing for VBox
        vbox.setSpacing(10);
        controls.getChildren().add(vbox);

    }

    void displayState(String boardstate) {

        //removing spaces in boardstate string
        boardstate = boardstate.replaceAll(" ","");

        TheBoard theBoard = new TheBoard(boardstate);

        GridPane boardGridPane = new GridPane();
        boardGridPane.setHgap(2);
        boardGridPane.setVgap(2);


        //For board
        for (int row = 0; row < theBoard.getRows(); row++) {

            for (int col = 0; col < theBoard.getColumns(); col++) {

                if(theBoard.hasCat(row,col)){

                    String imagePath = addSquareWithCatByColour(theBoard, row, col);
                    Image image = new Image(imagePath);

                    // Create an ImageView to display the image
                    ImageView imageView = new ImageView(image);

                    imageView.setFitHeight(35);
                    imageView.setFitWidth(35);

                    boardGridPane.add(imageView, col, row);

                }else{

                    String imagePath = addSquareImageByColour(theBoard, row, col);
                    Image image = new Image(imagePath);

                    // Create an ImageView to display the image
                    ImageView imageView = new ImageView(image);

                    imageView.setFitHeight(35);
                    imageView.setFitWidth(35);

                    boardGridPane.add(imageView, col, row);

                }
            }
        }

        boardGridPane.setLayoutX(400);
        boardGridPane.setLayoutY(20);

        root.getChildren().add(boardGridPane);

    }

    String addSquareWithCatByColour(TheBoard theBoard, int row, int col){

        String imagePath = "file:src/comp1110/ass2/gui/assets/";

        if (theBoard.getColor(row,col) == Colour.FIRE) {
            return imagePath + "fire.png";
        } else if (theBoard.getColor(row,col) == Colour.BLUE) {
            return imagePath + "blueCat.png";
        } else if (theBoard.getColor(row,col) == Colour.RED) {
            return imagePath + "redCat.png";
        } else if (theBoard.getColor(row,col) == Colour.YELLOW) {
            return imagePath + "yellowCat.png";
        } else if (theBoard.getColor(row,col) == Colour.PURPLE) {
            return imagePath + "purpleCat.png";
        }else if (theBoard.getColor(row,col) == Colour.GREEN) {
            return imagePath + "greenCat.png";
        }else if (theBoard.getColor(row,col) == Colour.OBJECTIVE) {
            return imagePath + "objective.png";
        }
        //TODO: Aditya, check
//        else if (theBoard.getColor(row,col) == Colour.WILD) {
//            return Color.BROWN;
//        }
        else{
            return imagePath + "blue.png";
        }
    }


    String addSquareImageByColour(TheBoard theBoard, int row, int col){

        String imagePath = "file:src/comp1110/ass2/gui/assets/";

        if (theBoard.getColor(row,col) == Colour.FIRE) {
            return imagePath + "fire.png";
        } else if (theBoard.getColor(row,col) == Colour.BLUE) {
            return imagePath + "blue.png";
        } else if (theBoard.getColor(row,col) == Colour.RED) {
            return imagePath + "red.png";
        } else if (theBoard.getColor(row,col) == Colour.YELLOW) {
            return imagePath + "yellow.png";
        } else if (theBoard.getColor(row,col) == Colour.PURPLE) {
            return imagePath + "purple.png";
        }else if (theBoard.getColor(row,col) == Colour.GREEN) {
            return imagePath + "green.png";
        }else if (theBoard.getColor(row,col) == Colour.OBJECTIVE) {
            return imagePath + "objective.png";
        }
        //TODO: Aditya, check
//        else if (theBoard.getColor(row,col) == Colour.WILD) {
//            return Color.BROWN;
//        }
        else{
            return imagePath + "blue.png";
        }
    }

    String setupChallengeAndReturnBoardState(String difficulty){

        Challenge challenge = new Challenge(Integer.parseInt(difficulty));

        String boardStateFromChallenge = setBoardStateFromSelectedChallenge(challenge.getChallenge());
        System.out.println("Namaste Mummy and Papa!! " + boardStateFromChallenge);

        return boardStateFromChallenge;

    }



    public String setBoardStateFromSelectedChallenge(String challengeString) {

        String islandSubstring = challengeString.substring(0, challengeString.indexOf('F'));
        String fireSubstring = challengeString.substring(challengeString.indexOf('F') + 1, challengeString.indexOf('C'));
        String catSubstring = challengeString.substring(challengeString.indexOf('C') + 1, challengeString.indexOf('R'));
        String raftSubstring = challengeString.substring(challengeString.indexOf('R') + 1);

        TheBoard theBoard = new TheBoard();
        Square[][] board = theBoard.formBoard(islandSubstring);
        theBoard.setSquares(board);

        addFire(board, fireSubstring);

        addCats(board, catSubstring);

        addRaft(board, raftSubstring);

        return theBoard.boardToString();

    }


}
