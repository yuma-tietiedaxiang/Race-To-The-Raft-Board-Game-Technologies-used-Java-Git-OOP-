package comp1110.ass2.gui;

import comp1110.TheBoard;
import comp1110.ass2.Colour;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Random;

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
     * @param hand A string representing the cards in a player's hand (the hand string, see the STRING-REPRESENTATION.md for more details)
     *
     */
    void displayState(String boardstate, String hand) {

//        root.getChildren().clear(); // Clear previous state
//        root.getChildren().add(controls); // Add controls back

        String testingBoardState = """
                 fffffffffrrfffffff
                 fffffffffrRfffffff
                 fffffffffrrfffffff
                 fffgffyrgpygyrygbr
                 fffgGfggyygprbprpg
                 fffgggbgprbpygbpyb
                 ffffffbpbpgrbrrbgy
                 ffffffgygybpgygprb
                 ffffffbrrrybgygybg
                 ffffffgpbbyrprgbbp
                 ffffffbyrbpybgpryg
                 ffffffbyrbpybgpryg
                 ffffffpgyrggrbgyby
                 fffffybgbpryybpgyp
                 ffffYyybpgbprygrow
                 fffyyyyryygbygybww
                 """;

        TheBoard theBoard = new TheBoard(testingBoardState);

        System.out.println("Namaste Mummy and Papa!! " + theBoard.getRows() + " : " + theBoard.getColumns());

        GridPane gridPane = new GridPane();
        gridPane.setHgap(2);
        gridPane.setVgap(2);

        for (int row = 0; row < theBoard.getRows(); row++) {

            for (int col = 0; col < theBoard.getColumns(); col++) {

                Rectangle rectangle = new Rectangle(35, 35);
                StackPane stack = new StackPane();

                rectangle.setFill(getSquareColour(theBoard, row, col));

                if(theBoard.hasCat(row,col)){
                    System.out.println("Namaste Mummy and Papa!! " + row + " : " + col +  " has cat");
                    Text text = new Text("CAT");
                    text.setFont(Font.font(14));
//                    text.setFill(getSquareColour(theBoard, row, col));
                    stack.getChildren().addAll(rectangle, text);
                    gridPane.add(stack, col, row);
                }else{
                    Text text = new Text(String.valueOf(theBoard.getSquares()[row][col]));
                    stack.getChildren().addAll(rectangle, text);
                    gridPane.add(stack, col, row);
                }
            }
        }

        gridPane.setLayoutX(380);

        root.getChildren().add(gridPane);

        // FIXME TASK 4
    }

    Color getSquareColour(TheBoard theBoard, int row, int col){
        if (theBoard.getColor(row,col) == Colour.FIRE) {
            return Color.DARKORANGE;
        } else if (theBoard.getColor(row,col) == Colour.BLUE) {
            return Color.BLUE;
        } else if (theBoard.getColor(row,col) == Colour.RED) {
            return Color.RED;
        }
        else if (theBoard.getColor(row,col) == Colour.YELLOW) {
            return Color.YELLOW;
        }
        else if (theBoard.getColor(row,col) == Colour.PURPLE) {
            return Color.PURPLE;
        }else if (theBoard.getColor(row,col) == Colour.GREEN) {
            return Color.GREEN;
        }else{
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
        controls.getChildren().addAll(fields, labels, button);
    }

    /**
     * Create refresh button. Upon pressing, capture the textFields and call displayState
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

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Race to the Raft Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
        makeControls();
        displayState("", "");
        root.getChildren().add(controls);
        makeControls();
        stage.setScene(scene);
        stage.show();
    }
}
