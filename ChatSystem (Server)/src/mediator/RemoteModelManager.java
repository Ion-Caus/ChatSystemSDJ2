package mediator;

import javafx.application.Platform;
import model.Message;
import model.Model;
import model.UserName;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.PropertyChangeHandler;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RemoteModelManager implements RemoteModel, LocalListener<Object,Object>
{
  private PropertyChangeHandler<Object,Object> property;
  private Model model;

  public RemoteModelManager(Model model) throws RemoteException, MalformedURLException {
    this.model = model;
    this.property = new PropertyChangeHandler<>(this);

    startRegistry();
    startServer();
    model.addListener(this,"Message");
  }

  private void startRegistry() throws RemoteException
  {
    try
    {
      Registry registry = LocateRegistry.createRegistry(1099);
      System.out.println("Registry started...");
    }
    catch (java.rmi.server.ExportException e)
    {
      System.out.println("Error: " + e.getMessage());
    }
  }

  private void startServer() throws RemoteException, MalformedURLException
  {
    UnicastRemoteObject.exportObject(this, 0);
    Naming.rebind("Chat", this);
  }

  @Override public void login(String userName) {
    model.addUser(userName);
    property.firePropertyChange("Login",null,userName);
  }

  @Override public void logout(String userName) {
    model.removeUser(new UserName(userName));
    property.firePropertyChange("Logout",null,userName);
  }

  @Override public ArrayList<String> getAllUsers() {
    return model.getAllUsers();
  }

  @Override public void addMessage(Message message) {
    model.addMessage(message);
  }


  @Override public boolean addListener(GeneralListener listener, String... propertyNames)
      throws RemoteException
  {
    return property.addListener(listener,propertyNames);
  }

  @Override public boolean removeListener(GeneralListener listener,
      String... propertyNames) throws RemoteException
  {
    return property.removeListener(listener,propertyNames);
  }

  @Override public void propertyChange(ObserverEvent event) {
    Platform.runLater(()->property.firePropertyChange(event));
  }
}
