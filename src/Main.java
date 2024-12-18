import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            UserManager userManager = new UserManager();
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n========================");
                System.out.println("1. Create User\n2. View Users\n3. Update User\n4. Delete User\n5. Check Expired Accounts\n6. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter username: ");
                        String username = scanner.next();
                        System.out.print("Enter password: ");
                        String password = scanner.next();
                        userManager.createUser(username, password);
                        break;
                    case 2:
                        userManager.readUsers();
                        break;
                    case 3:
                        System.out.print("Enter user ID: ");
                        int id = scanner.nextInt();
                        System.out.print("Enter new password: ");
                        String newPassword = scanner.next();
                        userManager.updateUser(id, newPassword);
                        break;
                    case 4:
                        System.out.print("Enter user ID: ");
                        int deleteId = scanner.nextInt();
                        userManager.deleteUser(deleteId);
                        break;
                    case 5:
                        userManager.checkExpiredAccounts();
                        break;
                    case 6:
                        userManager.closeConnection();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
