package view;

import javafx.scene.layout.Region;
import viewmodel.ViewModelFactory;

public abstract class ViewController
{

  private Region root;
  private ViewHandler viewHandler;
  private ViewModelFactory viewModelFactory;

  public ViewController()
  {
    // FXML loader
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
    // TODO
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
