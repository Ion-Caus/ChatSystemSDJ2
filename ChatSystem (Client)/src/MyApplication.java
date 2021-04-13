import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

public class MyApplication extends Application {
  private Model model;
  public void start(Stage primaryStage) {
    try {
      model = new ModelManager();
      ViewModelFactory viewModelFactory = new ViewModelFactory(model);
      ViewHandler view = new ViewHandler(viewModelFactory);

      view.start(primaryStage);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void stop(){
    System.out.println("Logout: " + model.getUsername());
    if (model.getUsername() != null) {
      model.logout();
    }
  }
}