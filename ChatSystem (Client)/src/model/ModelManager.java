package model;

import javafx.application.Platform;
import mediator.ChatClient;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;

public class ModelManager implements Model, PropertyChangeListener {
    public static final String HOST = "localhost";
    public static final int PORT = 6789;

    private PropertyChangeSupport property;
    private ChatClient chatClient;

    public ModelManager() throws IOException {
        property = new PropertyChangeSupport(this);
        chatClient = new ChatClient( HOST, PORT);
        chatClient.addListener("Login", this);
        chatClient.addListener("Message", this);
    }

    @Override
    public void sendMessage(String message) {
        chatClient.sendMessage(message);
    }

    @Override
    public void login(String username) throws Exception {
        chatClient.login(username);
    }

    @Override
    public String getUsername() {
        return chatClient.getUsername();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater( () ->
                property.firePropertyChange(
                        evt.getPropertyName(),
                        evt.getOldValue(),
                        evt.getNewValue()
                )
        );
    }

    @Override
    public void addListener(String nameProperty,
                            PropertyChangeListener listener) {
        property.addPropertyChangeListener(nameProperty, listener);
    }

    @Override
    public void removeListener(String nameProperty,
                               PropertyChangeListener listener) {
        property.removePropertyChangeListener(nameProperty, listener);
    }

}
