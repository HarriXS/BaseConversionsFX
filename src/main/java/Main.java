import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage){
        // Constants
        final double HEIGHT = 250;
        final double WIDTH = 200;

        // Random JavaFX shite
        Pane root = new Pane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        // The list to be shown in the combo boxes
        String[] bases = {"Binary", "Denary", "Hexadecimal"};

        // Creating the nodes
        TextField inputField = new TextField();
        TextField outputField = new TextField();
        ComboBox<String> inputType = new ComboBox<>(FXCollections.observableArrayList(bases));
        ComboBox<String> outputType = new ComboBox<>(FXCollections.observableArrayList(bases));

        // Add them to the pane
        root.getChildren().add(inputField);
        root.getChildren().add(outputField);
        root.getChildren().add(inputType);
        root.getChildren().add(outputType);

        // Random JavaFX shite continued
        stage.setTitle("Base Conversions");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        // Positioning the nodes on the pane
        final int YOFFSET = 50;
        inputField.setLayoutX((WIDTH / 2) - (inputField.getWidth() / 2));   // Centered on the x-axis
        inputField.setLayoutY(YOFFSET);
        outputField.setLayoutX((WIDTH / 2) - (outputField.getWidth() / 2));
        outputField.setLayoutY(HEIGHT - YOFFSET - outputField.getHeight() / 2);
        inputType.setLayoutX((WIDTH / 2) - (inputType.getWidth() / 2));
        inputType.setLayoutY(YOFFSET + 30);
        outputType.setLayoutX((WIDTH / 2) - (outputType.getWidth() / 2));
        outputType.setLayoutY(HEIGHT - YOFFSET - 30 - outputType.getHeight() / 2);

        // Set default values of the combo boxes
        inputType.getSelectionModel().select("Binary");
        outputType.getSelectionModel().select("Denary");

        // Setup event listeners
        inputField.textProperty().addListener((observable, oldValue, newValue) -> update(inputField, outputField, inputType, outputType));
        inputType.valueProperty().addListener((observable, oldValue, newValue) -> update(inputField, outputField, inputType, outputType));
        outputType.valueProperty().addListener((observable, oldValue, newValue) -> update(inputField, outputField, inputType, outputType));
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Update the text in the bottom field
    private static void update(TextField inputField, TextField outputField, ComboBox<String> inputType, ComboBox<String> outputType) {
        // Other Classes
        BinaryConversions binConv = new BinaryConversions();
        DenaryConversions denConv = new DenaryConversions();
        HexConversions hexConv = new HexConversions();

        switch(inputType.getValue()) {
            case "Binary":
                switch (outputType.getValue()) {
                    case "Binary" -> outputField.setText(inputField.getText());
                    case "Denary" -> outputField.setText((binConv.binaryToDenary(inputField.getText())));
                    case "Hexadecimal" -> outputField.setText(binConv.binaryToHex(inputField.getText()));
                }
                break;
            case "Denary":
                switch (outputType.getValue()) {
                    case "Binary" -> outputField.setText(denConv.denaryToBinary(inputField.getText()));
                    case "Denary" -> outputField.setText(inputField.getText());
                    case "Hexadecimal" -> outputField.setText(denConv.denaryToHex(inputField.getText()));
                }
                break;
            case "Hexadecimal":
                switch (outputType.getValue()) {
                    case "Binary" -> outputField.setText(hexConv.hexToBinary(inputField.getText()));
                    case "Denary" -> outputField.setText(hexConv.hexToDenary(inputField.getText()));
                    case "Hexadecimal" -> outputField.setText(inputField.getText());
                }
                break;
        }
    }
}
