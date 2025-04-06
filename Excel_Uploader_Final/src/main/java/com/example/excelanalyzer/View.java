package com.example.excelanalyzer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class View {
    private Stage stage;
    private ComboBox<String> productSelector;
    private ProfitChart chartAnalyzer;

    public View(Stage parentStage, ObservableList<SalesData> salesData) {
        stage = new Stage();
        BorderPane root = new BorderPane();

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> stage.close());

        productSelector = new ComboBox<>();
        productSelector.getItems().add("All");
        for (SalesData data : salesData) {
            if (!productSelector.getItems().contains(data.getProductName())) {
                productSelector.getItems().add(data.getProductName());
            }
        }

        // Автоматическое обновление графика при изменении выбранного продукта
        productSelector.setOnAction(e -> updateChart(salesData));

        ImageView backIcon = new ImageView(new Image("file:back_icon.png"));
        backIcon.setFitHeight(20);
        backIcon.setFitWidth(20);
        backButton.setGraphic(backIcon);

        HBox topPanel = new HBox(10, productSelector);
        root.setTop(topPanel);

        HBox bottomPanel = new HBox(backButton);
        root.setBottom(bottomPanel);

        chartAnalyzer = new ProfitChart();
        root.setCenter(chartAnalyzer.getChart());

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Profit View");
        stage.setScene(scene);
        stage.initOwner(parentStage);
        stage.show();
    }

    private void updateChart(ObservableList<SalesData> salesData) {
        String selectedProduct = productSelector.getValue();
        if (selectedProduct == null) return;

        ObservableList<SalesData> filteredData;

        if ("All".equals(selectedProduct)) {
            filteredData = salesData; // Показываем все данные
        } else {
            filteredData = FXCollections.observableArrayList(
                    salesData.filtered(data -> data.getProductName().equals(selectedProduct))
            );
        }

        // Параметр groupByMonth можно менять в зависимости от условий
        boolean groupByMonth = selectedProduct.equals("All"); // Пример: если "All", показываем данные по месяцам
        chartAnalyzer.updateChart(filteredData, groupByMonth);
    }
}
