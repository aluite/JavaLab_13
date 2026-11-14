package ru.guu;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    private TextField firstField;
    private TextField secondField;
    private Label resultLabel;

    @Override
    public void start(Stage stage) {
        Label title = new Label("Калькулятор суммы двух чисел");
        firstField = new TextField();
        firstField.setPromptText("Введите первое число");
        secondField = new TextField();
        secondField.setPromptText("Введите второе число");

        Button calculateButton = new Button("Вычислить сумму");
        Button clearButton = new Button("Очистить");
        resultLabel = new Label();

        calculateButton.setMaxWidth(Double.MAX_VALUE);
        clearButton.setMaxWidth(Double.MAX_VALUE);
        calculateButton.setOnAction(event -> calculateSum());
        clearButton.setOnAction(event -> clearForm());

        VBox root = new VBox(10, title, firstField, secondField,
                calculateButton, clearButton, resultLabel);
        root.setPadding(new Insets(18));
        root.setMinWidth(300);

        Scene scene = new Scene(root, 380, 260);
        stage.setTitle("Лабораторная работа №13 — JavaFX");
        stage.setScene(scene);
        stage.setMinWidth(320);
        stage.setMinHeight(240);
        stage.show();

        firstField.requestFocus();
    }

    private void calculateSum() {
        String firstText = firstField.getText().trim();
        String secondText = secondField.getText().trim();

        if (firstText.isEmpty() || secondText.isEmpty()) {
            resultLabel.setText("Ошибка: заполните оба поля.");
            return;
        }

        try {
            double first = Double.parseDouble(firstText);
            double second = Double.parseDouble(secondText);

            if (!Double.isFinite(first) || !Double.isFinite(second)) {
                resultLabel.setText("Ошибка: введите конечные числа.");
                return;
            }

            double sum = first + second;
            if (!Double.isFinite(sum)) {
                resultLabel.setText("Ошибка: результат слишком велик.");
                return;
            }

            resultLabel.setText("Результат: " + sum);
        } catch (NumberFormatException exception) {
            resultLabel.setText("Ошибка: используйте числа с точкой, например 2.5.");
        }
    }

    private void clearForm() {
        firstField.clear();
        secondField.clear();
        resultLabel.setText("");
        firstField.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
