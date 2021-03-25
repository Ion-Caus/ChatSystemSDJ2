package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.ChatMainViewModel;

public class ChatMainViewController extends ViewController
{
  @FXML private ListView<String> messageList;
  @FXML private TextField messageField;
  @FXML private Label loggedInAsLabel;
  @FXML private Label errorLabel;
  @FXML private ListView<String> userList;

  private ChatMainViewModel viewModel;

  public ChatMainViewController()
  {
    // FXML loader
  }

  @Override protected void init()
  {
    this.viewModel = getViewModelFactory().getMainChatViewModel();
    this.messageList.setItems(viewModel.getChatTextProperty());
    this.messageField.textProperty().bind(viewModel.getMessageProperty());
    this.loggedInAsLabel.textProperty().bindBidirectional(
        viewModel.getLoggedInAsProperty());
    this.errorLabel.textProperty().bind(viewModel.getErrorProperty());
    this.userList.setItems(viewModel.getListUserProperty());
  }

  @Override public void reset() {
    viewModel.clear();
  }

  @FXML private void sendMessage()
  {
    // TODO
  }

  @FXML private void onEnter()
  {
    sendMessage();
  }
}
