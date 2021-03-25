package view;

import javafx.event.Event;
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
    inputField.setText("");
  }


  @FXML
  private void onEnter(Event event) {
    if (event.getSource() == inputField) {
      broadcastMessage();
    }
  }

  public void broadcastMessage()
  {
    viewModel.setMessage();
    reset();
  }
}
