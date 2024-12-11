public class User {
    private int id;
    private String username;
    private String password;
    private String registerDate;
    private String expireDate;

    // Constructors
    public User(int id, String username, String password, String registerDate, String expireDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.registerDate = registerDate;
        this.expireDate = expireDate;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRegisterDate() { return registerDate; }
    public void setRegisterDate(String registerDate) { this.registerDate = registerDate; }

    public String getExpireDate() { return expireDate; }
    public void setExpireDate(String expireDate) { this.expireDate = expireDate; }
}
