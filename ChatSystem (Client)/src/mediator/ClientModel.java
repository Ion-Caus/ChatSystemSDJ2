package mediator;

import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientModel extends LocalSubject<Object, Object>
{
  void login(String userName);
  void logout();
  ArrayList<String> getAllUsers();
  void addMessage (String message);
  String getUsername();
}
