package com.example.excelanalyzer;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    public List<SalesData> readExcelFile(String filePath) throws IOException {
        List<SalesData> salesDataList = new ArrayList<>();
        FileInputStream fis = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {

            if (row.getRowNum() == 0) continue;

            try {
                int id = (int) getNumericCellValue(row.getCell(0));
                String productName = getStringCellValue(row.getCell(1));
                double price = getNumericCellValue(row.getCell(2));
                int quantity = (int) getNumericCellValue(row.getCell(3));
                double totalSale = getNumericCellValue(row.getCell(4));
                LocalDate saleDate = getDateCellValue(row.getCell(5));

                SalesData salesData = new SalesData(id, productName, price, quantity, totalSale, saleDate);
                salesDataList.add(salesData);
            } catch (Exception e) {
                System.err.println("Error reading for line " + row.getRowNum() + ": " + e.getMessage());
            }
        }

        workbook.close();
        fis.close();
        return salesDataList;
    }

    private String getStringCellValue(Cell cell) {
        if (cell == null) return "";
        return cell.getCellType() == CellType.STRING ? cell.getStringCellValue() : "";
    }

    private double getNumericCellValue(Cell cell) {
        if (cell == null) return 0;
        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            try {
                return Double.parseDouble(cell.getStringCellValue());
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        return 0;
    }

    private LocalDate getDateCellValue(Cell cell) {
        if (cell == null || cell.getCellType() != CellType.NUMERIC || !DateUtil.isCellDateFormatted(cell)) {
            return LocalDate.now();
        }
        return cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
