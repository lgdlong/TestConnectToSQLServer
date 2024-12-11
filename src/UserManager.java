import java.sql.*;

public class UserManager {
    private final Connection connection;

    public UserManager() throws SQLException {
        // Establish database connection
        String url = "jdbc:sqlserver://35.223.19.28:1433;databaseName=AccountManagement;trustServerCertificate=true";
        String user = "sqlserver";  // SQL Server username
        String password = "12345678";
        connection = DriverManager.getConnection(url, user, password);
    }

    public void createUser(String username, String password) throws SQLException {
        String registerDate = java.time.LocalDate.now().toString();
        String expireDate = java.time.LocalDate.now().plusMonths(1).toString(); // 1-month validity
        String query = "INSERT INTO Users (Username, Password, RegisterDate, ExpireDate) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, username);
        stmt.setString(2, password);
        stmt.setString(3, registerDate);
        stmt.setString(4, expireDate);
        stmt.executeUpdate();
        System.out.println("User created successfully.");
    }

    public void readUsers() throws SQLException {
        String query = "SELECT * FROM Users";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("ID") + ", Username: " + rs.getString("Username") +
                    ", RegisterDate: " + rs.getString("RegisterDate") + ", ExpireDate: " + rs.getString("ExpireDate"));
        }
    }

    public void updateUser(int id, String newPassword) throws SQLException {
        String query = "UPDATE Users SET Password = ? WHERE ID = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, newPassword);
        stmt.setInt(2, id);
        stmt.executeUpdate();
        System.out.println("Password updated successfully.");
    }

    public void deleteUser(int id) throws SQLException {
        String query = "DELETE FROM Users WHERE ID = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        System.out.println("User deleted successfully.");
    }

    public void checkExpiredAccounts() throws SQLException {
        String today = java.time.LocalDate.now().toString();
        String query = "SELECT * FROM Users WHERE ExpireDate < ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, today);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            System.out.println("Expired Account - ID: " + rs.getInt("ID") + ", Username: " + rs.getString("Username"));
        }
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    // Fixed test method
    public void test() throws SQLException {
        // Print debug info to verify connection
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT DB_NAME() AS CurrentDatabase");
        if (rs.next()) {
            System.out.println("Connected to database: " + rs.getString("CurrentDatabase"));
        }

        // Execute INSERT query
        System.out.println("Executing query: INSERT INTO Users (Username, Password, RegisterDate, ExpireDate) VALUES ('john_doe', 'securepassword123', '2024-12-11', '2025-01-11');");
        int rowsAffected = stmt.executeUpdate(
                "INSERT INTO Users (Username, Password, RegisterDate, ExpireDate) " +
                        "VALUES ('john_doe', 'securepassword123', '2024-12-11', '2025-01-11');"
        );
        System.out.println("Rows affected: " + rowsAffected);
    }
}