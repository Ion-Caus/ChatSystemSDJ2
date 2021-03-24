package model;

import utility.observer.NamedPropertyChangeSubject;

public interface Model extends NamedPropertyChangeSubject
{
  public int getNumberOfUsers();
  public User getUser(int index) throws IndexOutOfBoundsException;
  public User getUserByName(String name);
  public void addUser(User user)
      throws IllegalStateException, IllegalArgumentException;
  public void addUser(UserName userName)
      throws IllegalStateException, IllegalArgumentException;
  public void addUser(String userName)
      throws IllegalStateException, IllegalArgumentException;
  public boolean contains(User user);
  public void addMessage(String message);
}
