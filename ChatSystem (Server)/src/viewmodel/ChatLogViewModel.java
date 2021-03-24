package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChatLogViewModel
{
  private Model model;
  private ObservableList<String> logs;
  private StringProperty message;

  public ChatLogViewModel(Model model){
    this.model = model;
    this.logs = FXCollections.observableArrayList();
    this.message = new SimpleStringProperty();
  }

  public ObservableList<String> getLogs() {
    return logs;
  }

  public StringProperty getMessageProperty() {
    return message;
  }

  public void setMessage() {
    if (!message.get().isEmpty()) {
      //model.addMessage(message.get()); potential model modifications needed
    }
  }
  
}
