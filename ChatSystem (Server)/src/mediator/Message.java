package mediator;

public class Message
{
  private String type;
  private String source;

  public Message(String type, String source) {
    this.type = type;
    this.source = source;
  }

  public String getType() {
    return type;
  }

  public String getSource() {
    return source;
  }
}
