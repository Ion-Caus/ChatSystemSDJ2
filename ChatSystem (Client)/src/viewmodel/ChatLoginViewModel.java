package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChatLoginViewModel implements PropertyChangeListener
{
  private Model model;
  private StringProperty error;
  private StringProperty username;

  public ChatLoginViewModel(Model model)
  {
    this.model = model;
    this.error = new SimpleStringProperty();
    this.username = new SimpleStringProperty();
    model.addListener("Login",this);
  }

  public StringProperty getErrorProperty()
  {
    return error;
  }

  public StringProperty getUsernameProperty()
  {
    return username;
  }

  public void login(){
    model.login(username.get());
  }

  public void clear(){
    error.set("");
    username.set("");
  }

  @Override public void propertyChange(PropertyChangeEvent evt) {
    Platform.runLater(()->);
  }
}
