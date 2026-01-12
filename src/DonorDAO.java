import java.sql.*;

public class DonorDAO {

    public static void insertDonor(Donor donor) throws SQLException {
        String sql = "INSERT INTO donors(name, email) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, donor.getName());
            stmt.setString(2, donor.getEmail());
            stmt.executeUpdate();
        }
    }

    public static void readDonors() throws SQLException {
        String sql = "SELECT * FROM donors";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getString("email")
                );
            }
        }
    }

    public static void updateDonorEmail(int id, String newEmail) throws SQLException {
        String sql = "UPDATE donors SET email=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newEmail);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    public static void deleteDonor(int id) throws SQLException {
        String sql = "DELETE FROM donors WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    public static void createTables() {
        String sqlDonors = "CREATE TABLE IF NOT EXISTS donors (" +
                "id SERIAL PRIMARY KEY, " +
                "name VARCHAR(100), " +
                "email VARCHAR(100));";

        String sqlDonations = "CREATE TABLE IF NOT EXISTS donations (" +
                "id SERIAL PRIMARY KEY, " +
                "donor_id INT REFERENCES donors(id), " +
                "amount DOUBLE PRECISION);";

        try (java.sql.Connection conn = DatabaseConnection.getConnection();
             java.sql.Statement stmt = conn.createStatement()) {
            stmt.execute(sqlDonors);
            stmt.execute(sqlDonations);
            System.out.println("Таблицы успешно созданы или уже существуют.");
        } catch (java.sql.SQLException e) {
            System.out.println("Ошибка при создании таблиц: " + e.getMessage());
        }
    }
}
