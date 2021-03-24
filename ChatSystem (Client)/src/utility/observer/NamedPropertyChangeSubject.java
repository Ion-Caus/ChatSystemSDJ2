package utility.observer;

import java.beans.PropertyChangeListener;

public interface NamedPropertyChangeSubject
{
  void addListener(PropertyChangeListener listener);
  void removeListener(PropertyChangeListener listener);
}