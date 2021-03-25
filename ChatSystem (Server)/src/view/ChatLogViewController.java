package view;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import viewmodel.ChatLogViewModel;


public class ChatLogViewController extends ViewController
{
  @FXML private ListView<String> logList;
  @FXML private TextField inputField;

  private ChatLogViewModel viewModel;

  public ChatLogViewController()
  {
  }

  @Override protected void init() {
    this.viewModel = getViewModelFactory().getChatLogViewModel();
    this.inputField.textProperty().bindBidirectional(viewModel.getMessageProperty());
    this.logList.setItems(viewModel.getLogs());
  }

  public void reset() {
  }


  @FXML
  private void onEnter() {
    viewModel.setMessage();
    inputField.setText("");
  }

  public void broadcastMessage()
  {
    viewModel.setMessage();
    inputField.setText("");
  }
}
