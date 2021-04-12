package mediator;

import model.Message;
import model.UserList;
import model.UserName;
import utility.observer.subject.RemoteSubject;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteModel extends RemoteSubject
{
  void login(UserName userName) throws RemoteException;
  void logout(UserName userName) throws RemoteException;
  ArrayList<String> getAllUsers() throws RemoteException;
  void addMessage (Message message) throws RemoteException;

}
