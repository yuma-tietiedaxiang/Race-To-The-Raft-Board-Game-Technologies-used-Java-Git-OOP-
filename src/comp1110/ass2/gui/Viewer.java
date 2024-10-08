package comp1110.ass2.gui;

import comp1110.ass2.TheBoard;
import comp1110.ass2.Colour;
import comp1110.ass2.PathwayCard;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Viewer extends Application {

    private final Group root = new Group();
    private static final int VIEWER_WIDTH = 1100;
    private static final int VIEWER_HEIGHT = 650;
    private static final int MARGIN_X = 20;

    private final Group controls = new Group();
    private TextArea handTextField;
    private TextArea boardTextField;

    /**
     * Draw the given board and hand in the window, removing any previously drawn boards/hands.
     *
     * @param boardstate newline separated string representing each row of the board (the board string, see the STRING-REPRESENTATION.md for more details
     * @param hand       A string representing the cards in a player's hand (the hand string, see the STRING-REPRESENTATION.md for more details)
     */

    // author: Aditya Arora
    void displayState(String boardstate, String hand) {

        root.getChildren().clear(); // Clear previous state
        root.getChildren().add(controls); // Add controls back

//        String testingBoardState = """
//                 fffffffffrrfffffff
//                 fffffffffrRfffffff
//                 fffffffffrrfffffff
//                 fffgffyrgpygyrygbr
//                 fffgGfggyygprbprpg
//                 fffgggbgprbpygbpyb
//                 ffffffbpbpgrbrrbgy
//                 ffffffgygybpgygprb
//                 ffffffbrrrybgygybg
//                 ffffffgpbbyrprgbbp
//                 ffffffbyrbpybgpryg
//                 ffffffbyrbpybgpryg
//                 ffffffpgyrggrbgyby
//                 fffffybgbpryybpgyp
//                 ffffYyybpgbprygrow
//                 fffyyyyryygbygybww
//                 """;

        //removing spaces in boardstate string
        boardstate = boardstate.replaceAll(" ", "");

        TheBoard theBoard = new TheBoard(boardstate);
        PathwayCard pathwayCard = new PathwayCard(hand);

//        TheBoard theBoard = new TheBoard(testingBoardState);
//        PathwayCard pathwayCard = new PathwayCard("AfhkBCDahw");

        //For hand Pathway cards
        for (int count = 0; count < pathwayCard.getNumberOfCardsInHand(); count++) {

            GridPane pathwayCardGridPane = new GridPane();
            pathwayCardGridPane.setHgap(2);
            pathwayCardGridPane.setVgap(2);

            //ignoring first char
            int charPosition = 1;

            for (int row = 0; row < 3; row++) {

                for (int col = 0; col < 3; col++) {

                    StackPane stack = new StackPane();
                    Rectangle rectangle = new Rectangle(25, 25);

                    char c = pathwayCard.getCardsInHand().get(count).charAt(charPosition);

                    rectangle.setFill(getColourFromChar(c));

                    Text text = new Text(String.valueOf(c));
                    stack.getChildren().addAll(rectangle, text);
                    pathwayCardGridPane.add(stack, col, row);

                    charPosition++;
//                    pathwayCardGridPane.add(rectangle, col, row);

                }
            }

            pathwayCardGridPane.setLayoutX(30 + 105 * ((double) count % 2));
            pathwayCardGridPane.setLayoutY(85 + 85 * ((double) count / 2));

            root.getChildren().add(pathwayCardGridPane);

        }

        GridPane boardGridPane = new GridPane();
        boardGridPane.setHgap(2);
        boardGridPane.setVgap(2);


        //For board
        for (int row = 0; row < theBoard.getRows(); row++) {

            for (int col = 0; col < theBoard.getColumns(); col++) {


//                String imagePath = "file:src/comp1110/ass2/gui/assets/blue.png";
//                Image image = new Image(imagePath);
//
//                // Create an ImageView to display the image
//                ImageView imageView = new ImageView(image);
//
//                imageView.setFitHeight(35);
//                imageView.setFitWidth(35);
//
//                root.getChildren().add(imageView);


//                Rectangle rectangle = new Rectangle(35, 35);
//                StackPane stack = new StackPane();
//
//                rectangle.setFill(getSquareColour(theBoard, row, col));

                if (theBoard.hasCat(row, col)) {
//                    Text text = new Text("CAT");
//                    text.setFont(Font.font(14));
//                    text.setFill(getSquareColour(theBoard, row, col));
//                    stack.getChildren().addAll(rectangle, text);
//                    boardGridPane.add(stack, col, row);


                    String imagePath = addSquareWithCatByColour(theBoard, row, col);
                    Image image = new Image(imagePath);

                    // Create an ImageView to display the image
                    ImageView imageView = new ImageView(image);

                    imageView.setFitHeight(35);
                    imageView.setFitWidth(35);

                    boardGridPane.add(imageView, col, row);


//                    root.getChildren().add(imageView);

                } else {

                    String imagePath = addSquareImageByColour(theBoard, row, col);
                    Image image = new Image(imagePath);

                    // Create an ImageView to display the image
                    ImageView imageView = new ImageView(image);

                    imageView.setFitHeight(35);
                    imageView.setFitWidth(35);

                    boardGridPane.add(imageView, col, row);

//                    Text text = new Text(String.valueOf(theBoard.getSquares()[row][col]));
//                    stack.getChildren().addAll(rectangle, text);
//                    boardGridPane.add(stack, col, row);
                }
            }
        }

        boardGridPane.setLayoutX(400);
        boardGridPane.setLayoutY(20);

        root.getChildren().add(boardGridPane);

        // FIXME TASK 4 - Done
    }

    // author: Aditya Arora
    Color getColourFromChar(char ch) {

        Colour colour = Colour.fromChar(ch);
        if (colour == Colour.BLUE) {
            return Color.BLUE;
        } else if (colour == Colour.RED) {
            return Color.RED;
        } else if (colour == Colour.YELLOW) {
            return Color.YELLOW;
        } else if (colour == Colour.PURPLE) {
            return Color.PURPLE;
        } else if (colour == Colour.GREEN) {
            return Color.GREEN;
        } else if (colour == Colour.OBJECTIVE) {
            return Color.GOLD;
        } else if (colour == Colour.WILD) {
            return Color.BROWN;
        } else {
            return Color.BLACK;
        }
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
//        else if (theBoard.getColor(row,col) == Colour.WILD) {
//            return Color.BROWN;
//        }
        else {
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
//        else if (theBoard.getColor(row,col) == Colour.WILD) {
//            return Color.BROWN;
//        }
        else {
            return imagePath + "blue.png";
        }
    }

    String addSquareImageByColourUseless(Color colour) {


        //String imagePath = "file:src/comp1110/ass2/gui/assets/blue.png";
        String imagePath = "file:src/comp1110/ass2/gui/assets/";

        if (colour == Color.BLUE) {
            return imagePath + "blue.png";
        } else if (colour == Color.RED) {
            return imagePath + "red.png";
        } else if (colour == Color.YELLOW) {
            return imagePath + "yellow.png";
        } else if (colour == Color.PURPLE) {
            return imagePath + "purple.png";
        } else if (colour == Color.GREEN) {
            return imagePath + "green.png";
        }
//        else if (colour == Color.OBJECTIVE) {
//            return imagePath + "objective.png";
//        }else if (colour == Colour.WILD) {
//            //TODO: Aditya, check this
//            return imagePath + "blue.png";
//        }
        else {
            return imagePath + "blue.png";
        }

    }

    // author: Aditya Arora
    Color getSquareColour(TheBoard theBoard, int row, int col) {
        if (theBoard.getColor(row, col) == Colour.FIRE) {
            return Color.DARKORANGE;
        } else if (theBoard.getColor(row, col) == Colour.BLUE) {
            return Color.BLUE;
        } else if (theBoard.getColor(row, col) == Colour.RED) {
            return Color.RED;
        } else if (theBoard.getColor(row, col) == Colour.YELLOW) {
            return Color.YELLOW;
        } else if (theBoard.getColor(row, col) == Colour.PURPLE) {
            return Color.PURPLE;
        } else if (theBoard.getColor(row, col) == Colour.GREEN) {
            return Color.GREEN;
        } else if (theBoard.getColor(row, col) == Colour.OBJECTIVE) {
            return Color.GOLD;
        } else if (theBoard.getColor(row, col) == Colour.WILD) {
            return Color.BROWN;
        } else {
            return Color.BLACK;
        }
    }

    /**
     * Generate controls for Viewer
     */
    private void makeControls() {
        Label playerLabel = new Label("Cards in hand:");
        handTextField = new TextArea();
        handTextField.setPrefWidth(100);
        Label boardLabel = new Label("Board State:");
        boardTextField = new TextArea();
        boardTextField.setPrefWidth(100);
        Button button = refreshButton();
        button.setLayoutY(VIEWER_HEIGHT - 250);
        button.setLayoutX(MARGIN_X);
        HBox fields = new HBox();
        fields.getChildren().addAll(handTextField, boardTextField);
        fields.setSpacing(20);
        fields.setLayoutX(MARGIN_X);
        fields.setLayoutY(VIEWER_HEIGHT - 200);
        HBox labels = new HBox();
        labels.getChildren().addAll(playerLabel, boardLabel);
        labels.setSpacing(45);
        labels.setLayoutX(MARGIN_X);
        labels.setLayoutY(VIEWER_HEIGHT - 220);

        final Rectangle rectangle = new Rectangle(100, 100, 200, 50);
        Draggable.Nature nature = new Draggable.Nature(rectangle);

        controls.getChildren().addAll(fields, labels, button);
//        controls.getChildren().addAll(fields, labels, button, rectangle);
    }

    /**
     * Create refresh button. Upon pressing, capture the textFields and call displayState
     *
     * @return the created button
     */
    private Button refreshButton() {
        Button button = new Button("Refresh");
        button.setOnAction(e -> {
            String boardText = boardTextField.getText();
            String handCards = handTextField.getText();
            displayState(boardText, handCards);
        });
        return button;
    }

    // author: Aditya Arora and Fabian
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Race to the Raft Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
        makeControls();
        displayState("", "");
//        root.getChildren().add(controls);
        makeControls();
        stage.setScene(scene);
        stage.show();
    }
}
