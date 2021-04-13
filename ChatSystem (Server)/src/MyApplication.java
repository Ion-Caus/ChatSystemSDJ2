import javafx.application.Application;
import javafx.stage.Stage;
import mediator.RemoteModel;
import mediator.RemoteModelManager;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class MyApplication extends Application
{
  private Model model;
  private RemoteModel server;

  @Override public void start(Stage stage)
  {
    model = new ModelManager();
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler viewHandler = new ViewHandler(viewModelFactory);
    viewHandler.start(stage);

    // starting server...

    try {
      server = new RemoteModelManager(model);
    }
    catch (RemoteException | MalformedURLException e) {
      e.printStackTrace();
    }
  }


}
