package Controlers;

import Event.ChatRoomEvent;
import Model.ChatRoom;
import Model.User;
import Service.ChatRoomService;
import Service.EventBus;

import java.util.Set;

public class ChatRoomController {
    private final ChatRoomService _chatRoomService;

    public ChatRoomController(ChatRoomService chatRoomService, EventBus eventBus) {
        this._chatRoomService = chatRoomService;

    }

    public void createChatRoom(String id, String name) {
        this._chatRoomService.addChatRoom(id, name);
    }

    public void addUserToChatRoom(String chatRoomId, User user) {
        this._chatRoomService.addUserToChatRoom(chatRoomId, user);
    }

    public void removeUserFromChatRoom(String chatRoomId, User user) {
        this._chatRoomService.removeUserFromChatRoom(chatRoomId, user);
    }

    public Set<User> getChatRoomParticipants(String chatRoomId) {
        return this._chatRoomService.getChatRoomParticipants(chatRoomId);
    }
}
