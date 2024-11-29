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
import java.sql.ResultSet;


public class SignUp extends Application {

    
    // JDBC URL, username, and password
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/projetpoo";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    // SQL query to insert a new user
    private static final String INSERT_USER_SQL = "INSERT INTO utilisateur (username, pwd, email, nom, prenom, type, id_conf) VALUES (?, ?, ?, ?, ?, ?, 1111)";

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sign Up");

        // Create elements for the sign-up window
        Text titleText = new Text("Sign Up");
        titleText.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        titleText.setFill(Color.rgb(64, 64, 64));

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameField.setPrefWidth(200);

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(200);

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setPrefWidth(200);

        Label firstNameLabel = new Label("First Name:");
        TextField firstNameField = new TextField();
        firstNameField.setPrefWidth(200);

        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameField = new TextField();
        lastNameField.setPrefWidth(200);

        ChoiceBox<String> userTypeChoice = new ChoiceBox<>();
        userTypeChoice.getItems().addAll("Author", "Guest", "Participant");
        userTypeChoice.setValue("Author");

        Button signUpButton = new Button("Sign Up");
        Button loginButton = new Button("Log In");
        
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
        grid.add(emailLabel, 0, 3);
        grid.add(emailField, 1, 3);
        grid.add(firstNameLabel, 0, 4);
        grid.add(firstNameField, 1, 4);
        grid.add(lastNameLabel, 0, 5);
        grid.add(lastNameField, 1, 5);
        grid.add(new Label("User Type:"), 0, 6);
        grid.add(userTypeChoice, 1, 6);
        grid.add(signUpButton, 1, 7);
        grid.add(loginButton, 1, 9);
        grid.add(new Label("Already have an account?"), 0, 9);


        // Add some styling
        grid.setStyle("-fx-background-color: #f5f5f5; -fx-border-color: #ddd; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-padding: 20px;");
        titleText.setStyle("-fx-font-size: 32px;");
        signUpButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;");
        loginButton.setStyle("-fx-background-color: #666666; -fx-text-fill: white;");

        // Create a border pane to center the grid
        BorderPane root = new BorderPane(grid);

        // Create the scene and display the window
        Scene scene = new Scene(root, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        // Event handler for the "Log In" button
        loginButton.setOnAction(e -> {
            // Close the sign-up window
            primaryStage.close();

            // Launch the login window
            new login().start(new Stage());
        });

        // Event handler for the "Sign Up" button
        signUpButton.setOnAction(e -> {
        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty() || emailField.getText().isEmpty() ||
            firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty()) {
            showAlert("Empty Fields", "Please fill in all fields.");
        } else if (!isValidUsername(usernameField.getText())) {
            showAlert("Invalid Username", "Username should contain only letters and spaces.");
        } else if (!isValidPassword(passwordField.getText())) {
            showAlert("Invalid Password", "Password should not contain spaces.");
        } else if (!isValidEmail(emailField.getText())) {
            showAlert("Invalid Email", "Please enter a valid email address.");
        } else if (!isValidName(firstNameField.getText())) {
            showAlert("Invalid First Name", "First Name should contain only letters and spaces.");
        } else if (!isValidName(lastNameField.getText())) {
            showAlert("Invalid Last Name", "Last Name should contain only letters and spaces.");
        } else if (emailExists(emailField.getText())) {
            showAlert("User Already Exists", "A user with this email already exists.");
        } else if (usernameExists(usernameField.getText())) {
            showAlert("Username Exists", "Please choose a different username.");
        }  else {
            // Form is valid, proceed with sign-up process
        signUpUser(usernameField.getText(), passwordField.getText(), emailField.getText(),
                firstNameField.getText(), lastNameField.getText(), userTypeChoice.getValue());
        }
       });
        
        
        
        
     }
    // Method to check if email already exists
    private boolean emailExists(String email) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
         PreparedStatement statement = connection.prepareStatement("SELECT * FROM utilisateur WHERE email = ?")) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException ex) {
            System.err.println("Error occurred while checking email existence: " + ex.getMessage());
            return false;
        }
    }

    // Method to check if username already exists
    private boolean usernameExists(String username) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM utilisateur WHERE username = ?")) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException ex) {
            System.err.println("Error occurred while checking username existence: " + ex.getMessage());
            return false;
        }
    }


    private boolean isValidUsername(String username) {
        return username.matches("[a-zA-Z ]+");
    }

    private boolean isValidPassword(String password) {
        return !password.contains(" ");
    }

    private boolean isValidEmail(String email) {
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }

    private boolean isValidName(String name) {
        return name.matches("[a-zA-Z ]+");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    // Method to sign up a new user
    private void signUpUser(String username, String password, String email, String firstName, String lastName, String userType) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(INSERT_USER_SQL)) {
            // Set parameters for the SQL statement
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);
            statement.setString(4, firstName);
            statement.setString(5, lastName);
            statement.setString(6, userType);

        // Execute the SQL statement
        int rowsInserted = statement.executeUpdate();

        if (rowsInserted > 0) {
            System.out.println("User signed up successfully!");
            // Commit the transaction
//            connection.commit();
        } else {
            System.out.println("Failed to sign up user.");
        }
    } catch (SQLException ex) {
        System.err.println("Error occurred while signing up user: " + ex.getMessage());
    }
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
