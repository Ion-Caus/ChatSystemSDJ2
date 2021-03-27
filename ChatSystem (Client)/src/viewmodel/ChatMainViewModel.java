package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.Message;
import mediator.MessagePackage;
import model.Model;
import model.User;
import model.UserList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ChatMainViewModel implements PropertyChangeListener {
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
        model.addListener("Message", this);
        model.addListener("User", this);

        addUsersToList();

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

    private void addUsersToList()
    {
        ArrayList<User> allUsers = model.getAllUsers();
        for (int i = 0; i < allUsers.size(); i++)
        {
            this.listUser.add(allUsers.get(i).getUserName().getName());
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        MessagePackage messagePackage = (MessagePackage)evt.getNewValue();
        if (messagePackage != null) {
            Platform.runLater(() -> {
                switch (evt.getPropertyName()) {
                    case "Message":
                        addMessageToChat(messagePackage.getMessage()
                        );
                        break;
                   // case "User":
                        //addUsersToList();
                       // break;
                }
            });
        }
    }
}
