package model;

import mediator.Message;
import utility.observer.NamedPropertyChangeSubject;

import java.util.ArrayList;

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
  void addMessage(Message message);
  void removeUser(UserName userName) throws IllegalStateException, IllegalArgumentException;
  ArrayList<User> getAllUsers();
}
