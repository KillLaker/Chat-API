package Model;


import java.sql.*;
import java.util.Locale;

public class User {
    private String name;
    private String username;
    private String email;
    private String password;



    public User(String name, String username, String email, String password){
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;

    }

    public User(ResultSet resultSet) throws SQLException{
        this(
                resultSet.getString("name"),
                resultSet.getString("username"),
                resultSet.getString("email"),
                resultSet.getString("password")
        );
    }

    public String getUsername(){
        return this.username;
    }
    public void addUserInDB() {
        String url = "jdbc:postgresql://localhost:6543/Chat_API";
        String usernameDB = "admin";
        String passwordDB = "123";
        try(Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB)){
            String query = "INSERT INTO users (name, username, email, password) VALUES(?, ?, ?, ?)";
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setString(1, this.name);
                preparedStatement.setString(2, this.username);
                preparedStatement.setString(3, this.email);
                preparedStatement.setString(4, this.password);
                preparedStatement.executeUpdate();
                System.out.println("Adding user: " + this + " was successful");
            }

        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("ERROR: Adding user: " + this + " to the database was not successful");
        }

    }

    public void deleteUserFromDB(){
        String url = "jdbc:postgresql://localhost:6543/Chat_API";
        String usernameDB = "admin";
        String passwordDB = "123";
        try(Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB)){
            String query = "DELETE FROM users WHERE username = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setString(1, this.username);
                System.out.println("Removing user: " + this + " was successful");
            }

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("ERROR: Deleting user: " + this + " from the database was not successful");
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void updateNameInDB(String username, String newName) {

        String url = "jdbc:postgresql://localhost:6543/Chat_API";
        String usernameDB = "admin";
        String passwordDB = "123";

        try (Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB)) {
            String query = "UPDATE users SET name = ? WHERE username = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, newName);
                preparedStatement.setString(2, username);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Name updated successfully");
                } else {
                    System.out.println("Username not found or no changes made");
                }
            }
        } catch (SQLException e) {
            System.err.println("ERROR: Could not update the name" + e.getMessage());
        }
    }

    public void updateUsernameInDB(String username, String newUsername) {
        String url = "jdbc:postgresql://localhost:6543/Chat_API";
        String usernameDB = "admin";
        String passwordDB = "123";

        try (Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB)) {
            String query = "UPDATE users SET username = ? WHERE username = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, newUsername);
                preparedStatement.setString(2, username);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Username updated successfully");
                } else {
                    System.out.println("Username not found or no changes made");
                }
            }
        } catch (SQLException e) {
            System.err.println("ERROR: Could not update the username" + e.getMessage());
        }
    }


    public void updateEmailInDB(String username, String newEmail) {
        String url = "jdbc:postgresql://localhost:6543/Chat_API";
        String usernameDB = "admin";
        String passwordDB = "123";

        try (Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB)) {
            String query = "UPDATE users SET email = ? WHERE username = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, newEmail);
                preparedStatement.setString(2, username);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Email updated successfully");
                } else {
                    System.out.println("Username not found or no changes made");
                }
            }
        } catch (SQLException e) {
            System.err.println("ERROR: Could not update the email" + e.getMessage());
        }
    }

    public void updatePasswordInDB(String username, String newPassword) {
        String url = "jdbc:postgresql://localhost:6543/Chat_API";
        String usernameDB = "admin";
        String passwordDB = "123";

        try (Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB)) {
            String query = "UPDATE users SET password = ? WHERE username = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, newPassword);
                preparedStatement.setString(2, username);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Password updated successfully");
                } else {
                    System.out.println("Username not found or no changes made");
                }
            }
        } catch (SQLException e) {
            System.err.println("ERROR: Could not update the password" + e.getMessage());
        }
    }


    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword(){
        return this.password;
    }
}
