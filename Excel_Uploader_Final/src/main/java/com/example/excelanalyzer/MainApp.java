package com.example.excelanalyzer;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class MainApp extends Application {
    private TableView<SalesData> table = new TableView<>();
    private ObservableList<SalesData> data = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();

        Button openButton = new Button("Choose file");
        openButton.setOnAction(e -> openFile(stage));

        // Создаем VBox для размещения кнопки "Choose File"
        VBox vbox = new VBox(10);
        vbox.setAlignment(javafx.geometry.Pos.CENTER); // Центрируем содержимое
        vbox.getChildren().add(openButton); // Добавляем только кнопку "Choose File"

        root.setCenter(vbox); // Устанавливаем VBox в центр

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("ExcelUploader");
        stage.setScene(scene);
        stage.show();
    }

    private void setupTable() {
        TableColumn<SalesData, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getId()).asString());

        TableColumn<SalesData, String> productColumn = new TableColumn<>("Name");
        productColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getProductName()));

        TableColumn<SalesData, String> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(String.valueOf(cellData.getValue().getPrice())));

        TableColumn<SalesData, String> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(String.valueOf(cellData.getValue().getQuantity())));

        TableColumn<SalesData, String> totalSaleColumn = new TableColumn<>("Total Sales");
        totalSaleColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(String.valueOf(cellData.getValue().getTotalSale())));

        TableColumn<SalesData, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getSaleDate().toString()));

        table.getColumns().addAll(idColumn, productColumn, priceColumn, quantityColumn, totalSaleColumn, dateColumn);
    }

    private void openFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try {
                // Настраиваем таблицу перед загрузкой данных
                setupTable();

                ExcelReader excelReader = new ExcelReader();
                List<SalesData> salesDataList = excelReader.readExcelFile(file.getAbsolutePath());
                data.setAll(salesDataList);
                table.setItems(data);

                // Создаем кнопку "Profit" и добавляем ее в интерфейс
                Button analyzeButton = new Button("Profit");
                analyzeButton.setOnAction(e -> openView(stage));

                // Создаем VBox для размещения таблицы и кнопки "Profit"
                VBox vbox = new VBox(10, table, analyzeButton);
                vbox.setAlignment(javafx.geometry.Pos.CENTER);
                BorderPane root = (BorderPane) stage.getScene().getRoot();
                root.setCenter(vbox); // Устанавливаем новый VBox в центр

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void openView(Stage parentStage) {
        new View(parentStage, data);
    }
}