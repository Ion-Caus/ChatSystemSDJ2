package viewmodel;

import external.Log;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.Message;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChatLogViewModel implements PropertyChangeListener
{
  private Model model;
  private ObservableList<String> logs;
  private StringProperty message;

  public ChatLogViewModel(Model model){
    this.model = model;
    this.logs = FXCollections.observableArrayList();
    this.message = new SimpleStringProperty();
    model.addListener("Message", this);
    model.addListener("UserAdded",this);
    model.addListener("UserLeft",this);
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
              new Message( "Server", message.get())
      );
    }
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {

    switch (evt.getPropertyName()) {
      case "Message": {
        Platform.runLater(() -> {
          Message message = (Message) evt.getNewValue();
          logs.add(message.toString());
        });
        break;
      }
      case "UserAdded":{
        Platform.runLater(
            ()-> {
              model.addMessage(
                  new Message( "", "New user joined the server: "+evt.getNewValue().toString())
              );
            }
        );
        break;
      }
      case "UserLeft":{
        Platform.runLater(
            ()->{
              model.addMessage(
                  new Message( "", "User: "+evt.getNewValue().toString()+" has left the chat")
              );
            }
        );
        break;
      }
    }


  }
}
