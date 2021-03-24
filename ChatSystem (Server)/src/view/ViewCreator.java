package view;

import javafx.scene.layout.Region;

public abstract class ViewCreator
{
  public ViewCreator()
  {

  }

  public ViewController getViewController(ViewController.View id)
  {

  }

  protected abstract void initViewController(ViewController controller, Region root);

  private ViewController loadFromFXML(String fxmlFile)
  {

  }
}
