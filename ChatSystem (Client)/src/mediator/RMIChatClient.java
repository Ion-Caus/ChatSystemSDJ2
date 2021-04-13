package mediator;

import javafx.application.Platform;
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

public class RMIChatClient implements ClientModel, RemoteListener<Object, Object> {
    private RemoteModel remoteModel;
    private PropertyChangeHandler<Object, Object> property;
    private String user;

    public static final String HOST = "localhost";

    public RMIChatClient()
            throws RemoteException, NotBoundException, MalformedURLException {
        this.remoteModel = (RemoteModel) Naming.lookup("rmi://" + HOST
                + ":1099/Chat");
        UnicastRemoteObject.exportObject(this, 0);
        remoteModel.addListener(this);
        this.property = new PropertyChangeHandler<>(this);
    }

    @Override
    public void login(String userName) throws IllegalArgumentException {
        try {
            user = remoteModel.login(userName);
        }
        catch (IllegalStateException | RemoteException re) {
            throw new IllegalStateException(re.getMessage());
        }

    }

    @Override
    public void logout() {
        try {
            remoteModel.logout(user);
        }
        catch (RemoteException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Override
    public ArrayList<String> getAllUsers()  {
        try {
            return remoteModel.getAllUsers();
        }
        catch (RemoteException e) {
            throw new IllegalStateException(e.getMessage());
        }

    }

    @Override
    public void addMessage(String message)  {
        try {
            remoteModel.addMessage(new Message(user, message));
        }
        catch (RemoteException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Override
    public String getUsername() {
        return user;
    }

    @Override
    public void propertyChange(ObserverEvent event) throws RemoteException {
        Platform.runLater( () ->
            property.firePropertyChange(event)
        );
    }

    @Override
    public boolean addListener(GeneralListener<Object, Object> listener, String... propertyNames) {
        return property.addListener(listener, propertyNames);
    }

    @Override
    public boolean removeListener(GeneralListener<Object, Object> listener, String... propertyNames) {
        return property.removeListener(listener, propertyNames);
    }
}
