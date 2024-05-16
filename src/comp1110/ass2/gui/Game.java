package comp1110.ass2.gui;

import comp1110.ass2.*;
import javafx.application.Application;
import javafx.event.Event;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
    private GridPane selectedPathwayCard = null;
    private final GridPane firetileGridPane = new GridPane();

    private int cardCount = 0;
    private final int maxCardAllowed = 6;

    Draggable.Nature fireTileDraggableNature;

    private final List<String> firetilesRemainingInBag = new ArrayList<>(List.of(Utility.FIRE_TILES));

    // FIXME TASK 11 Basic game
    // FIXME TASK 13 Fully working game

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(this.root, WINDOW_WIDTH, WINDOW_HEIGHT);
//        makeControls();
        String boardState = setupChallengeAndReturnBoardState(initialChallenge);
        displayState(boardState);
        root.getChildren().add(controls);
        makeControls();
        stage.setScene(scene);
        stage.show();
    }


    void makeControls() {
        // Create a ComboBox (dropdown menu)
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("0", "1", "2", "3", "4", "5");

//        comboBox.setPromptText("Select the Difficulty Level");
        comboBox.setValue(initialChallenge);

        Label selectedOptionLabel = new Label();
        selectedOptionLabel.setText("Difficulty Level Selected: " + initialChallenge);

        HBox challengeHBox = new HBox(comboBox, selectedOptionLabel);
        challengeHBox.setSpacing(10);

        // Event handler to the ComboBox
        comboBox.setOnAction(event -> {
            // Get the selected item
            String selectedItem = comboBox.getSelectionModel().getSelectedItem();
            selectedOptionLabel.setText("Difficulty Level Selected: " + selectedItem);

            String boardState = setupChallengeAndReturnBoardState(selectedItem);

            root.getChildren().clear();

            displayState(boardState);

            root.getChildren().add(controls);

        });


        GridPane decksGridPane = new GridPane();
        cardDisplayGrid = new GridPane(); // Grid to display cards

        decksGridPane.setHgap(10);
        decksGridPane.setVgap(10);

        setupButtonGrid(decksGridPane);

        VBox vbox = getvBox(challengeHBox, decksGridPane);

        controls.getChildren().add(vbox);

    }

    private VBox getvBox(HBox challengeHorizontalBox, GridPane decksGridPane) {
        Button rotateCardClockwiseButton = new Button("Rotate Clockwise");
        Button rotateCardCounterClockwiseButton = new Button("Rotate Counterclockwise");

        Button drawFireTileButton = new Button("Draw Fire Tile");

        drawFireTileButton.setOnAction(e -> drawFireTile());

//        GridPane tileGrid = createTileGrid(firetileDrawn);

        drawFireTileButton.setOnAction(event -> drawFireTile());

        rotateCardClockwiseButton.setOnAction(e -> rotateSelectedCard(90));
        rotateCardCounterClockwiseButton.setOnAction(e -> rotateSelectedCard(-90));

        HBox cardRotationHBox = new HBox(rotateCardClockwiseButton, rotateCardCounterClockwiseButton);
        cardRotationHBox.setSpacing(10);


        //FireTile rotate Button
        HBox fireTileRotationHBox = getFireTileRotationHBox();


        // Create a VBox to hold the ComboBox and Label
        VBox vbox = new VBox(challengeHorizontalBox, decksGridPane, cardDisplayGrid, cardRotationHBox,
                drawFireTileButton, firetileGridPane, fireTileRotationHBox);

        // Set spacing for VBox
        vbox.setSpacing(20);
        vbox.setLayoutX(MARGIN_X);
        vbox.setLayoutY(MARGIN_Y);
        return vbox;
    }

    private HBox getFireTileRotationHBox() {
        Button rotateFireTileClockwiseButton = new Button("Rotate Clockwise");
        Button rotateFireTileCounterClockwiseButton = new Button("Rotate Counterclockwise");


        rotateFireTileClockwiseButton.setOnAction(e -> rotateFireTile(90));
        rotateFireTileCounterClockwiseButton.setOnAction(e -> rotateFireTile(-90));

        HBox fireTileRotationHBox = new HBox(rotateFireTileClockwiseButton, rotateFireTileCounterClockwiseButton);
        fireTileRotationHBox.setSpacing(10);
        return fireTileRotationHBox;
    }

    private GridPane createTileGrid(String tileString) {
        GridPane grid = new GridPane();
        // Parse the coordinates from the string, skip the first character
        for (int i = 1; i < tileString.length(); i += 2) {
            int row = tileString.charAt(i) - '0';     // Convert char to integer
            int col = tileString.charAt(i + 1) - '0'; // Convert char to integer

            String fireTileImagePath = "file:src/comp1110/ass2/gui/assets/fire.png";

            Image image = new Image(fireTileImagePath);

            // Create an ImageView to display the image
            ImageView imageView = new ImageView(image);

            imageView.setFitHeight(squareSideSize);
            imageView.setFitWidth(squareSideSize);

//            Rectangle rect = new Rectangle(20, 20);
//            rect.setFill(Color.GREEN);
            grid.add(imageView, col, row);
        }
        return grid;
    }

    private void drawFireTile() {
        if (!firetilesRemainingInBag.isEmpty()) {

            // Remove previous one, then add another one. Else it will speed up the drag for subsequent tiles
            if(fireTileDraggableNature != null && !fireTileDraggableNature.getDragNodes().isEmpty()) {
                fireTileDraggableNature.removeDraggedNode(firetileGridPane);
            }

            Random rand = new Random();
            String tileString = firetilesRemainingInBag.get(rand.nextInt(firetilesRemainingInBag.size()));
            firetileGridPane.getChildren().clear(); // Clear previous tile
            GridPane newTileGrid = createTileGrid(tileString);
            firetileGridPane.getChildren().add(newTileGrid); // Display new tile
            fireTileDraggableNature = new Draggable.Nature(firetileGridPane);
//            nature.removeDraggedNode(firetileGridPane);
        } else {
            System.out.println("No more fire tiles available.");
        }
    }


    private void setupButtonGrid(GridPane gridPane) {
        Button deckAButton = new Button("Deck A (" + deckAList.size() + ")");
        Button deckBButton = new Button("Deck B (" + deckAList.size() + ")");
        Button deckCButton = new Button("Deck C (" + deckAList.size() + ")");
        Button deckDButton = new Button("Deck D (" + deckAList.size() + ")");

        deckAButton.setOnAction(event -> drawCard(deckAButton, deckAList, 'A'));
        deckBButton.setOnAction(event -> drawCard(deckBButton, deckBList, 'B'));
        deckCButton.setOnAction(event -> drawCard(deckCButton, deckCList, 'C'));
        deckDButton.setOnAction(event -> drawCard(deckDButton, deckDList, 'D'));

        gridPane.add(deckAButton, 0, 0);
        gridPane.add(deckBButton, 1, 0);
        gridPane.add(deckCButton, 0, 1);
        gridPane.add(deckDButton, 1, 1);
    }

    private void displayCard(String card) {
        GridPane cardGrid = getCardGrid(card);


        cardGrid.setOnMouseClicked(this::handleCardSelection);
        cardGrid.setBorder(null); // To Ensure no border unless selected
        cardDisplayGrid.setVgap(10);
        cardDisplayGrid.setHgap(10);
        cardDisplayGrid.add(cardGrid, cardDisplayGrid.getChildren().size() % 3, cardDisplayGrid.getChildren().size() / 3);

//        cardGrid.setOnMousePressed(this::handleMousePress);
//        cardGrid.setOnMouseClicked(this::handleCardSelection);
//        cardGrid.setOnMouseDragged(this::handleMouseDrag);

//        setupGridDragHandlers(cardGrid);

        cardCount++;
    }

    private GridPane getCardGrid(String card) {
        GridPane cardGrid = new GridPane();
        for (int i = 0; i < 9; i++) {
            int row = i / 3;
            int col = i % 3;
//            Text text = new Text(String.valueOf(card.charAt(i)));
//            cardGrid.add(text, col, row);

            String imagePath = Colour.getSquareImagePathByCharacter(card.charAt(i));
            Image image = new Image(imagePath);

            // Create an ImageView to display the image
            ImageView imageView = new ImageView(image);

            imageView.setFitHeight(squareSideSize);
            imageView.setFitWidth(squareSideSize);

            cardGrid.add(imageView, col, row);

        }

        cardGrid.toFront();

        //Do not remove! Used for dragging
        Draggable.Nature nature = new Draggable.Nature(cardGrid);
        return cardGrid;
    }

    private void handleCardSelection(MouseEvent event) {
        if (selectedPathwayCard != null) {
            selectedPathwayCard.setStyle(""); // Clear style from previously selected card
        }
        selectedPathwayCard = (GridPane) event.getSource();
        selectedPathwayCard.setStyle("-fx-border-color: goldenrod; -fx-border-width: 2; -fx-border-style: solid;");
    }

    private void rotateSelectedCard(double angle) {
        if (selectedPathwayCard != null) {
            Rotate rotate = new Rotate(angle, selectedPathwayCard.getWidth() / 2, selectedPathwayCard.getHeight() / 2);
            selectedPathwayCard.getTransforms().add(rotate);
        }
    }

    private void rotateFireTile(double angle) {
        Rotate rotate = new Rotate(angle, firetileGridPane.getWidth() / 4, firetileGridPane.getHeight() / 4);
        firetileGridPane.getTransforms().add(rotate);
    }


    //TODO: Aditya, use object instead of string
    private void drawCard(Button deckButton, List<String> deck, char deckName) {
        if (!deck.isEmpty() && cardCount < maxCardAllowed) {
            Collections.shuffle(deck);
            String card = deck.remove(deck.size() - 1);
//            deckButton.setText("Cards left in " + deckName + ": " + deck.size());
            deckButton.setText("Deck " + deckName + " (" + deck.size() + ")");
            displayCard(card.substring(1)); // Ignore the first character
//            System.out.println("Drawn card: " + card);

        } else {
            System.out.println("No more cards left in the deck!");
        }
    }

    void displayState(String boardstate) {

        //removing spaces in boardstate string
        boardstate = boardstate.replaceAll(" ", "");

        TheBoard theBoard = new TheBoard(boardstate);

        GridPane boardGridPane = new GridPane();
        List<int[]> catPositions = new ArrayList<>();
//        boardGridPane.setHgap(2);
//        boardGridPane.setVgap(2);

        //For board
        for (int row = 0; row < theBoard.getRows(); row++) {

            for (int col = 0; col < theBoard.getColumns(); col++) {
                if (theBoard.hasCat(row, col)) {
                    catPositions.add(new int[]{row, col});
                } else {

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

        // Second pass: only fill cells that have cats. If this is not done, cats would get behind the board after the row in
        // which they lie.
        for (int[] position : catPositions) {
            int row = position[0];
            int col = position[1];

            //If cat is present, square with cat is placed on top of the square of same colour.
            StackPane stack = new StackPane();

            String imagePathWithoutCat = addSquareImageByColour(theBoard, row, col);
            Image imageWithoutCat = new Image(imagePathWithoutCat);

            // Create an ImageView to display the image
            ImageView imageViewWithoutCat = new ImageView(imageWithoutCat);
            imageViewWithoutCat.setFitHeight(squareSideSize);
            imageViewWithoutCat.setFitWidth(squareSideSize);


            String imagePath = addSquareWithCatByColour(theBoard, row, col);
            Image image = new Image(imagePath);

            // Create an ImageView to display the image
            ImageView imageView = new ImageView(image);

            imageView.setFitHeight(squareSideSize);
            imageView.setFitWidth(squareSideSize);

            stack.getChildren().addAll(imageViewWithoutCat, imageView);

            Draggable.Nature catDraggableNature = new Draggable.Nature(imageView);

            boardGridPane.add(stack, col, row);

        }

        boardGridPane.setLayoutX(425);
        boardGridPane.setLayoutY(MARGIN_Y);

        root.getChildren().add(boardGridPane);

    }

    String addSquareWithCatByColour(TheBoard theBoard, int row, int col) {

        String imagePath = "file:src/comp1110/ass2/gui/assets/";

        if (theBoard.getColor(row, col) == Colour.FIRE) {
            return imagePath + "fire.png";
        } else if (theBoard.getColor(row, col) == Colour.BLUE) {
            return imagePath + "blueCat.png";
        } else if (theBoard.getColor(row, col) == Colour.RED) {
            return imagePath + "redCat.png";
        } else if (theBoard.getColor(row, col) == Colour.YELLOW) {
            return imagePath + "yellowCat.png";
        } else if (theBoard.getColor(row, col) == Colour.PURPLE) {
            return imagePath + "purpleCat.png";
        } else if (theBoard.getColor(row, col) == Colour.GREEN) {
            return imagePath + "greenCat.png";
        } else if (theBoard.getColor(row, col) == Colour.OBJECTIVE) {
            return imagePath + "objective.png";
        }
        //TODO: Aditya, check
        else if (theBoard.getColor(row, col) == Colour.WILD) {
            return imagePath + "objective.png";
        } else {
            return imagePath + "blue.png";
        }
    }
    String addSquareImageByColour(TheBoard theBoard, int row, int col) {

        String imagePath = "file:src/comp1110/ass2/gui/assets/";

        if (theBoard.getColor(row, col) == Colour.FIRE) {
            return imagePath + "fire.png";
        } else if (theBoard.getColor(row, col) == Colour.BLUE) {
            return imagePath + "blue.png";
        } else if (theBoard.getColor(row, col) == Colour.RED) {
            return imagePath + "red.png";
        } else if (theBoard.getColor(row, col) == Colour.YELLOW) {
            return imagePath + "yellow.png";
        } else if (theBoard.getColor(row, col) == Colour.PURPLE) {
            return imagePath + "purple.png";
        } else if (theBoard.getColor(row, col) == Colour.GREEN) {
            return imagePath + "green.png";
        } else if (theBoard.getColor(row, col) == Colour.OBJECTIVE) {
            return imagePath + "objective.png";
        }
        //TODO: Aditya, check
        else if (theBoard.getColor(row, col) == Colour.WILD) {
            return imagePath + "objective.png";
        } else {
            return imagePath + "blue.png";
        }
    }

    String setupChallengeAndReturnBoardState(String difficulty) {

        Challenge challenge = new Challenge(Integer.parseInt(difficulty));

        String boardStateFromChallenge = setBoardStateFromSelectedChallenge(challenge.getChallenge());

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


    private void handleMousePress(MouseEvent event) {
        GridPane grid = (GridPane) event.getSource();
        selectedPathwayCard = grid;
        grid.toFront(); // Ensure the gridpane is at the front when selected
        grid.setCursor(javafx.scene.Cursor.MOVE);
    }

    //Not used, can be useful in future though
    private void handleMousePressOG(MouseEvent event) {
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
//            System.out.println("Namaste Mummy and Papa!!");
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
            boolean success = db.hasString();
            // Perform the move operation here
            event.setDropCompleted(success);
            event.consume();
        });

        // Additional cleanup if needed
        cardGrid.setOnDragDone(Event::consume);
    }


}
