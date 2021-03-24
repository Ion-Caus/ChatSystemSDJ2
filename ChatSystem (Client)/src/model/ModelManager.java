package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements Model
{
  private PropertyChangeSupport property;

  public ModelManager()
  {
    // TODO
  }

  @Override public void addListener(String nameProperty,
      PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(nameProperty, listener);
  }

  @Override public void removeListener(String nameProperty,
      PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(nameProperty, listener);
  }
}
