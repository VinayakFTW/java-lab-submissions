import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.*;

public class RestaurantJavaFXApp extends Application {

    static final String URL = "jdbc:mysql://localhost:3306/RestaurantDB?useSSL=false&allowPublicKeyRetrieval=true";
    static final String USER = "root";
    static final String PASS = "ads123";

    Connection con;
    TextArea output;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        con = DriverManager.getConnection(URL, USER, PASS);

        BorderPane root = new BorderPane();

        // ---------------- HEADER ----------------
        Label titleLabel = new Label("Restaurant Management DB");
        titleLabel.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: #ecf0f1;");
        HBox header = new HBox(titleLabel);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(20));
        header.setStyle("-fx-background-color: #2c3e50; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 0, 4);");
        root.setTop(header);

        // ---------------- SIDEBAR ----------------
        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #34495e;");
        sidebar.setPrefWidth(240);

        String catStyle = "-fx-text-fill: #bdc3c7; -fx-font-size: 13px; -fx-font-weight: bold;";
        String btnStyle = "-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-pref-width: 200px; -fx-cursor: hand;";
        String btnStyleDanger = "-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-pref-width: 200px; -fx-cursor: hand;";
        String btnStyleWarning = "-fx-background-color: #f39c12; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-pref-width: 200px; -fx-cursor: hand;";

        Label insertCat = new Label("MANAGE DATA");
        insertCat.setStyle(catStyle);
        Button addRestBtn = new Button("Add Restaurant..."); addRestBtn.setStyle(btnStyle);
        addRestBtn.setOnAction(e -> insertRestaurant());
        Button addItemBtn = new Button("Add Menu Item..."); addItemBtn.setStyle(btnStyle);
        addItemBtn.setOnAction(e -> insertMenuItem());

        Label selectCat = new Label("QUERIES");
        selectCat.setStyle(catStyle);
        Button viewAllBtn = new Button("View All Items"); viewAllBtn.setStyle(btnStyle);
        viewAllBtn.setOnAction(e -> selectAll());
        Button priceBtn = new Button("Price <= 100"); priceBtn.setStyle(btnStyle);
        priceBtn.setOnAction(e -> selectPriceLessThan100());
        Button cafeBtn = new Button("Cafe Java Items"); cafeBtn.setStyle(btnStyle);
        cafeBtn.setOnAction(e -> selectCafeJava());

        Label updateCat = new Label("BULK UPDATE");
        updateCat.setStyle(catStyle);
        Button updateBtn = new Button("Raise Price <=100"); updateBtn.setStyle(btnStyleWarning);
        updateBtn.setOnAction(e -> updatePrice());

        Label deleteCat = new Label("CLEANUP");
        deleteCat.setStyle(catStyle);
        Button deleteBtn = new Button("Delete 'P' Items"); deleteBtn.setStyle(btnStyleDanger);
        deleteBtn.setOnAction(e -> deleteItems());

        sidebar.getChildren().addAll(
                insertCat, addRestBtn, addItemBtn,
                selectCat, viewAllBtn, priceBtn, cafeBtn,
                updateCat, updateBtn,
                deleteCat, deleteBtn
        );
        root.setLeft(sidebar);

        // ---------------- CENTER OUTPUT ----------------
        output = new TextArea();
        output.setEditable(false);
        output.setFont(Font.font("Consolas", 15));
        output.setStyle("-fx-control-inner-background: #1e1e1e; -fx-text-fill: #a9b7c6;");
        
        VBox centerArea = new VBox(10);
        centerArea.setPadding(new Insets(20));
        centerArea.setStyle("-fx-background-color: #ecf0f1;");
        
        Label outTitle = new Label("Output & Results:");
        outTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        
        VBox.setVgrow(output, Priority.ALWAYS);
        centerArea.getChildren().addAll(outTitle, output);
        
        root.setCenter(centerArea);

        Scene scene = new Scene(root, 900, 650);
        stage.setScene(scene);
        stage.setTitle("Restaurant DB Administrator");
        stage.show();
    }

    // ---------------- INSERT RESTAURANT ----------------
    void insertRestaurant() {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO Restaurant(Name, Address) VALUES ('NewRes', 'Mumbai')");
            printSuccess("Restaurant 'NewRes' Inserted Successfully!");
        } catch (Exception e) {
            printError(e.getMessage());
        }
    }

    // ---------------- INSERT MENU ITEM ----------------
    void insertMenuItem() {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO MenuItem(Name, Price, ResId) VALUES ('NewItem', 120, 1)");
            printSuccess("Menu Item 'NewItem' Inserted Successfully!");
        } catch (Exception e) {
            printError(e.getMessage());
        }
    }

    // ---------------- SELECT ALL ----------------
    void selectAll() {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM MenuItem");
            output.setText(formatResult(rs));
        } catch (Exception e) {
            printError(e.getMessage());
        }
    }

    // ---------------- SELECT PRICE <=100 ----------------
    void selectPriceLessThan100() {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM MenuItem WHERE Price <=100");
            output.setText(formatResult(rs));
        } catch (Exception e) {
            printError(e.getMessage());
        }
    }

    // ---------------- SELECT CAFE JAVA ----------------
    void selectCafeJava() {
        try {
            Statement stmt = con.createStatement();
            String query = "SELECT m.* FROM MenuItem m " +
                    "JOIN Restaurant r ON m.ResId = r.Id " +
                    "WHERE r.Name = 'Cafe Java'";
            ResultSet rs = stmt.executeQuery(query);
            output.setText(formatResult(rs));
        } catch (Exception e) {
            printError(e.getMessage());
        }
    }

    // ---------------- UPDATE ----------------
    void updatePrice() {
        try {
            Statement stmt = con.createStatement();
            int rows = stmt.executeUpdate("UPDATE MenuItem SET Price = 200 WHERE Price <=100");
            printSuccess("Prices Updated! Rows affected: " + rows);
        } catch (Exception e) {
            printError(e.getMessage());
        }
    }

    // ---------------- DELETE ----------------
    void deleteItems() {
        try {
            Statement stmt = con.createStatement();
            int rows = stmt.executeUpdate("DELETE FROM MenuItem WHERE Name LIKE 'P%'");
            printSuccess("Items starting with 'P' deleted! Rows affected: " + rows);
        } catch (Exception e) {
            printError(e.getMessage());
        }
    }

    // ---------------- HELPER METHODS ----------------
    void printSuccess(String msg) {
        output.setText("✅ SUCCESS:\n" + msg);
    }
    
    void printError(String msg) {
        output.setText("❌ ERROR:\n" + msg);
    }

    // ---------------- FORMAT RESULT ----------------
    String formatResult(ResultSet rs) throws SQLException {
        StringBuilder sb = new StringBuilder();

        // Fixed width formatting for clean output
        sb.append(String.format("%-5s | %-20s | %-10s | %-5s\n", "ID", "Name", "Price", "ResId"));
        sb.append("----------------------------------------------------------\n");

        int count = 0;
        while (rs.next()) {
            sb.append(String.format("%-5d | %-20s | $%-9.2f | %-5d\n", 
                    rs.getInt("Id"), 
                    rs.getString("Name"), 
                    rs.getDouble("Price"), 
                    rs.getInt("ResId")));
            count++;
        }
        sb.append("----------------------------------------------------------\n");
        sb.append(count).append(" row(s) returned.\n");

        return sb.toString();
    }
}