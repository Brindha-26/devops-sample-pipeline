package com.brindha;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class App extends Application {
    private ListView<String> taskListView;
    private TextField taskInput;
    private List<String> tasks;

    @Override
    public void start(Stage stage) {
        tasks = new ArrayList<>();
        
        // Create the main layout
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        
        // Title
        Label title = new Label("TODO List");
        title.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
        root.setTop(title);
        
        // Center - Task List
        taskListView = new ListView<>();
        taskListView.setPrefHeight(300);
        root.setCenter(taskListView);
        
        // Bottom - Input Section
        VBox inputSection = createInputSection();
        root.setBottom(inputSection);
        
        // Create the scene and show the stage
        Scene scene = new Scene(root, 400, 500);
        stage.setTitle("JavaFX TODO List");
        stage.setScene(scene);
        stage.show();
    }

    private VBox createInputSection() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        
        // Input field
        taskInput = new TextField();
        taskInput.setPromptText("Enter a new task...");
        
        // Buttons
        HBox buttonBox = new HBox(10);
        
        Button addButton = new Button("Add Task");
        addButton.setPrefWidth(100);
        addButton.setOnAction(e -> addTask());
        
        Button deleteButton = new Button("Delete Task");
        deleteButton.setPrefWidth(100);
        deleteButton.setOnAction(e -> deleteTask());
        
        Button clearButton = new Button("Clear All");
        clearButton.setPrefWidth(100);
        clearButton.setOnAction(e -> clearAllTasks());
        
        buttonBox.getChildren().addAll(addButton, deleteButton, clearButton);
        
        vbox.getChildren().addAll(taskInput, buttonBox);
        return vbox;
    }

    private void addTask() {
        String task = taskInput.getText().trim();
        if (!task.isEmpty()) {
            tasks.add(task);
            taskListView.getItems().add(task);
            taskInput.clear();
        }
    }

    private void deleteTask() {
        int selectedIndex = taskListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            tasks.remove(selectedIndex);
            taskListView.getItems().remove(selectedIndex);
        } else {
            showAlert("Please select a task to delete.");
        }
    }

    private void clearAllTasks() {
        if (!tasks.isEmpty()) {
            tasks.clear();
            taskListView.getItems().clear();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
