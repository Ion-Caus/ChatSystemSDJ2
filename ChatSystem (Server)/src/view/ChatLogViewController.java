package view;

import java.util.HashMap;
import java.util.Map;

public class ChatLogViewController extends ViewController
{

  private ChatLogViewController()
  {

  }

  @Override protected void init() {

  }


  /*
  @FXML private ListView<String> logList;
  @FXML private TextField inputField;

  private Region root;
  private LogViewModel viewModel;
  private ViewHandler viewHandler;
  public View key;

  public ChatLogViewController(View key)
  {
    super(key);
  }

  public void init(ViewHandler viewHandler, LogViewModel viewModel, Region root) {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    this.inputField.textProperty().bindBidirectional(viewModel.getMessageProperty());
    this.logList.setItems(viewModel.getLogs());
  }

  public void reset() {
    // do nothing
  }

  public Region getRoot() {
    return root;
  }

  @FXML
  private void onEnter() {
    viewModel.setMessage();
    inputField.setText("");
  }
   */

}
