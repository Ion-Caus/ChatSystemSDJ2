import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

import java.io.IOException;

public class MyApplication extends Application {

  public void start(Stage primaryStage) {
    try {
      Model model = new ModelManager();
      ViewModelFactory viewModelFactory = new ViewModelFactory(model);
      ViewHandler view = new ViewHandler(viewModelFactory);

      view.start(primaryStage);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}