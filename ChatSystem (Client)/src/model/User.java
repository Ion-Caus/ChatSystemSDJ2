package model;

public class User {

    private UserName userName;


    public User(UserName userName)
    {
        if (userName == null)
        {
            throw new IllegalArgumentException("Null username");
        }
        this.userName = userName;

    }

    public String toString()
    {
        return userName.toString();
    }

    public UserName getUserName()
    {
        return userName;
    }


}
