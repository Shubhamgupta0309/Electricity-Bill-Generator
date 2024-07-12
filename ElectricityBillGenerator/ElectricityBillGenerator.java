import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ElectricityBillGenerator extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Electricity Bill Generator");

        Label nameLabel = new Label("Customer Name:");
        TextField nameField = new TextField();

        Label prevReadingLabel = new Label("Previous Reading:");
        TextField prevReadingField = new TextField();

        Label currReadingLabel = new Label("Current Reading:");
        TextField currReadingField = new TextField();

        Label connectionTypeLabel = new Label("Connection Type:");
        ComboBox<String> connectionTypeComboBox = new ComboBox<>();
        connectionTypeComboBox.getItems().addAll("Residential", "Commercial", "Industrial");
        connectionTypeComboBox.setValue("Residential");

        Button calculateButton = new Button("Calculate");
        Label totalCostLabel = new Label();

        calculateButton.setOnAction(event -> {
            try {
                double prevReading = Double.parseDouble(prevReadingField.getText());
                double currReading = Double.parseDouble(currReadingField.getText());
                double tariffRate = getTariffRate(connectionTypeComboBox.getValue());

                double unitsConsumed = currReading - prevReading;
                double totalCost = unitsConsumed * tariffRate;

                totalCostLabel.setText("Total Cost: \u20B9 " + totalCost);
            } catch (NumberFormatException e) {
                totalCostLabel.setText("Invalid input. Please enter valid readings.");
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.addRow(0, nameLabel, nameField);
        gridPane.addRow(1, prevReadingLabel, prevReadingField);
        gridPane.addRow(2, currReadingLabel, currReadingField);
        gridPane.addRow(3, connectionTypeLabel, connectionTypeComboBox);
        gridPane.add(calculateButton, 1, 4);
        gridPane.add(totalCostLabel, 1, 5);

        Scene scene = new Scene(gridPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private double getTariffRate(String connectionType) {
        if ("Residential".equals(connectionType)) {
            return 6.20; 
        } else if ("Commercial".equals(connectionType)) {
            return 9.50;
        } else if ("Industrial".equals(connectionType)) {
            return 15.00;
        } else {
            return 0.0;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
