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

    public void createUser(String username, String password) {
        try {
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
        } catch (SQLException e) {
            System.err.println("Error creating user: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void readUsers() {
        try {
            String query = "SELECT * FROM Users";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("ID") + ", Username: " + rs.getString("Username") +
                        ", RegisterDate: " + rs.getString("RegisterDate") + ", ExpireDate: " + rs.getString("ExpireDate"));
            }
        } catch (SQLException e) {
            System.err.println("Error reading users: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateUser(int id, String newPassword) {
        try {
            String query = "UPDATE Users SET Password = ? WHERE ID = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, newPassword);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            System.out.println("Password updated successfully.");
        } catch (SQLException e) {
            System.err.println("Error updating user: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        try {
            String query = "DELETE FROM Users WHERE ID = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("User deleted successfully.");
        } catch (SQLException e) {
            System.err.println("Error deleting user: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void checkExpiredAccounts() {
        try {
            String today = java.time.LocalDate.now().toString();
            String query = "SELECT * FROM Users WHERE ExpireDate < ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, today);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Expired Account - ID: " + rs.getInt("ID") + ", Username: " + rs.getString("Username"));
            }
        } catch (SQLException e) {
            System.err.println("Error checking expired accounts: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed successfully.");
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
