package javafxapplication1;

import javafx.application.Application;
import javafx.geometry.Insets;
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
import java.sql.ResultSet;
import java.sql.SQLException;

public class login extends Application {

    // JDBC URL, username, and password
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/projetpoo";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    // SQL query to retrieve user information
    private static final String SELECT_USER_SQL = "SELECT * FROM utilisateur WHERE username = ? AND pwd = ?";

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Log In");

        // Create elements for the login window
        Text titleText = new Text("Log In");
        titleText.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        titleText.setFill(Color.rgb(64, 64, 64));

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameField.setPrefWidth(200);

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(200);

        Button loginButton = new Button("Log In");
        Button signUpButton = new Button("Sign Up");

        // Create a grid to organize the elements
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(15);
        grid.setHgap(10);
        grid.add(titleText, 0, 0, 2, 1);
        grid.add(usernameLabel, 0, 1);
        grid.add(usernameField, 1, 1);
        grid.add(passwordLabel, 0, 2);
        grid.add(passwordField, 1, 2);
        grid.add(loginButton, 1, 3);
        grid.add(signUpButton, 1, 5);
        grid.add(new Label("Don't have an account yet?"), 0, 5);

        // Add some styling
        grid.setStyle("-fx-background-color: #f5f5f5; -fx-border-color: #ddd; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-padding: 20px;");
        titleText.setStyle("-fx-font-size: 32px;");
        loginButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;");
        signUpButton.setStyle("-fx-background-color: #666666; -fx-text-fill: white;");

        // Create a border pane to center the grid
        BorderPane root = new BorderPane(grid);

        // Create the scene and display the window
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Event handler for the "Sign Up" button
        signUpButton.setOnAction(e -> {
            // Close the login window
            primaryStage.close();

            // Launch the sign-up window
            new SignUp().start(new Stage());
        });

        // Event handler for the "Log In" button
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (username.isEmpty() || password.isEmpty()) {
                showAlert("Empty Fields", "Please fill in both username and password.");
            } else {
                // Authenticate user
                authenticateUser(username, password);
            }
        });
    }

    private void authenticateUser(String username, String password) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_SQL)) {
            // Set parameters for the SQL statement
            statement.setString(1, username);
            statement.setString(2, password);

            // Execute the SQL statement
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Login successful!");
            } else {
                showAlert("Login Failed", "Invalid username or password.");
            }
        } catch (SQLException ex) {
            System.err.println("Error occurred while authenticating user: " + ex.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
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
}
