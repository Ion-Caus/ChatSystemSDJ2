package model;

import utility.observer.NamedPropertyChangeSubject;

public interface Model extends NamedPropertyChangeSubject
{
  int getNumberOfUsers();
  User getUser(int index) throws IndexOutOfBoundsException;
  User getUserByName(String name);
  void addUser(User user)
      throws IllegalStateException, IllegalArgumentException;
  void addUser(UserName userName)
      throws IllegalStateException, IllegalArgumentException;
  void addUser(String userName)
      throws IllegalStateException, IllegalArgumentException;
  boolean contains(User user);
  void addMessage(String message, String user);
}
