package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;

public class ChatMainViewModel
{
  private Model model;
  private StringProperty chatText;
  private StringProperty message;
  private StringProperty loggedInAs;
  private StringProperty error;
  private ObservableList<String> listUser;

  public ChatMainViewModel(Model model)
  {
    this.model = model;
    this.chatText = new SimpleStringProperty();
    this.message = new SimpleStringProperty();
    this.loggedInAs = new SimpleStringProperty();
    this.error = new SimpleStringProperty();
    this.listUser = FXCollections.observableArrayList();
  }

  public StringProperty getChatTextProperty()
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
}
