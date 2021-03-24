package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.util.HashMap;

public abstract class ViewCreator
{

  private HashMap<View,ViewController> map;

  public ViewCreator()
  {
    this.map = new HashMap<>();
  }

  public ViewController getViewController(View id)
  {
    switch (id){
      case CHATLOG:{
        if(map.get(View.CHATLOG)==null){
          map.put(View.CHATLOG,loadFromFXML(View.CHATLOG.getFxmlFile()));
        }
        return map.get(View.CHATLOG);
      }
      default:
        return null;
    }
  }

  protected abstract void initViewController(ViewController controller, Region root);

  private ViewController loadFromFXML(String fxmlFile)
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(fxmlFile));
    return loader.getController();
  }

}
