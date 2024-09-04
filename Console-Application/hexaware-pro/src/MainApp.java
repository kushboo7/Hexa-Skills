package src;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class MainApp {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Main Menu:");
            System.out.println("1. Candidate");
            System.out.println("2. Trainer");
            System.out.println("3. Exit");

            int choice = getIntInput("Choose an option (1,2,3): ");

            switch (choice) {
                case 1:
                    handleCandidateMenu();
                    break;
                case 2:
                    handleTrainerMenu();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleCandidateMenu() {
        while (true) {
            System.out.println("Candidate Menu:");
            System.out.println("1. Sign Up");
            System.out.println("2. Sign In");
            System.out.println("3. Back");

            int choice = getIntInput("Choose an option (1-3): ");

            switch (choice) {
                case 1:
                    signUp();
                    break;
                case 2:
                    signIn();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleTrainerMenu() {
        while (true) {
            System.out.println("Trainer Menu:");
            System.out.println("1. Sign In");
            System.out.println("2. Back");

            int choice = getIntInput("Choose an option (1-2): ");

            switch (choice) {
                case 1:
                    signInTrainer();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void signUp() {
        System.out.println("Candidate Sign Up:");

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        System.out.print("Enter Degree: ");
        String degree = scanner.nextLine();

        System.out.print("Enter Specialization: ");
        String specialization = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter LinkedIn Profile URL: ");
        String linkedinProfile = scanner.nextLine();

        System.out.print("Enter GitHub Profile URL: ");
        String githubProfile = scanner.nextLine();

        System.out.print("Enter Programming Languages Known (comma-separated): ");
        String programmingLanguages = scanner.nextLine();

        System.out.print("Enter Certifications (JSON format): ");
        String certifications = scanner.nextLine();

        System.out.print("Enter Internships (JSON format): ");
        String internships = scanner.nextLine();

        System.out.print("Enter Courses (JSON format): ");
        String courses = scanner.nextLine();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String insertCandidateSQL = "INSERT INTO candidates (name, email, password, degree, specialization, phone_number, linkedin_profile, github_profile, programming_languages, certifications, internships, courses) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertCandidateSQL)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, password);
                preparedStatement.setString(4, degree);
                preparedStatement.setString(5, specialization);
                preparedStatement.setString(6, phoneNumber);
                preparedStatement.setString(7, linkedinProfile);
                preparedStatement.setString(8, githubProfile);
                preparedStatement.setString(9, programmingLanguages);
                preparedStatement.setString(10, certifications);
                preparedStatement.setString(11, internships);
                preparedStatement.setString(12, courses);
                preparedStatement.executeUpdate();
                System.out.println("Sign up successful!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void signIn() {
        System.out.println("Candidate Sign In:");
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT id, name, email, certifications FROM candidates WHERE email = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    int candidateId = resultSet.getInt("id");
                    String candidateName = resultSet.getString("name");
                    String candidateEmail = resultSet.getString("email");
                    String certifications = resultSet.getString("certifications");

                    System.out.println("Sign-in successful! Welcome, " + candidateName + ".");
                    recommendBatch(certifications, candidateId, candidateName, candidateEmail);
                } else {
                    System.out.println("Invalid email or password. Please try again.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error during sign-in: " + e.getMessage());
        }
    }

    private static void recommendBatch(String certifications, int candidateId, String candidateName, String candidateEmail) {
        String recommendedBatch = "";

        if (certifications.contains("aws") || certifications.contains("java")) {
            recommendedBatch = "Java Batch";
        } else if (certifications.contains("azure") || certifications.contains(".net")) {
            recommendedBatch = ".NET Batch";
        } else if (certifications.contains("python")) {
            recommendedBatch = "Data Engineering Batch";
        }

        if (!recommendedBatch.isEmpty()) {
            System.out.println("Based on your certifications, we recommend you join the " + recommendedBatch + ".");
            System.out.println("Please choose one of the following options:");
            System.out.println("1. Java Batch");
            System.out.println("2. .NET Batch");
            System.out.println("3. Data Engineering Batch");

            int choice = getIntInput("Choose an option (1-3): ");
            String batchOptionSelected = "";

            switch (choice) {
                case 1:
                    batchOptionSelected = "Java Batch";
                    break;
                case 2:
                    batchOptionSelected = ".NET Batch";
                    break;
                case 3:
                    batchOptionSelected = "Data Engineering Batch";
                    break;
                default:
                    System.out.println("Invalid choice. No batch selected.");
                    return;
            }

            insertBatch(batchOptionSelected, candidateId, candidateName, candidateEmail);
        } else {
            System.out.println("No batch recommendation available based on your certifications.");
        }
    }

    private static void insertBatch(String batchOptionSelected, int candidateId, String candidateName, String candidateEmail) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String insertBatchSQL = "INSERT INTO batch (batch_option_selected, candidate_id, candidate_email, candidate_name) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertBatchSQL)) {
                preparedStatement.setString(1, batchOptionSelected);
                preparedStatement.setInt(2, candidateId);
                preparedStatement.setString(3, candidateEmail);
                preparedStatement.setString(4, candidateName);
                preparedStatement.executeUpdate();
                System.out.println("Batch allocation successful!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static boolean signInTrainer() {
        System.out.println("Trainer Sign In: reference email:trainer1@gmail.com    &&  password :trainer");
        System.out.print("Enter Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine().trim();
    
        // Assuming you have a table for trainers and only one table needs to be checked
        String sql = "SELECT * FROM trainer WHERE email = ? AND password = ?";
    
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
    
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
    
            if (resultSet.next()) {
                System.out.println("Sign-in successful! Welcome, Trainer.");
                displayTrainerBatchOptions();
                return true; // Sign-in successful
            } else {
                System.out.println("Invalid email or password. Please try again.");
                return false; // Sign-in failed
            }
        } catch (SQLException e) {
            System.out.println("Error during sign-in: " + e.getMessage());
            return false; // Sign-in failed due to an error
        }
    }
    
    private static void displayTrainerBatchOptions() {
        while (true) {
            System.out.println("\nTrainer Batch Options:");
            System.out.println("1. Java Batch");
            System.out.println("2. .NET Batch");
            System.out.println("3. Data Engineering Batch (Python)");
            System.out.println("4. Back");
    
            int choice = getIntInput("Choose an option (1-4): ");
    
            String batchName = null;
            switch (choice) {
                case 1:
                    batchName = "Java Batch";
                    break;
                case 2:
                    batchName = ".NET Batch";
                    break;
                case 3:
                    batchName = "Data Engineering Batch (Python)";
                    break;
                case 4:
                    return; // Go back to the previous menu
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue;
            }
    
            // Fetch and display the details for the selected batch
            fetchAndDisplayBatchDetails(batchName);
        }
    }
    
    private static void fetchAndDisplayBatchDetails(String batchName) {
        String sql = "SELECT b.id, c.name, c.email FROM batch b JOIN candidates c ON b.candidate_id = c.id WHERE b.batch_option_selected = ?";
    
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, batchName);
            ResultSet resultSet = statement.executeQuery();
    
            System.out.println("\nStudents enrolled in " + batchName + ":");
            if (!resultSet.isBeforeFirst()) { // Check if resultSet is empty
                System.out.println("No students found in this batch.");
            } else {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
    
                    System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching batch details: " + e.getMessage());
        }
    }
    
    
    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next(); // Clear invalid input
            System.out.print(prompt);
        }
        int number = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character after the number input
        return number;
    }
    

    // Database Connection
    public static class DatabaseConnection {
        private static final String URL = "jdbc:mysql://localhost:3306/skill_navigator";
        private static final String USER = "root";
        private static final String PASSWORD = "";

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }
}
