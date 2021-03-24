package model;

import external.log.Log;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements Model
{
  private UserList users;
  private PropertyChangeSupport propertyChangeSupport;

  public ModelManager()
  {
    this.users = new UserList();
    this.propertyChangeSupport = new PropertyChangeSupport(this);
  }

   public synchronized void addMessage(String message)
  {
    Log.getLog().addLog(message);
    propertyChangeSupport.firePropertyChange("Message", null, message);
  }

  @Override public int getNumberOfUsers()
  {
    return users.size();
  }

  @Override public User getUser(int index) throws IndexOutOfBoundsException
  {
    return users.getUser(index);
  }

  @Override public User getUserByName(String name)
  {
    return users.getUserByName(name);
  }

  @Override public void addUser(User user)
      throws IllegalStateException, IllegalArgumentException
  {
    users.addUser(user);
    System.out.println("ADDED: " + user);
  }

  @Override public void addUser(String userName)
      throws IllegalStateException, IllegalArgumentException
  {
    addUser(new UserName(userName));
  }

  @Override public void addUser(UserName userName)
      throws IllegalStateException, IllegalArgumentException
  {
    users.addUser(userName);
    System.out.println("ADDED: " + new User(userName));
  }

  @Override public boolean contains(User user)
  {
    return users.contains(user);
  }

  @Override public void addListener(String propertyName,
      PropertyChangeListener listener)
  {
    propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
  }

  @Override public void removeListener(String propertyName,
      PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
  }
}
