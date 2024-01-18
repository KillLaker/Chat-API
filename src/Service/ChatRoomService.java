package Service;

import Event.ChatRoomEvent;
import Model.ChatRoom;
import Model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

public class ChatRoomService {

    private static final String URL = "jdbc:postgresql://localhost:6543/Chat_API";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "123";
    private final EventBus eventBus;

    public ChatRoomService(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void addChatRoom(String id, String name) {
        ChatRoom chatRoom = new ChatRoom(id, name);
        chatRoom.createInDB();

        ChatRoomEvent event = new ChatRoomEvent(ChatRoomEvent.EventType.CHAT_ROOM_CREATED, chatRoom, null);
        eventBus.publish(event);
    }

    public void addUserToChatRoom(String chatRoomId, User user) {
        ChatRoom chatRoom = new ChatRoom(chatRoomId, null);
        chatRoom.addUser(user);

        ChatRoomEvent event = new ChatRoomEvent(ChatRoomEvent.EventType.USER_ADDED_TO_CHAT_ROOM, chatRoom, user);
        eventBus.publish(event);
    }

    public void removeUserFromChatRoom(String chatRoomId, User user) {
        ChatRoom chatRoom = new ChatRoom(chatRoomId, null);
        chatRoom.removeUser(user);

        ChatRoomEvent event = new ChatRoomEvent(ChatRoomEvent.EventType.USER_REMOVED_FROM_CHAT_ROOM, chatRoom, user);
        eventBus.publish(event);
    }

    public Set<User> getChatRoomParticipants(String chatRoomId) {
        ChatRoom chatRoom = new ChatRoom(chatRoomId, null);
        return chatRoom.getParticipants();
    }
}
