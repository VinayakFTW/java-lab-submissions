import java.sql.*;

public class RestaurantJDBC_Final {

    static final String URL = "jdbc:mysql://localhost:3306/RestaurantDB?useSSL=false";
    static final String USER = "root";
    static final String PASS = "ads123";

    public static void main(String[] args) {

        try {
            Connection con = DriverManager.getConnection(URL, USER, PASS);

            insertRestaurants(con);
            insertMenuItems(con);

            selectPriceLessThan100(con);
            selectFromCafeJava(con);

            updatePrice(con);
            deleteItemsStartingWithP(con);

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------- INSERT RESTAURANTS ----------------
    public static void insertRestaurants(Connection con) throws SQLException {
        Statement stmt = con.createStatement();

        for (int i = 1; i <= 10; i++) {
            String sql = "INSERT INTO Restaurant(Name, Address) VALUES ('Restaurant" + i + "', 'Address" + i + "')";
            stmt.executeUpdate(sql);
        }

        // Special restaurant
        stmt.executeUpdate("INSERT INTO Restaurant(Name, Address) VALUES ('Cafe Java', 'Pune')");

        System.out.println("Restaurants Inserted!");
    }

    // ---------------- INSERT MENU ITEMS ----------------
    public static void insertMenuItems(Connection con) throws SQLException {
        Statement stmt = con.createStatement();

        String[] items = {
                "Pizza", "Pasta", "Burger", "Fries", "Sandwich",
                "Coffee", "Tea", "Noodles", "Soup", "Cake"
        };

        double[] prices = {
                150, 120, 90, 80, 70,
                60, 40, 110, 95, 130
        };

        for (int i = 0; i < items.length; i++) {
            String sql = "INSERT INTO MenuItem(Name, Price, ResId) VALUES ('"
                    + items[i] + "', " + prices[i] + ", " + ((i % 10) + 1) + ")";
            stmt.executeUpdate(sql);
        }

        // Special item for Cafe Java
        stmt.executeUpdate("INSERT INTO MenuItem(Name, Price, ResId) VALUES ('Espresso', 85, 11)");

        System.out.println("Menu Items Inserted!");
    }

    // ---------------- SELECT (Price <=100) ----------------
    public static void selectPriceLessThan100(Connection con) throws SQLException {
        Statement stmt = con.createStatement();

        System.out.println("\nMenu Items with Price <= 100:");
        ResultSet rs = stmt.executeQuery("SELECT * FROM MenuItem WHERE Price <= 100");

        printTable(rs);
    }

    // ---------------- SELECT (Cafe Java) ----------------
    public static void selectFromCafeJava(Connection con) throws SQLException {
        Statement stmt = con.createStatement();

        System.out.println("\nMenu Items from Cafe Java:");

        String query = "SELECT m.* FROM MenuItem m " +
                "JOIN Restaurant r ON m.ResId = r.Id " +
                "WHERE r.Name = 'Cafe Java'";

        ResultSet rs = stmt.executeQuery(query);
        printTable(rs);
    }

    // ---------------- UPDATE ----------------
    public static void updatePrice(Connection con) throws SQLException {
        Statement stmt = con.createStatement();

        System.out.println("\nUpdating Price <=100 to 200...");
        stmt.executeUpdate("UPDATE MenuItem SET Price = 200 WHERE Price <= 100");

        ResultSet rs = stmt.executeQuery("SELECT * FROM MenuItem");
        printTable(rs);
    }

    // ---------------- DELETE ----------------
    public static void deleteItemsStartingWithP(Connection con) throws SQLException {
        Statement stmt = con.createStatement();

        System.out.println("\nDeleting items starting with 'P'...");
        stmt.executeUpdate("DELETE FROM MenuItem WHERE Name LIKE 'P%'");

        ResultSet rs = stmt.executeQuery("SELECT * FROM MenuItem");
        printTable(rs);
    }

    // ---------------- PRINT TABLE ----------------
    public static void printTable(ResultSet rs) throws SQLException {

        System.out.println("---------------------------------------------------");
        System.out.printf("%-5s %-15s %-10s %-5s\n", "ID", "Name", "Price", "ResId");
        System.out.println("---------------------------------------------------");

        while (rs.next()) {
            System.out.printf("%-5d %-15s %-10.2f %-5d\n",
                    rs.getInt("Id"),
                    rs.getString("Name"),
                    rs.getDouble("Price"),
                    rs.getInt("ResId"));
        }

        System.out.println("---------------------------------------------------");
    }
}