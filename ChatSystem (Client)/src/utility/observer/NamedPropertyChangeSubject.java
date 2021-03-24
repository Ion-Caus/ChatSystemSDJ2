package utility.observer;

import java.beans.PropertyChangeListener;

public interface NamedPropertyChangeSubject
{
  void addListener(String nameProperty, PropertyChangeListener listener);
  void removeListener(String nameProperty, PropertyChangeListener listener);
}