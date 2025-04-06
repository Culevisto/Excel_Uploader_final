Excel_Reader_beta 📊

Excel_Reader_beta is a Java application using JavaFX for analyzing and visualizing sales data from Excel files. The app allows users to load data, filter by product, and display sales trends in charts.
Features ✨

    Load data from Excel 📥: Supports .xlsx files. The app allows you to load sales data including product name, price, quantity, total sales, and date.

    Data analysis 📈: Users can select a specific product and analyze its sales on a chart showing the sales trend over time.

    Interactive charts 📉: The app displays charts for selected products with the ability to dynamically update the data based on user selection.

    Simple and intuitive interface 🖥️: All actions, such as file upload, product selection for analysis, and chart viewing, are easily accessible via a clear interface with buttons and dropdown menus.

Excel File Format 📂

To ensure the app works properly, please make sure your Excel file has the following columns in the specified order:

    Product ID — a unique product identifier.

    Product Name — the name of the product.

    Price — the price of the product.

    Quantity — the number of units sold.

    Total Sale — total sales (price * quantity).

    Sale Date — the date the product was sold (format: dd.MM.yyyy).

Example:
ID	Product Name	Price	Quantity	Total Sale	Sale Date
1	Product A	100	10	1000	01.01.2025
2	Product B	150	5	750	02.01.2025
3	Product C	200	20	4000	03.01.2025
Technologies 🛠️

    Java — main programming language.

    JavaFX — for building the graphical user interface.

    Apache POI — for working with Excel files.

How to Run 🚀

    Clone the repository.

    Build the project in your IDE.

    Run MainApp.java to launch the application.

    Select an Excel file with sales data.

    Select a product and analyze its sales on the chart.