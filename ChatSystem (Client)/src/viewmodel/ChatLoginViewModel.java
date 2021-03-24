package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class ChatLoginViewModel
{
  private Model model;
  private StringProperty error;
  private StringProperty username;

  public ChatLoginViewModel(Model model)
  {
    this.model = model;
    this.error = new SimpleStringProperty();
    this.username = new SimpleStringProperty();
  }

  public StringProperty getErrorProperty()
  {
    return error;
  }

  public StringProperty getUsernameProperty()
  {
    return username;
  }
}
