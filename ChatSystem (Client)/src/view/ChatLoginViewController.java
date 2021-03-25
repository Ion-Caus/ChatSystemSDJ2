package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.ChatLoginViewModel;

public class ChatLoginViewController extends ViewController
{
  @FXML private Label errorLabel;
  @FXML private TextField usernameField;

  private Region root;
  private ChatLoginViewModel viewModel;
  private ViewHandler viewHandler;

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

  @FXML private void loginPressed()
  {
    // TODO
  }

  @FXML private void onEnter()
  {
    loginPressed();
  }
}
