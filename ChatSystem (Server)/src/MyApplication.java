import javafx.application.Application;
import javafx.stage.Stage;
import mediator.ChatServer;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

public class MyApplication extends Application
{
  private ChatServer chatServer;

  @Override public void start(Stage stage)
  {
    Model model = new ModelManager();
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler viewHandler = new ViewHandler(viewModelFactory);
    viewHandler.start(stage);

    chatServer = new ChatServer(model);
    new Thread(chatServer).start();
  }

  @Override public void stop()
  {
    chatServer.close();
  }
}
