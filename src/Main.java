import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/MP06_UF02_AEA1";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "rootroot";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        menuLogic(scanner);
        scanner.close();
    }

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public static void insertVehicle(String brand, String model, Integer capacity) {
        String sql = "INSERT INTO Vehicles (Marca, Model, CapacitatMaleter) VALUES (?, ?, ?)";

        try (Connection conn = connect();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, brand);
            ps.setString(2, model);
            ps.setInt(3, capacity);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Insert Vehicle Successfully");
            } else {
                System.out.println("Insert Vehicle Failed");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void getAllVehicles() {
        String sql = "SELECT * FROM Vehicles";

        try (Connection conn = connect();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String brand = rs.getString("Marca");
                String model = rs.getString("Model");
                int capacity = rs.getInt("CapacitatMaleter");

                System.out.printf("ID: %d, Brand: %s, Model: %s, Capacity: %d%n", id, brand, model, capacity);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void deleteVehicle(int id) {
        String sql = "DELETE FROM Vehicles WHERE Id = ?";

        try (Connection conn = connect();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Delete Vehicle Successfully");
            } else {
                System.out.println("Delete Vehicle Failed");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void updateVehicle(int id, String brand, String model, Integer capacity) {
        String sql = "UPDATE Vehicles SET Marca = ?, Model = ?, CapacitatMaleter = ? WHERE Id = ?";

        try (Connection conn = connect();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, brand);
            ps.setString(2, model);
            ps.setInt(3, capacity);
            ps.setInt(4, id);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Update Vehicle Successfully");
            } else {
                System.out.println("Update Vehicle Failed");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static void displayMenu() {
        System.out.println("\nSelect an operation:");
        System.out.println("1. Insert Vehicle");
        System.out.println("2. Get All Vehicles");
        System.out.println("3. Update Vehicle");
        System.out.println("4. Delete Vehicle");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void menuLogic(Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.print("Enter brand: ");
                    String brand = scanner.nextLine();
                    System.out.print("Enter model: ");
                    String model = scanner.nextLine();
                    System.out.print("Enter capacity: ");
                    int capacity = Integer.parseInt(scanner.nextLine());
                    insertVehicle(brand, model, capacity);
                    break;
                case "2":
                    getAllVehicles();
                    break;
                case "3":
                    System.out.print("Enter vehicle id to update: ");
                    int updateId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter new brand: ");
                    String newBrand = scanner.nextLine();
                    System.out.print("Enter new model: ");
                    String newModel = scanner.nextLine();
                    System.out.print("Enter new capacity: ");
                    int newCapacity = Integer.parseInt(scanner.nextLine());
                    updateVehicle(updateId, newBrand, newModel, newCapacity);
                    break;
                case "4":
                    System.out.print("Enter vehicle id to delete: ");
                    int deleteId = Integer.parseInt(scanner.nextLine());
                    deleteVehicle(deleteId);
                    break;
                case "5":
                    exit = true;
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}