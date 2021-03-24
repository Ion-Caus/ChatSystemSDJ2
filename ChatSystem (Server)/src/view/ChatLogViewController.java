package view;

import java.util.HashMap;
import java.util.Map;

public class ChatLogViewController extends ViewController
{
  private static Map<View, ChatLogViewController> allInstances = new HashMap<>();

  private ChatLogViewController(View key)
  {
    super(key);
  }

  @Override protected void init()
  {
    getInstance(View.CHATLOG);
  }

  public static ChatLogViewController getInstance(View key)
  {
    ChatLogViewController instance = allInstances.get(key);
    if (instance == null)
    {
      synchronized (allInstances)
      {
        instance = allInstances.get(key);
        if (instance == null)
        {
          instance = new ChatLogViewController(key);
          allInstances.put(key, instance);
        }
      }
    }
    return instance;
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
