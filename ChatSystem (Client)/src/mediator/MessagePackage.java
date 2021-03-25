package mediator;

public class MessagePackage {

    private String type;
    private Message message;
    private String source;

    public MessagePackage(String type, Message message) {
        this.type = type;
        this.message = message;
        this.source = null;
    }

    public MessagePackage(String type, String source) {
        this.type = type;
        this.message = null;
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    public String getType() {
        return type;
    }

    public Message getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return String.format("-->%s, (%s) , %s ", type, message, source);
    }
}