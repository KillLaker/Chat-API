package Service;

import Event.MessageEvent;
import Model.Message;

import java.sql.*;
import java.time.LocalDate;

public class MessageService {
    private final EventBus eventBus;

    public MessageService(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void sendMessage(String content) {
        Message message = new Message(content, Date.valueOf(LocalDate.now()));
        message.sendMessageInDB();

        MessageEvent event = new MessageEvent(MessageEvent.EventType.NEW_MESSAGE, message);
        eventBus.publish(event);
    }

    public void deleteMessage(String content) {
        Message message = findMessage(content);
        if (message != null) {
            message.deleteMessageInDB();
            MessageEvent event = new MessageEvent(MessageEvent.EventType.DELETED_MESSAGE, message);
            eventBus.publish(event);
        }
    }

    public void editMessage(String content, String newContent) {
        Message message = findMessage(content);
        if (message != null) {
            message.updateMessageInDB(content, newContent, message.getDate());
            MessageEvent event = new MessageEvent(MessageEvent.EventType.EDITED_MESSAGE, message);
            eventBus.publish(event);
        }
    }


    private Message findMessage(String content) {
        String url = "jdbc:postgresql://localhost:6543/Chat_API";
        String usernameDB = "admin";
        String passwordDB = "123";

        try (Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB)) {
            String query = "SELECT * FROM messages WHERE content = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, content);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String retrievedContent = resultSet.getString("content");
                        Date retrievedDate = resultSet.getDate("date");
                        return new Message(retrievedContent, retrievedDate);
                    } else {
                        System.out.println("Message not found in the database");
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            e.getStackTrace();
            System.out.println("ERROR: Finding message was not successful");
            return null;
        }
    }

}










