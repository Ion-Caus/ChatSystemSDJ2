package model;

public class Message {

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

  public Message addUserIp(String ip) {
     username += "(" + ip + ")";
     return this;
  }

  public Message removeUserIp() {
    if (username.contains("(")) {
      this.username = username.replace(
              username.substring( username.indexOf("("), username.indexOf(")")+1 ),
            "");
    }
    return this;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public String toString() {
    return String.format("%s: %s ", username, message);
  }
}