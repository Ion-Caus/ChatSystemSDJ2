package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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

    this.messageList.setItems(viewModel.getChatListProperty());
    this.userList.setItems(viewModel.getListUserProperty());

    this.messageField.textProperty().bindBidirectional(viewModel.getMessageProperty());

    this.loggedInAsLabel.textProperty().bind(viewModel.getLoggedInAsProperty());
    this.errorLabel.textProperty().bind(viewModel.getErrorProperty());
  }

  @Override public void reset() {
    viewModel.clear();
  }

  @FXML private void sendMessage()
  {
    viewModel.sendMessage();
    reset();
  }

  @FXML private void onEnter()
  {
    sendMessage();
  }
}
