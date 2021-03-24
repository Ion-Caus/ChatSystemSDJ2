package view;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.ChatLogViewModel;


public class ChatLogViewController extends ViewController
{
  @FXML private ListView<String> logList;
  @FXML private TextField inputField;

  private Region root;
  private ChatLogViewModel viewModel;
  private ViewHandler viewHandler;
  public View key;

  public ChatLogViewController()
  {
  }

  @Override protected void init() {
    this.inputField.textProperty().bindBidirectional(viewModel.getMessageProperty());
    this.logList.setItems(viewModel.getLogs());
  }

  public void reset() {
  }

  public Region getRoot() {
    return root;
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
