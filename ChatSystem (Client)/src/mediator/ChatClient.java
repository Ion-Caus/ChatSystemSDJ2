package mediator;

import com.google.gson.Gson;
import model.Model;
import model.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;

public class ChatClient implements Model {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Gson gson;

    private MessagePackage receivedMessage;
    private UserListPackage receivedListPackage;
    private boolean waiting;
    private PropertyChangeSupport property;

    private ChatClientReader chatClientReader;

    private String username;

    public ChatClient(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.gson = new Gson();

        property = new PropertyChangeSupport(this);

        chatClientReader = new ChatClientReader(this, in);
        Thread chatClientReaderThread= new Thread(chatClientReader);
        chatClientReaderThread.setDaemon(true);
        chatClientReaderThread.start();
    }

    public void disconnect() {
        try {
            chatClientReader.close();
            socket.close();
            in.close();
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized MessagePackage waitingForReply() throws Exception {
        while (receivedMessage == null){
            waiting = true;
            wait();
        }
        waiting = false;

        MessagePackage returnMessage = receivedMessage;
        receivedMessage = null;

        if (returnMessage.getType().equalsIgnoreCase("Error")) {
            throw new Exception(returnMessage.getSource());
        }
        return returnMessage;
    }

    private synchronized UserListPackage waitingForReplyList()
        throws InterruptedException
    {
        while (receivedListPackage == null)
        {
            waiting = true;
            wait();
        }
        waiting = false;

        UserListPackage listPackage = receivedListPackage;
        receivedListPackage = null;

        return listPackage;
    }


    public synchronized void receive(String requestJson){

        MessagePackage requestMessage = gson.fromJson(requestJson, MessagePackage.class);
        if (requestMessage == null) {
            disconnect();
        }
        if (waiting) {
            receivedMessage = requestMessage;
            notify();
        }
        if (gson.fromJson(requestJson, Map.class).get("type").equals("User"))
        {
            receivedListPackage = gson
                .fromJson(requestJson, UserListPackage.class);
            notify();
        }
        else {
            property.firePropertyChange("Message", null, requestMessage);
        }

    }

    @Override
    public void sendMessage(String message) {
        MessagePackage requestMessage = new MessagePackage("Message", new Message(username, message));
        out.println(gson.toJson(requestMessage));
        try {
            waitingForReply();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void login(String username) throws Exception {
        MessagePackage requestMessage = new MessagePackage("Login", username);

        out.println(gson.toJson(requestMessage));

        try {
            MessagePackage replyMessage = waitingForReply();
            this.username = replyMessage.getSource();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override public void logout() {
        MessagePackage logoutMessage = new MessagePackage("Logout",this.username);
        out.println(gson.toJson(logoutMessage));
        System.out.println("Logging out...");
        try {
            waitingForReply();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override public ArrayList<User> getAllUsers()
    {

        UserListPackage user = new UserListPackage("User", null);
        out.println(gson.toJson(user));
        try
        {
            return waitingForReplyList().getUsers();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addListener(String nameProperty, PropertyChangeListener listener) {
        property.addPropertyChangeListener(nameProperty, listener);

    }

    @Override
    public void removeListener(String nameProperty, PropertyChangeListener listener) {
        property.removePropertyChangeListener(nameProperty, listener);
    }
}
