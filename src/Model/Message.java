package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class Message {
    private String content;
    private Date date;

    public Message(String content, Date date){
        this.content = content;
        this.date = date;
    }

    public Date getDate(){
        return this.date;
    }

    public void sendMessageInDB(){
        String url = "jdbc:postgresql://localhost:6543/Chat_API";
        String usernameDB = "admin";
        String passwordDB = "123";

        try (Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB)) {
            String query = "INSERT INTO messages (content, date) VALUES (?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, content);
                preparedStatement.setTimestamp(2, new java.sql.Timestamp(date.getTime()));

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Message stored in the database successfully");
                } else {
                    System.out.println("Failed to store the message in the database");
                }
            }
        } catch (SQLException e) {
            System.err.println("ERROR: Sending message was not successful" + e.getMessage());
        }
    }

    public void deleteMessageInDB() {
        String url = "jdbc:postgresql://localhost:6543/Chat_API";
        String usernameDB = "admin";
        String passwordDB = "123";

        try (Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB)) {
            String query = "DELETE FROM messages WHERE content = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, content);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Message deleted from the database successfully");
                } else {
                    System.out.println("Message not found or no changes made in the database");
                }
            }
        } catch (SQLException e) {
            System.out.println("ERROR: Deleting message was not successful" + e.getMessage());
        }
    }

    public void updateMessageInDB(String content, String newContent, Date date) {

        String url = "jdbc:postgresql://localhost:6543/Chat_API";
        String usernameDB = "admin";
        String passwordDB = "123";

        try (Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB)) {
            String query = "UPDATE messages SET content = ?, date = ? WHERE content = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, newContent);
                preparedStatement.setDate(2, (java.sql.Date) date);
                preparedStatement.setString(3, content);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Message updated in the database successfully");
                } else {
                    System.out.println("Message not found or no changes made in the database");
                }
            }
        } catch (SQLException e) {
            System.out.println("ERROR: Updating message was not successful" + e.getMessage());
        }
    }

    public String getContent() {
        return this.content;
    }


}
