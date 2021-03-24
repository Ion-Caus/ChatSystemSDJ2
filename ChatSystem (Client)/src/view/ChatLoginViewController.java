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
  public View key;

  public ChatLoginViewController()
  {

  }

  @Override protected void init()
  {
    this.errorLabel.textProperty().bind(viewModel.getErrorProperty());
    this.usernameField.textProperty().bindBidirectional(viewModel.getUsernameProperty());
  }

  public Region getRoot()
  {
    return root;
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
