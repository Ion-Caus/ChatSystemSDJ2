package model;

public class ModelManager implements Model
{
  private UserList users;

  public ModelManager()
  {
    this.users = new UserList();
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
    users.addUser(user);
    System.out.println("ADDED: " + user);
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
    System.out.println("ADDED: " + new User(userName));
  }

  @Override public boolean contains(User user)
  {
    return users.contains(user);
  }
}
