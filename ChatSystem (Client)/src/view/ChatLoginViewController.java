package view;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.ChatLoginViewModel;

public class ChatLoginViewController extends ViewController
{
  @FXML private Label errorLabel;
  @FXML private TextField usernameField;

  private ChatLoginViewModel viewModel;

  public ChatLoginViewController()
  {
    // FXML loader
  }

  @Override protected void init()
  {
    this.viewModel = getViewModelFactory().getLoginChatViewModel();
    this.errorLabel.textProperty().bind(viewModel.getErrorProperty());
    this.usernameField.textProperty().bindBidirectional(viewModel.getUsernameProperty());
  }

  @Override public void reset() {
    viewModel.clear();
  }

  @FXML private void loginPressed()
  {
    viewModel.login();
  }

  @FXML private void onEnter(Event event)
  {
    if(event.getSource()==usernameField)
      loginPressed();
  }

}
