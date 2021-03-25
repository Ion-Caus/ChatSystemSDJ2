package model;

import mediator.ChatClient;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;

public class ModelManager implements Model
{
  public static final String HOST = "localhost";
  public static final int PORT = 6789;
  private PropertyChangeSupport property;
  private ChatClient chatClient;

  public ModelManager() throws IOException
  {
    // TODO
    property = new PropertyChangeSupport(this);
    chatClient = new ChatClient(this, HOST, PORT);
    //chatClient.addListener();
  }

  @Override public void addListener(String nameProperty,
      PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(nameProperty, listener);
  }

  @Override public void removeListener(String nameProperty,
      PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(nameProperty, listener);
  }
}
