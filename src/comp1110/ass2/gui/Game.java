package comp1110.ass2.gui;

import comp1110.ass2.Challenge;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Game extends Application {

    private final Group root = new Group();
    private static final int WINDOW_WIDTH = 1100;
    private static final int WINDOW_HEIGHT = 650;

    private final String initialChallenge = "0";

    // FIXME TASK 11 Basic game
    // FIXME TASK 13 Fully working game

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(this.root, WINDOW_WIDTH, WINDOW_HEIGHT);
        createDropdownMenu();
        stage.setScene(scene);
        stage.show();
    }


    void createDropdownMenu() {
        // Create a ComboBox (dropdown menu)
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("0","1", "2", "3", "4", "5");

//        comboBox.setPromptText("Select the Difficulty Level");
        comboBox.setValue(initialChallenge);
        setupChallenge(initialChallenge);

        Label selectedOptionLabel = new Label();

        // Event handler to the ComboBox
        comboBox.setOnAction(event -> {
            // Get the selected item
            String selectedItem = comboBox.getSelectionModel().getSelectedItem();
            selectedOptionLabel.setText("Difficulty Level Selected: " + selectedItem);

            setupChallenge(selectedItem);

        });

        // Create a VBox to hold the ComboBox and Label
        VBox vbox = new VBox(comboBox, selectedOptionLabel);

        // Set spacing for VBox
        vbox.setSpacing(10);
        root.getChildren().add(vbox);

    }

    void setupChallenge(String difficulty){

        Challenge challenge = new Challenge(Integer.parseInt(difficulty));

//        System.out.println("Namaste Mummy and Papa!! " + challenge.getChallenge());

    }


}
