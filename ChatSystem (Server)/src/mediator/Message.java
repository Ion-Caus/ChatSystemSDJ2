package mediator;

import model.User;

public class Message
{
  private User user;
  private String type;
  private String message;

  public Message(User user, String type, String message) {

    if (message==null){
      throw new IllegalArgumentException("No message");
    }
    if (user==null){
      throw new IllegalArgumentException("No user");
    }
    this.user = user;
    this.type = type;
    this.message = message;
  }

  public Message(User user) {
    this.user = user;
  }

  public String getType() {
    return type;
  }

  public String getMessage() {
    return message;
  }
}
