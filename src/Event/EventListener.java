package Event;

import Model.ChatRoom;
import Service.EventBus.EventHandler;
import Model.Message;
import Model.User;

public class EventListener implements EventHandler<Object> {
    @Override
    public void handle(Object event) {
        if (event instanceof MessageEvent) {
            handleMessageEvent((MessageEvent) event);
        } else if (event instanceof UserEvent) {
            handleUserEvent((UserEvent) event);
        } else if (event instanceof ChatRoomEvent) {
            handleChatRoomEvent((ChatRoomEvent) event);
        }
    }
    private void handleMessageEvent(MessageEvent event) {
        switch (event.getType()) {
            case NEW_MESSAGE:
                onNewMessage(event.getMessage());
                break;
            case EDITED_MESSAGE:
                onEditedMessage(event.getMessage());
                break;
            case DELETED_MESSAGE:
                onDeletedMessage(event.getMessage());
                break;
        }
    }

    private void handleUserEvent(UserEvent event) {
        switch (event.getType()) {
            case USER_CREATED:
                onUserCreated(event.getUser());
                break;
            case USER_DELETED:
                onUserDeleted(event.getUser());
                break;
            case USER_NAME_UPDATED:
                onUserNameUpdated(event.getUser());
                break;
            case USER_USERNAME_UPDATED:
                onUserUsernameUpdated(event.getUser());
                break;
            case USER_EMAIL_UPDATED:
                onUserEmailUpdated(event.getUser());
                break;
            case USER_PASSWORD_UPDATED:
                onUserPasswordUpdated(event.getUser());
                break;
        }
    }


    private void handleChatRoomEvent(ChatRoomEvent event) {
        switch (event.getType()) {
            case CHAT_ROOM_CREATED:
                onChatRoomCreated(event.getChatRoom());
                break;
            case USER_ADDED_TO_CHAT_ROOM:
                onUserAddedToChatRoom(event.getChatRoom(), event.getUser());
                break;
            case USER_REMOVED_FROM_CHAT_ROOM:
                onUserRemovedFromChatRoom(event.getChatRoom(), event.getUser());
                break;
        }
    }


    private void onNewMessage(Message message) {
        System.out.println("New message received: " + message.getContent());
    }

    private void onEditedMessage(Message message) {
        System.out.println("Message edited: " + message.getContent());
    }

    private void onDeletedMessage(Message message) {
        System.out.println("Message deleted.");
    }

    private void onUserCreated(User user) {
        System.out.println("User created: " + user.getUsername());
    }

    private void onUserDeleted(User user) {
        System.out.println("User deleted: " + user.getUsername());
    }

    private void onUserNameUpdated(User user) {
        System.out.println("User name updated: " + user.getName());
    }

    private void onUserUsernameUpdated(User user) {
        System.out.println("User username updated: " + user.getUsername());
    }

    private void onUserEmailUpdated(User user) {
        System.out.println("User email updated: " + user.getEmail());
    }

    private void onUserPasswordUpdated(User user) {
        System.out.println("User password updated.");
    }
    private void onChatRoomCreated(ChatRoom chatRoom) {
        System.out.println("Chat room created: " + chatRoom.getName());
    }

    private void onUserAddedToChatRoom(ChatRoom chatRoom, User user) {
        System.out.println("User " + user.getUsername() + " added to chat room: " + chatRoom.getName());
    }

    private void onUserRemovedFromChatRoom(ChatRoom chatRoom, User user) {
        System.out.println("User " + user.getUsername() + " removed from chat room: " + chatRoom.getName());
    }
}

}
