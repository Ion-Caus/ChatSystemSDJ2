package mediator;

import com.google.gson.Gson;
import model.Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ChatClient implements Model {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Gson gson;

    private Message receivedMessage;
    private boolean waiting;
    private PropertyChangeSupport property;

    private String username;

    public ChatClient(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.gson = new Gson();

        property = new PropertyChangeSupport(this);

        ChatClientReader chatClientReader = new ChatClientReader(this, in);
        Thread chatClientReaderThread= new Thread(chatClientReader);
        chatClientReaderThread.setDaemon(true);
        chatClientReaderThread.start();
    }

    public void disconnect() throws IOException {
            socket.close();
            in.close();
            out.close();
    }

    public synchronized Message waitingForReply() throws Exception {
        while (receivedMessage == null){
            waiting = true;
            wait();
        }
        waiting = false;

        Message returnMessage = receivedMessage;
        receivedMessage = null;

        if (returnMessage.getType().equalsIgnoreCase("Error")) {
            throw new Exception(returnMessage.getMessage());
        }
        return returnMessage;
    }


    public synchronized void receive(String requestJson){
        Message requestMessage = gson.fromJson(requestJson, Message.class);
        if (waiting) {
            receivedMessage = requestMessage;
            notify();
        }
        else {
            property.firePropertyChange("Message", null, requestMessage);
        }

    }

    @Override
    public void sendMessage(String message) {
        Message requestMessage = new Message("Message", message, getUsername());
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
        Message requestMessage = new Message("Login", null, username);

        out.println(gson.toJson(requestMessage));

        try {
            Message replyMessage = waitingForReply();
            this.username = replyMessage.getUsername();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getUsername() {
        return username;
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
