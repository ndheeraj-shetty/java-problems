schema.sql 
CREATE DATABASE IF NOT EXISTS FoodDB; 
USE FoodDB; 
CREATE TABLE IF NOT EXISTS Restaurant ( 
Id INT PRIMARY KEY AUTO_INCREMENT, 
Name VARCHAR(100), 
Address VARCHAR(200) 
); 
CREATE TABLE IF NOT EXISTS MenuItem ( 
Id INT PRIMARY KEY AUTO_INCREMENT, 
Name VARCHAR(100), 
Price INT, 
ResId INT, 
FOREIGN KEY (ResId) REFERENCES Restaurant(Id) 
); 
DatabaseConnection.java 
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 
public class DatabaseConnection { 
private static final String URL = "jdbc:mysql://localhost:3306/FoodDB"; 
private static final String USER = "root"; // change to your MySQL username 
private static final String PASSWORD = "your_password"; // change to your MySQL 
password 
public static Connection getConnection() throws SQLException { 
return DriverManager.getConnection(URL, USER, PASSWORD); 
} 
} 
RestaurantMenuApp.java 
import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.control.*; 
import javafx.scene.layout.VBox; 
import javafx.stage.Stage; 
import java.sql.*; 
// JavaFX app to perform CRUD on Restaurant & MenuItem tables 
public class RestaurantMenuApp extends Application { 
private TextArea outputArea; // area to show results/errors 
@Override 
public void start(Stage primaryStage) { 
outputArea = new TextArea(); 
outputArea.setEditable(false); 
// Buttons for CRUD 
Button insertBtn = new Button("Insert Records"); 
Button selectBtn = new Button("Select Records"); 
Button updateBtn = new Button("Update Records"); 
Button deleteBtn = new Button("Delete Records"); 
// Event handlers 
insertBtn.setOnAction(e -> insertRecords()); 
selectBtn.setOnAction(e -> selectRecords()); 
updateBtn.setOnAction(e -> updateRecords()); 
deleteBtn.setOnAction(e -> deleteRecords()); 
// Layout 
VBox vbox = new VBox(10, insertBtn, selectBtn, updateBtn, deleteBtn, outputArea); 
Scene scene = new Scene(vbox, 600, 400); 
primaryStage.setTitle("Restaurant & MenuItem CRUD"); 
primaryStage.setScene(scene); 
primaryStage.show(); 
} 
// Insert sample records 
private void insertRecords() { 
try (Connection conn = DatabaseConnection.getConnection(); 
Statement stmt = conn.createStatement()) { 
stmt.executeUpdate("DELETE FROM MenuItem"); 
stmt.executeUpdate("DELETE FROM Restaurant"); 
// Restaurants 
for (int i = 1; i <= 10; i++) { 
stmt.executeUpdate("INSERT INTO Restaurant (Name, Address) VALUES 
('Restaurant" + i + "', 'Address" + i + "')"); 
} 
stmt.executeUpdate("INSERT INTO Restaurant (Name, Address) VALUES ('Cafe 
Java', 'Main Street')"); 
// MenuItems 
for (int i = 1; i <= 10; i++) { 
stmt.executeUpdate("INSERT INTO MenuItem (Name, Price, ResId) VALUES 
('Item" + i + "', " + (i * 50) + ", " + i + ")"); 
} 
stmt.executeUpdate("INSERT INTO MenuItem (Name, Price, ResId) VALUES 
('Pasta Special', 80, (SELECT Id FROM Restaurant WHERE Name='Cafe Java'))"); 
outputArea.setText("Inserted 11 Restaurants and 11 MenuItems successfully.\n"); 
} catch (SQLSyntaxErrorException e) { 
outputArea.setText("SQL syntax error: " + e.getMessage()); 
} catch (SQLIntegrityConstraintViolationException e) { 
outputArea.setText("Constraint violation: " + e.getMessage()); 
} catch (SQLNonTransientConnectionException e) { 
outputArea.setText("Database connection error: " + e.getMessage()); 
} catch (SQLException e) { 
outputArea.setText("General SQL exception: " + e.getMessage()); 
} catch (Exception e) { 
outputArea.setText("Unexpected error: " + e.getMessage()); 
} 
} 
// Select records 
private void selectRecords() { 
try (Connection conn = DatabaseConnection.getConnection(); 
Statement stmt = conn.createStatement()) { 
outputArea.clear(); 
outputArea.appendText("MenuItems with price <= 100:\n"); 
ResultSet rs1 = stmt.executeQuery("SELECT * FROM MenuItem WHERE Price <= 
100"); 
printResultSet(rs1); 
outputArea.appendText("\nMenuItems from Cafe Java:\n"); 
ResultSet rs2 = stmt.executeQuery("SELECT m.* FROM MenuItem m JOIN 
Restaurant r ON m.ResId = r.Id WHERE r.Name = 'Cafe Java'"); 
printResultSet(rs2); 
} catch (SQLException ex) { 
outputArea.setText("Error selecting records: " + ex.getMessage()); 
} 
} 
// Update records 
private void updateRecords() { 
try (Connection conn = DatabaseConnection.getConnection(); 
Statement stmt = conn.createStatement()) { 
stmt.executeUpdate("UPDATE MenuItem SET Price = 200 WHERE Price <= 100"); 
outputArea.setText("Updated MenuItems where price <= 100 to 200.\n"); 
ResultSet rs = stmt.executeQuery("SELECT * FROM MenuItem"); 
printResultSet(rs); 
} catch (SQLException ex) { 
outputArea.setText("Error updating records: " + ex.getMessage()); 
} 
} 
// Delete records 
private void deleteRecords() { 
try (Connection conn = DatabaseConnection.getConnection(); 
Statement stmt = conn.createStatement()) { 
stmt.executeUpdate("DELETE FROM MenuItem WHERE Name LIKE 'P%'"); 
outputArea.setText("Deleted MenuItems where name starts with 'P'.\n"); 
ResultSet rs = stmt.executeQuery("SELECT * FROM MenuItem"); 
printResultSet(rs); 
} catch (SQLException ex) { 
outputArea.setText("Error deleting records: " + ex.getMessage()); 
} 
} 
// Print ResultSet rows 
private void printResultSet(ResultSet rs) throws SQLException { 
ResultSetMetaData meta = rs.getMetaData(); 
int columnCount = meta.getColumnCount(); 
while (rs.next()) { 
for (int i = 1; i <= columnCount; i++) { 
outputArea.appendText(rs.getString(i) + "\t"); 
} 
outputArea.appendText("\n"); 
} 
outputArea.appendText("--------------------------------------------------\n"); 
} 
public static void main(String[] args) { 
launch(args); 
} 
}