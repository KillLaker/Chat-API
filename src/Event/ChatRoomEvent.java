package Event;

import Model.ChatRoom;
import Model.User;

public class ChatRoomEvent {
    public enum EventType {
        CHAT_ROOM_CREATED,
        USER_ADDED_TO_CHAT_ROOM,
        USER_REMOVED_FROM_CHAT_ROOM
    }

    private final EventType type;
    private final ChatRoom chatRoom;
    private final User user; // This can be null for CHAT_ROOM_CREATED events

    public ChatRoomEvent(EventType type, ChatRoom chatRoom, User user) {
        this.type = type;
        this.chatRoom = chatRoom;
        this.user = user;
    }

    public EventType getType() {
        return type;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public User getUser() {
        return user;
    }
}
