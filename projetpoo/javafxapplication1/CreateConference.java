/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplication1;

/**
 *
 * @author mohamed
 */
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CreateConference extends Application {

    // JDBC URL, username, and password
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/projetpoo";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    // SQL query to insert a new conference
    private static final String INSERT_CONFERENCE_SQL = "INSERT INTO conference (titre, president_nom, president_prenom, instit_org, date, lieu, topics, date_limite_inscription, frais_inscription) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Create Conference");

        // Create elements for the conference creation form
        Text titleText = new Text("Create Conference");
        titleText.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        titleText.setFill(Color.rgb(64, 64, 64));

        Label titleLabel = new Label("Title:");
        TextField titleField = new TextField();

        Label presidentNameLabel = new Label("President's Name:");
        TextField presidentNameField = new TextField();

        Label presidentLastNameLabel = new Label("President's Last Name:");
        TextField presidentLastNameField = new TextField();

        Label institOrgLabel = new Label("Institution/Organization:");
        TextField institOrgField = new TextField();

        Label dateLabel = new Label("Date:");
        DatePicker dateField = new DatePicker();

        Label locationLabel = new Label("Location:");
        TextField locationField = new TextField();

        Label topicsLabel = new Label("Topics:");
        TextArea topicsField = new TextArea();
        topicsField.setPrefColumnCount(20);
        topicsField.setPrefRowCount(4);

        Label deadlineLabel = new Label("Registration Deadline:");
        DatePicker deadlineField = new DatePicker();

        Label registrationFeeLabel = new Label("Registration Fee:");
        TextField registrationFeeField = new TextField();
        registrationFeeField.setPromptText("Digits only");

        Button createButton = new Button("Create");
//        Button cancelButton = new Button("Cancel");

        // Create a grid to organize the elements
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20));
        grid.setVgap(15);
        grid.setHgap(10);
        grid.add(titleText, 0, 0, 2, 1);
        grid.add(titleLabel, 0, 1);
        grid.add(titleField, 1, 1);
        grid.add(presidentNameLabel, 0, 2);
        grid.add(presidentNameField, 1, 2);
        grid.add(presidentLastNameLabel, 0, 3);
        grid.add(presidentLastNameField, 1, 3);
        grid.add(institOrgLabel, 0, 4);
        grid.add(institOrgField, 1, 4);
        grid.add(dateLabel, 0, 5);
        grid.add(dateField, 1, 5);
        grid.add(locationLabel, 0, 6);
        grid.add(locationField, 1, 6);
        grid.add(topicsLabel, 0, 7);
        grid.add(topicsField, 1, 7);
        grid.add(deadlineLabel, 0, 8);
        grid.add(deadlineField, 1, 8);
        grid.add(registrationFeeLabel, 0, 9);
        grid.add(registrationFeeField, 1, 9);
        grid.add(createButton, 1, 10);
//        grid.add(cancelButton, 0, 10);

        // Add some styling
        grid.setStyle("-fx-background-color: #f5f5f5; -fx-border-color: #ddd; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-padding: 20px;");
        titleText.setStyle("-fx-font-size: 32px;");
        createButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;");
//        cancelButton.setStyle("-fx-background-color: #666666; -fx-text-fill: white;");

        // Create a border pane to center the grid
        BorderPane root = new BorderPane(grid);

        // Create the scene and display the window
        Scene scene = new Scene(root, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Event handler for the "Create" button
        createButton.setOnAction(e -> {
            if (titleField.getText().isEmpty() || presidentNameField.getText().isEmpty() || presidentLastNameField.getText().isEmpty()
                    || institOrgField.getText().isEmpty() || dateField.getValue() == null || locationField.getText().isEmpty()
                    || topicsField.getText().isEmpty() || deadlineField.getValue() == null || registrationFeeField.getText().isEmpty()) {
                showAlert("Empty Fields", "Please fill in all fields.", Alert.AlertType.ERROR);
            } else if (!isValidName(presidentNameField.getText()) || !isValidName(presidentLastNameField.getText())) {
                showAlert("Invalid Name", "Name and Last Name should contain only letters and spaces.",Alert.AlertType.ERROR);
            } else if (!isNumeric(registrationFeeField.getText())) {
                showAlert("Invalid Registration Fee", "Registration Fee should contain only digits.",Alert.AlertType.ERROR);
            } else {
                createConference(titleField.getText(), presidentNameField.getText(), presidentLastNameField.getText(),
                        institOrgField.getText(), dateField, locationField.getText(), topicsField.getText(),
                        deadlineField, Double.parseDouble(registrationFeeField.getText()));
            showAlert("Conference Created", "The conference was created successfully!", Alert.AlertType.CONFIRMATION);
            primaryStage.close();
                       }
        });

    }

    private boolean isValidName(String name) {
        return name.matches("[a-zA-Z ]+");
    }

    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    private void createConference(String title, String presidentName, String presidentLastName, String institOrg,
                                  DatePicker date, String location, String topics, DatePicker deadline, double registrationFee) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(INSERT_CONFERENCE_SQL)) {
            // Set parameters for the SQL statement
            statement.setString(1, title);
            statement.setString(2, presidentName);
            statement.setString(3, presidentLastName);
            statement.setString(4, institOrg);
            statement.setDate(5, java.sql.Date.valueOf(date.getValue()));
            statement.setString(6, location);
            statement.setString(7, topics);
            statement.setDate(8, java.sql.Date.valueOf(deadline.getValue()));
            statement.setDouble(9, registrationFee);

            // Execute the SQL statement
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Conference created successfully!");
            } else {
                System.out.println("Failed to create conference.");
            }
        } catch (SQLException ex) {
            System.err.println("Error occurred while creating conference: " + ex.getMessage());
        }
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);

    // Customize the alert icon based on the alert type
    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    switch (alertType) {
        case ERROR:
            alert.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("error_icon.png"))));
            break;
        case CONFIRMATION:
            alert.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("confirmation_icon.png"))));
            break;
        default:
            break;
    }

    alert.showAndWait();
}

    public static void main(String[] args) {
        // Register MySQL JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
            return;
        }
        launch(args);
    }

    void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

