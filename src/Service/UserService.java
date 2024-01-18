package Service;

import Event.UserEvent;
import Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final EventBus eventBus;

    public UserService(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void createUser(String name, String username, String email, String password) {
        User user = new User(name, username, email, password);
        user.addUserInDB();

        UserEvent event = new UserEvent(UserEvent.EventType.USER_CREATED, user);
        eventBus.publish(event);
    }

    public void deleteUser(String name, String username, String email, String password) {
        User user = new User(name, username, email, password);
        user.deleteUserFromDB();

        UserEvent event = new UserEvent(UserEvent.EventType.USER_DELETED, user);
        eventBus.publish(event);
    }

    public void updateName(String username, String newName) {
        User user = getUser(username);
        user.updateNameInDB(username, newName);

        UserEvent event = new UserEvent(UserEvent.EventType.USER_NAME_UPDATED, user);
        eventBus.publish(event);
    }

    public void updateUsername(String username, String newUsername) {
        User user = getUser(username);
        user.updateUsernameInDB(username, newUsername);

        UserEvent event = new UserEvent(UserEvent.EventType.USER_USERNAME_UPDATED, user);
        eventBus.publish(event);
    }

    public void updateEmail(String username, String newEmail) {
        User user = getUser(username);
        user.updateEmailInDB(username, newEmail);

        UserEvent event = new UserEvent(UserEvent.EventType.USER_EMAIL_UPDATED, user);
        eventBus.publish(event);
    }

    public void updatePassword(String username, String newPassword) {
        User user = getUser(username);
        user.updatePasswordInDB(username, newPassword);

        UserEvent event = new UserEvent(UserEvent.EventType.USER_PASSWORD_UPDATED, user);
        eventBus.publish(event);
    }
    public User getUser(String username){
        String url = "jdbc:postgresql://localhost:6543/Chat_API";
        String usernameDB = "admin";
        String passwordDB = "123";
        try(Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB)){
            String query = "SELECT * FROM users WHERE username = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setString(1, username);

                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    if(resultSet.next()){
                        return new User(resultSet);
                    }
                    else{
                        return null;
                    }
                }

            }
        }catch(SQLException e ){
            e.getStackTrace();
            System.out.println("ERROR: Finding user was not successful");
            return null;
        }

    }

    public List<User> getUsers() {
        String url = "jdbc:postgresql://localhost:6543/Chat_API";
        String usernameDB = "admin";
        String passwordDB = "123";
        List<User> userList = new ArrayList<>();

        try(Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB)){
            String query = "SELECT * FROM users";
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    while(resultSet.next()){
                        userList.add(new User(resultSet));
                    }

                }

            }
        }catch(SQLException e ){
            e.getStackTrace();
            System.out.println("ERROR: Finding user was not successful");
        }
        return userList;
    }
}
