package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Message;
import model.Model;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChatLogViewModel implements LocalListener<Object, Object> {
    private Model model;
    private ObservableList<String> logs;
    private StringProperty message;

    public ChatLogViewModel(Model model) {
        this.model = model;
        this.logs = FXCollections.observableArrayList();
        this.message = new SimpleStringProperty();
        model.addListener(this, "Message");
        model.addListener(this, "Login", "Logout");
    }

    public ObservableList<String> getLogs() {
        return logs;
    }

    public void clear() {
        message.set("");
    }

    public StringProperty getMessageProperty() {
        return message;
    }

    public void setMessage() {
        if (!message.get().isEmpty()) {

            model.addMessage(
                    new Message("Server", message.get())
            );
        }
    }


    @Override
    public void propertyChange(ObserverEvent<Object, Object> evt) {
        Platform.runLater(() -> {
            switch (evt.getPropertyName()) {
                case "Message":
                    Message message = (Message) evt.getValue2();
                    logs.add(message.toString());

                    break;
                case "Login":
                    model.addMessage(new Message("Server",
                            "New user joined the server: " + evt.getValue2()) );
                    break;
                case "Logout":
                    // TODO add .toString
                    model.addMessage(new Message("Server",
                            evt.getValue2() + " has left the chat"));
                    break;

            }
        });
    }

}