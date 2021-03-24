package view;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;

public class ViewHandler extends ViewCreator
{
  private Scene currentScene;
  private Stage primaryStage;
  private ViewModelFactory viewModelFactory;

  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.viewModelFactory = viewModelFactory;
    this.currentScene = new Scene(new Region());
  }

  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
    openView(View.LOGIN);
  }

  public void openView(View id)
  {

  }

  @Override
  protected void initViewController(ViewController controller, Region root)
  {
    controller.init(this, viewModelFactory, root);
  }
}
