package mediator;

import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientModel extends LocalSubject<Object, Object>
{
  void login(String userName) throws RemoteException;
  void logout(String userName) throws RemoteException;
  ArrayList<String> getAllUsers() throws RemoteException;
  void addMessage (Message message) throws RemoteException;
}
