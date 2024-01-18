package Event;

import Model.Message;

public class MessageEvent {
    public enum EventType {
        NEW_MESSAGE,
        EDITED_MESSAGE,
        DELETED_MESSAGE
    }

    private final EventType type;
    private final Message message;

    public MessageEvent(EventType type, Message message) {
        this.type = type;
        this.message = message;
    }

    public EventType getType() {
        return type;
    }

    public Message getMessage() {
        return message;
    }
}