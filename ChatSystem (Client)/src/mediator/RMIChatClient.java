package mediator;

import model.Model;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.RemoteListener;
import utility.observer.subject.PropertyChangeHandler;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RMIChatClient implements ClientModel, RemoteListener<Object,Object>
{
  private RemoteModel remoteModel;
  private Model model;
  public static final String HOST = "localhost";
  PropertyChangeHandler<Object,Object> property;


  public RMIChatClient(Model model)
      throws RemoteException, NotBoundException, MalformedURLException
  {
    this.model = model;
    this.remoteModel = (RemoteModel)Naming.lookup("rmi://" + HOST
        + ":1099/Chat");
    UnicastRemoteObject.exportObject(this,0);
    remoteModel.addListener(this);
    this.property = new PropertyChangeHandler<>(this);
  }

  @Override public void login(String userName) throws RemoteException {
    remoteModel.login(userName);
  }

  @Override public void logout(String userName) throws RemoteException {
    remoteModel.logout(userName);
  }

  @Override public ArrayList<String> getAllUsers() throws RemoteException {
    return remoteModel.getAllUsers();
  }

  @Override public void addMessage(Message message) throws RemoteException {
    remoteModel.addMessage(message);
  }

  @Override public void propertyChange(ObserverEvent event) throws RemoteException {

  }

  @Override public boolean addListener(GeneralListener<Object, Object> listener,
      String... propertyNames)
  {
    return property.addListener(listener,propertyNames);
  }

  @Override public boolean removeListener(GeneralListener<Object, Object> listener,
      String... propertyNames)
  {
    return property.removeListener(listener,propertyNames);
  }
}
