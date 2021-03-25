package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChatMainViewModel implements PropertyChangeListener
{
  private Model model;
  private StringProperty message;
  private StringProperty loggedInAs;
  private StringProperty error;
  private ObservableList<String> listUser, chatText;

  public ChatMainViewModel(Model model)
  {
    this.model = model;
    this.chatText = FXCollections.observableArrayList();
    this.message = new SimpleStringProperty();
    this.loggedInAs = new SimpleStringProperty();
    this.error = new SimpleStringProperty();
    this.listUser = FXCollections.observableArrayList();
    model.addListener("Message",this);
  }

  public ObservableList<String> getChatTextProperty()
  {
    return chatText;
  }

  public StringProperty getMessageProperty()
  {
    return message;
  }

  public StringProperty getLoggedInAsProperty()
  {
    return loggedInAs;
  }

  public StringProperty getErrorProperty()
  {
    return error;
  }

  public ObservableList<String> getListUserProperty()
  {
    return listUser;
  }

  public void clear(){
    error.set("");
    message.set("");
  }

  @Override public void propertyChange(PropertyChangeEvent evt) {
    Platform.runLater(()->);
  }
}
