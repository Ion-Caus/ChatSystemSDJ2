package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.Message;
import model.Model;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;


public class ChatMainViewModel implements LocalListener<Object, Object> {
    private Model model;
    private StringProperty message;
    private StringProperty loggedInAs;
    private StringProperty error;
    private ObservableList<String> listUser, chatList;

    public ChatMainViewModel(Model model) {
        this.model = model;

        this.chatList = FXCollections.observableArrayList();
        this.message = new SimpleStringProperty();
        this.loggedInAs = new SimpleStringProperty();
        this.error = new SimpleStringProperty();
        this.listUser = FXCollections.observableArrayList();
        model.addListener(this);

        loadFromModel();
    }

    private void loadFromModel() {
        listUser.clear();
        listUser.addAll(model.getAllUsers());
    }

    public ObservableList<String> getChatListProperty() {
        return chatList;
    }

    public StringProperty getMessageProperty() {
        return message;
    }

    public StringProperty getLoggedInAsProperty() {
        return loggedInAs;
    }

    public StringProperty getErrorProperty() {
        return error;
    }

    public ObservableList<String> getListUserProperty() {
        return listUser;
    }

    public void clear() {
        loggedInAs.set("Logged in as: " + model.getUsername());
        error.set("");
        message.set("");

    }

    public void sendMessage() {
        if (!message.get().isEmpty()) {
            model.sendMessage(message.get());
        }
    }

    private void addMessageToChat(Message message) {
        chatList.add(message.getUsername() + ": " + message.getMessage());
    }

    private void addUser(String username) {
        listUser.add(username);
        System.out.println(listUser);
    }

    private void removeUser(String username) {
        listUser.remove(username);
    }

    @Override
    public void propertyChange(ObserverEvent<Object, Object> event) {
        Platform.runLater(() -> {
            switch (event.getPropertyName()) {
                case "Message":
                    addMessageToChat((Message) event.getValue2());
                    break;
                case "Login":
                    addUser( (String) event.getValue2());
                    break;
                case "Logout":
                    removeUser( (String) event.getValue2());
                    break;

            }
        });
    }
}
