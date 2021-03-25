package mediator;

public class Message {

  private String username;
  private String type;
  private String message;

  public Message(String type, String message, String username) {
    this.username = username;
    this.type = type;
    this.message = message;
  }

  public String getUsername() {
    return username;
  }

  public String getType() {
    return type;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public String toString() {
    return String.format("%s, %s :, %s \n", type, username, message);
  }
}