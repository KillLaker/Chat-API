
package Event;

import Model.User;

public class UserEvent {
    public enum EventType {
        USER_CREATED,
        USER_DELETED,
        USER_NAME_UPDATED,
        USER_USERNAME_UPDATED,
        USER_EMAIL_UPDATED,
        USER_PASSWORD_UPDATED
    }

    private final EventType type;
    private final User user;

    public UserEvent(EventType type, User user) {
        this.type = type;
        this.user = user;
    }

    public EventType getType() {
        return type;
    }

    public User getUser() {
        return user;
    }
}
