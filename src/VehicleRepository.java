import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepository {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/mp06_uf02_aea1";
    private static final String DB_USER = "joacoguzman";
    private static final String DB_PASSWORD = "";

    public void crear(Vehicle vehicle) {
        String sql = "INSERT INTO vehicles (marca, model, capacitat_maleter) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, vehicle.getMarca());
            ps.setString(2, vehicle.getModel());
            ps.setInt(3, vehicle.getCapacitat_maleter());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Vehicle> listarTodos() {
        List<Vehicle> vehiclesList = new ArrayList<>();
        String sql = "SELECT * FROM vehicles";
        try (
                Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                ) {
            while (rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(rs.getInt("id"));
                vehicle.setMarca(rs.getString("marca"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setCapacitat_maleter(rs.getInt("capacitat_maleter"));
                vehiclesList.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehiclesList;
    }

    public void actualitzar(Vehicle vehicle) {
        String sql = "UPDATE vehicles SET marca = ?, model = ?, capacitat_maleter = ? WHERE id = ?";
        try (
                Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement ps = conn.prepareStatement(sql);
                ) {
            ps.setString(1, vehicle.getMarca());
            ps.setString(2, vehicle.getModel());
            ps.setInt(3, vehicle.getCapacitat_maleter());
            ps.setInt(4, vehicle.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM vehicles WHERE id = ?";
        try (
                Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement ps = conn.prepareStatement(sql);
                ) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
