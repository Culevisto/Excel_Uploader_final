module com.example.excelanalyzer {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.ooxml;
    requires java.base;
    requires org.apache.commons.compress;

    opens com.example.excelanalyzer to javafx.fxml;
    exports com.example.excelanalyzer;
}
