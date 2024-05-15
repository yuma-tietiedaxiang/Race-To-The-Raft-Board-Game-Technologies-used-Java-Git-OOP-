package comp1110.ass2.gui;

import comp1110.ass2.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static comp1110.ass2.Cat.addCats;
import static comp1110.ass2.FireTile.addFire;
import static comp1110.ass2.Raft.addRaft;

public class Game extends Application {

    private final Group root = new Group();
    private static final int WINDOW_WIDTH = 1100;
    private static final int WINDOW_HEIGHT = 650;

    private static final int MARGIN_X = 20;
    private static final int MARGIN_Y = 20;

    private final Group controls = new Group();

    private final String initialChallenge = "0";
    private final int squareSideSize = 35;

    private final List<String> deckAList = new ArrayList<>(List.of(Utility.DECK_A));
    private final List<String> deckBList = new ArrayList<>(List.of(Utility.DECK_B));
    private final List<String> deckCList = new ArrayList<>(List.of(Utility.DECK_C));
    private final List<String> deckDList = new ArrayList<>(List.of(Utility.DECK_D));

    private GridPane cardDisplayGrid;
    private int cardCount = 0;

    private Label deckALabel;
    private Label deckBLabel;
    private Label deckCLabel;
    private Label deckDLabel;

    // FIXME TASK 11 Basic game
    // FIXME TASK 13 Fully working game

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(this.root, WINDOW_WIDTH, WINDOW_HEIGHT);
//        makeControls();
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
        selectedOptionLabel.setText("Difficulty Level Selected: " + initialChallenge);

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


        GridPane decksGridPane = new GridPane();
        cardDisplayGrid = new GridPane(); // Grid to display cards

        decksGridPane.setHgap(10);
        decksGridPane.setVgap(10);

        setupButtonGrid(decksGridPane);


        // Create a VBox to hold the ComboBox and Label
        VBox vbox = new VBox(comboBox, selectedOptionLabel, decksGridPane, cardDisplayGrid);

        // Set spacing for VBox
        vbox.setSpacing(10);
        vbox.setLayoutX(MARGIN_X);
        vbox.setLayoutY(MARGIN_Y);

        controls.getChildren().add(vbox);

    }

    private void setupButtonGrid(GridPane gridPane) {
        Button deckAButton = new Button("Deck A");
        Button deckBButton = new Button("Deck B");
        Button deckCButton = new Button("Deck C");
        Button deckDButton = new Button("Deck D");

        deckALabel = new Label("Cards left: " + deckAList.size());
        deckBLabel = new Label("Cards left: " + deckBList.size());
        deckCLabel = new Label("Cards left: " + deckCList.size());
        deckDLabel = new Label("Cards left: " + deckDList.size());

        deckAButton.setOnAction(event -> drawCard(deckAList, deckALabel, 'A'));
        deckBButton.setOnAction(event -> drawCard(deckBList, deckBLabel, 'B'));
        deckCButton.setOnAction(event -> drawCard(deckCList, deckCLabel, 'C'));
        deckDButton.setOnAction(event -> drawCard(deckDList, deckDLabel, 'D'));

        gridPane.add(deckAButton, 0, 0);
        gridPane.add(deckBButton, 1, 0);
        gridPane.add(deckCButton, 0, 1);
        gridPane.add(deckDButton, 1, 1);

        gridPane.add(deckALabel, 0, 2);
        gridPane.add(deckBLabel, 1, 2);
        gridPane.add(deckCLabel, 0, 3);
        gridPane.add(deckDLabel, 1, 3);
    }

    private void displayCard(String card) {
        GridPane cardGrid = new GridPane();
        for (int i = 0; i < 9; i++) {
            int row = i / 3;
            int col = i % 3;
//            Text text = new Text(String.valueOf(card.charAt(i)));
//            cardGrid.add(text, col, row);

            String imagePath = getSquareImagePathByCharacter(card.charAt(i));
            Image image = new Image(imagePath);

            // Create an ImageView to display the image
            ImageView imageView = new ImageView(image);

            imageView.setFitHeight(squareSideSize);
            imageView.setFitWidth(squareSideSize);

            cardGrid.add(imageView, col, row);

        }

        cardGrid.toFront();

        Draggable.Nature nature = new Draggable.Nature(cardGrid);

//        cardGrid.setOnMousePressed(this::handleMousePress);
//        cardGrid.setOnMouseDragged(this::handleMouseDrag);

//        setupGridDragHandlers(cardGrid);

        int rowPosition = cardCount / 3;
        int colPosition = cardCount % 3;

        cardDisplayGrid.setVgap(10);
        cardDisplayGrid.setHgap(10);

        cardDisplayGrid.add(cardGrid, colPosition, rowPosition);
        cardCount++;
    }

    //Not used, can be useful in future though
    private void handleMousePress(MouseEvent event) {
        GridPane grid = (GridPane) event.getSource();
        grid.toFront(); // Ensure the gridpane is at the front when selected
        grid.setCursor(javafx.scene.Cursor.MOVE);
        // Store initial positions
        grid.setUserData(new double[]{
                event.getSceneX(), // Initial mouse X
                event.getSceneY(), // Initial mouse Y
                grid.getLayoutX(), // Initial grid layout X
                grid.getLayoutY()  // Initial grid layout Y
        });
    }

    //Not used, can be useful in future though
    private void handleMouseDrag(MouseEvent event) {
        GridPane grid = (GridPane) event.getSource();
        double[] data = (double[]) grid.getUserData();
        // Calculate the new layout positions based on mouse movement
        double newLayoutX = data[2] + (event.getSceneX() - data[0]);
        double newLayoutY = data[3] + (event.getSceneY() - data[1]);
        // Update the layout positions
        grid.setLayoutX(newLayoutX);
        grid.setLayoutY(newLayoutY);
    }


    //Not used, can be useful in future though
    private void setupGridDragHandlers(GridPane cardGrid) {
        cardGrid.setOnDragDetected(event -> {
            Dragboard db = cardGrid.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(""); // You might not need to transfer actual data
            db.setContent(content);
            System.out.println("Namaste Mummy and Papa!!");
            event.consume();
        });

        cardGrid.setOnDragOver(event -> {
            if (event.getGestureSource() != cardGrid && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        cardGrid.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                // Perform the move operation here
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });

        cardGrid.setOnDragDone(event -> {
            if (event.getTransferMode() == TransferMode.MOVE) {
                // Additional cleanup if needed
            }
            event.consume();
        });
    }



    private void drawCard(List<String> deck, Label label, char deckName) {
        if (!deck.isEmpty()) {

            Collections.shuffle(deck);
            String card = deck.remove(deck.size() - 1);
            label.setText("Cards left in " + deckName + ": " + deck.size());
            displayCard(card.substring(1)); // Ignore the first character
            System.out.println("Drawn card: " + card);

        } else {
            System.out.println("No more cards left in the deck!");
        }
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

                    imageView.setFitHeight(squareSideSize);
                    imageView.setFitWidth(squareSideSize);

                    boardGridPane.add(imageView, col, row);

                }else{

                    String imagePath = addSquareImageByColour(theBoard, row, col);
                    Image image = new Image(imagePath);

                    // Create an ImageView to display the image
                    ImageView imageView = new ImageView(image);

                    imageView.setFitHeight(squareSideSize);
                    imageView.setFitWidth(squareSideSize);

                    boardGridPane.add(imageView, col, row);

                }
            }
        }

        boardGridPane.setLayoutX(400);
        boardGridPane.setLayoutY(MARGIN_Y);

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
        else if (theBoard.getColor(row,col) == Colour.WILD) {
            return imagePath + "objective.png";
        }
        else{
            return imagePath + "blue.png";
        }
    }


    String getSquareImagePathByCharacter(char c){
        Colour colour = Colour.fromChar(c);

        String imagePath = "file:src/comp1110/ass2/gui/assets/";

        if (colour == Colour.BLUE) {
            return imagePath + "blue.png";
        } else if (colour == Colour.RED) {
            return imagePath + "red.png";
        } else if (colour == Colour.YELLOW) {
            return imagePath + "yellow.png";
        } else if (colour == Colour.PURPLE) {
            return imagePath + "purple.png";
        } else if (colour == Colour.GREEN) {
            return imagePath + "green.png";
        } else if (colour == Colour.OBJECTIVE) {
            return imagePath + "objective.png";
        } else if (colour == Colour.WILD) {
            return imagePath + "objective.png";
        } else {
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
        else if (theBoard.getColor(row,col) == Colour.WILD) {
            return imagePath + "objective.png";
        }
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
