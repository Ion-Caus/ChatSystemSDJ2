package model;

import external.Log;
import mediator.Message;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;

import java.util.ArrayList;

public class ModelManager implements Model
{
    private UserList users;
    private PropertyChangeHandler<Object,Object> property;

    public ModelManager()
    {
        this.users = new UserList();
        this.property = new PropertyChangeHandler<>(this);
    }


    @Override
    public synchronized void addMessage(Message message)
    {
        Log.getLog().addLog(message.toString());
        property.firePropertyChange("Message", null, message);
    }

    @Override
    public ArrayList<String> getAllUsers() {
        return users.getAllUsernames();
    }

    @Override public int getNumberOfUsers()
    {
        return users.size();
    }

    @Override public User getUser(int index) throws IndexOutOfBoundsException
    {
        return users.getUser(index);
    }

    @Override public User getUserByName(String name)
    {
        return users.getUserByName(name);
    }

    @Override public void addUser(User user)
            throws IllegalStateException, IllegalArgumentException
    {
        addUser(user.getUserName());
    }

    @Override public void addUser(String userName)
            throws IllegalStateException, IllegalArgumentException
    {
        addUser(new UserName(userName));
    }

    @Override public void addUser(UserName userName)
            throws IllegalStateException, IllegalArgumentException
    {
        users.addUser(userName);
        property.firePropertyChange("Login", null, userName.getName());
        Log.getLog().addLog("ADDED: " + new User(userName));
    }

    @Override public void removeUser(UserName userName)
            throws IllegalStateException, IllegalArgumentException
    {
        users.removeUser(userName);
        property.firePropertyChange("Logout", null, userName.getName());
        Log.getLog().addLog("REMOVED: " + new User(userName));
    }

    @Override public boolean contains(User user)
    {
        return users.contains(user);
    }

    @Override public boolean addListener(GeneralListener<Object, Object> listener,
        String... propertyNames)
    {
        return property.addListener(listener, propertyNames);
    }

    @Override public boolean removeListener(GeneralListener<Object, Object> listener,
        String... propertyNames)
    {
        return property.removeListener(listener, propertyNames);
    }
}
