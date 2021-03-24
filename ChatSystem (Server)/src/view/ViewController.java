package view;

import javafx.scene.layout.Region;
import viewmodel.ViewModelFactory;

public abstract class ViewController
{
  public enum View
  {
    CHATLOG;
  }

  private Region root;
  private ViewHandler viewHandler;
  private ViewModelFactory viewModelFactory;

  private View key;

  public ViewController(View key)
  {
    // FXML loader
    this.key = key;
  }

  protected abstract void init();

  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModelFactory = viewModelFactory;
    this.root = root;
  }

  public void reset()
  {
    // TO DO

  }

  public Region getRoot()
  {
    return root;
  }

  public ViewModelFactory getViewModelFactory()
  {
    return viewModelFactory;
  }

  public ViewHandler getViewHandler()
  {
    return viewHandler;
  }
}
