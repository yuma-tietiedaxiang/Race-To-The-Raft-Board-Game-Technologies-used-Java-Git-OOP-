package comp1110.ass2.gui;

import comp1110.TheBoard;
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

        for (int i = 0; i < theBoard.getRows(); i++) {

            for (int j = 0; j < theBoard.getColumns(); j++) {

                Rectangle rectangle = new Rectangle(35, 35);

                checkAndSetSquareColour(theBoard, rectangle, i, j);

                if(theBoard.hasCat(i,j)){
                    System.out.println("Namaste Mummy and Papa!! " + i + " : " + j +  " has cat");
                    StackPane stack = new StackPane();
                    Text text = new Text("CAT");
                    text.setFont(Font.font(20));
                    stack.getChildren().addAll(rectangle, text);
                    gridPane.add(stack, j, i);
                }else{
                    gridPane.add(rectangle, j, i);
                }
            }
        }

        gridPane.setLayoutX(380);

        root.getChildren().add(gridPane);

        // FIXME TASK 4
    }

    void checkAndSetSquareColour(TheBoard theBoard, Rectangle rectangle, int i, int j){
        if (theBoard.getColor(i,j) == 'f') {
            rectangle.setFill(Color.DARKORANGE);
        } else if (theBoard.getColor(i,j) == 'b') {
            rectangle.setFill(Color.BLUE);
        } else if (theBoard.getColor(i,j) == 'r') {
            rectangle.setFill(Color.RED);
        }
        else if (theBoard.getColor(i,j) == 'y') {
            rectangle.setFill(Color.YELLOW);
        }
        else if (theBoard.getColor(i,j) == 'p') {
            rectangle.setFill(Color.PURPLE);
        }else if (theBoard.getColor(i,j) == 'g') {
            rectangle.setFill(Color.GREEN);
        }else{
            rectangle.setFill(Color.BLACK);
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
