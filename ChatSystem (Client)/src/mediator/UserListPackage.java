package mediator;

import model.User;
import model.UserList;

import java.util.ArrayList;

public class UserListPackage
{
  private String type;
  private ArrayList<User> users;

  public UserListPackage(String type)
  {
    this(type, new ArrayList<>());
  }

  public UserListPackage(String type, ArrayList<User> users)
  {
    this.type = type;
    this.users = users;
  }

  public String getType()
  {
    return type;
  }

  public ArrayList<User> getUsers()
  {
    return users;
  }

  @Override public String toString()
  {
    return "Package: " + type + ", " + users;
  }

}
