package model;

import utility.observer.NamedPropertyChangeSubject;

import java.util.ArrayList;

public interface Model extends NamedPropertyChangeSubject {
    void sendMessage(String message);
    void login(String username) throws Exception;
    void logout();
    String getUsername();
    ArrayList<String> getAllUsers();

}
