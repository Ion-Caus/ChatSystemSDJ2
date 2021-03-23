package model;

public class User
{
  private UserName userName;
  private DateTime registrationDate;

  public User(UserName userName)
  {
    if (userName == null)
    {
      throw new IllegalArgumentException("Null username");
    }
    this.userName = userName;
    this.registrationDate = new DateTime();
  }

  public String toString()
  {
    return userName + " " + registrationDate;
  }

  public UserName getUserName()
  {
    return userName;
  }

  public DateTime getRegistrationDate()
  {
    return registrationDate;
  }

}
