package mediator;

import java.io.Serializable;

public class Message implements Serializable {

    private String username;
    private String message;

    public Message(String username, String message) {
        this.username = username;
        if (message == null || message.isEmpty()) {
            throw new IllegalArgumentException("Message cannot be empty");
        }
        this.message = message;
    }

    public String getUsername() {
        return username;
    }


    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return String.format("%s : %s ", username, message);
    }
}
