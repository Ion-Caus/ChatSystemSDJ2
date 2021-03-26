package model;

import java.util.ArrayList;

public class  UserList
{
  private ArrayList<User> users;

  public UserList()
  {
    this.users = new ArrayList<>();
  }

  public int size()
  {
    return users.size();
  }

  public User getUser(int index)
  {
    return users.get(index);
  }

  public ArrayList<User> getAllUsers() {
    return users;
  }

  public User getUserByName(String name)
  {
    for (int i = 0; i < users.size(); i++)
    {
      if (users.get(i).getUserName().getName().equalsIgnoreCase(name))
      {
        return users.get(i);
      }
    }
    return null;
  }

  public void addUser(User user)
  {
    if (!contains(user))
    {
      users.add(user);
    }
    else
    {
      throw new IllegalStateException("User already exist");
    }
  }

  public void addUser(UserName userName)
  {
    addUser(new User(userName));
  }

  public void removeUser(UserName userName){
    if(!contains(new User(userName))){
      throw new IllegalArgumentException("User not found");
    }
    users.removeIf(user -> user.getUserName().getName().equals(userName.getName()));
  }

  public boolean contains(User user)
  {
    if (user == null)
    {
      return false;
    }
    return getUserByName(user.getUserName().getName()) != null;
  }

  @Override public String toString()
  {
    return "UserList{" + "users=" + users + '}';
  }
}
