package model;

import utility.observer.NamedPropertyChangeSubject;

public interface Model extends NamedPropertyChangeSubject {
    void sendMessage(String message);
    void login(String username) throws Exception;
    String getUsername();

}
