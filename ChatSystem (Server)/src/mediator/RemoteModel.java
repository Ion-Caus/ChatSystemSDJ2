package mediator;

import utility.observer.subject.RemoteSubject;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteModel extends RemoteSubject<Object,Object>
{
  String login(String userName) throws RemoteException;
  void logout(String userName) throws RemoteException;
  ArrayList<String> getAllUsers() throws RemoteException;
  void addMessage (Message message) throws RemoteException;
}
