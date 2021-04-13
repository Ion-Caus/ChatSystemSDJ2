package model;

import javafx.application.Platform;
import mediator.ClientModel;
import mediator.Message;
import mediator.MessagePackage;
import mediator.RMIChatClient;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.LocalSubject;
import utility.observer.subject.PropertyChangeHandler;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ModelManager implements Model, LocalListener<Object, Object> {
    private PropertyChangeHandler<Object, Object> property;
    private ClientModel clientModel;

    public ModelManager() throws IOException, NotBoundException {
        property = new PropertyChangeHandler<>(this);
        clientModel = new RMIChatClient();
        clientModel.addListener(this, "Message", "Login", "Logout");
    }

    @Override
    public ArrayList<String> getAllUsers() {
        return clientModel.getAllUsers();
    }

    @Override
    public String getUsername() {
        return clientModel.getUsername();
    }

    @Override
    public void sendMessage(String message) {
        clientModel.addMessage(message);
    }

    @Override
    public void login(String username) throws IllegalArgumentException {
        clientModel.login(username);
    }

    @Override public void logout() {
        clientModel.logout();
    }

    @Override
    public void propertyChange(ObserverEvent<Object, Object> event) {
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
