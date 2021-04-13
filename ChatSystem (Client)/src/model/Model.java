package model;

import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Model extends LocalSubject<Object, Object> {
    void sendMessage(String message);
    void login(String username);
    void logout();
    ArrayList<String> getAllUsers();
    String getUsername();

}
