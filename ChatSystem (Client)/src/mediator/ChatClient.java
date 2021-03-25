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

    private ArrayList<Message> receivedMessages;
    private boolean waiting;
    private PropertyChangeSupport property;

    private Model model;

    public ChatClient(Model model, String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.gson = new Gson();
        this.model = model;

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
        while (receivedMessages.isEmpty()){
            waiting = true;
            wait();
        }
        waiting = false;

        Message returnMessage = receivedMessages.get(0);
        receivedMessages.remove(returnMessage);

        if (returnMessage.getType().equalsIgnoreCase("Error")) {
            throw new Exception(returnMessage.getMessage());
        }
        return returnMessage;
    }


    public synchronized void receive(String requestJson){
        Message requestMessage = gson.fromJson(requestJson, Message.class);
        if (waiting) {
            receivedMessages.add(requestMessage);
            notify();
        }
        else {
            property.firePropertyChange(requestMessage.getType(), requestMessage.getUsername(), requestMessage.getMessage());
        }

    }

    public void sendMessage(Message message){
        String messageAsJSON = gson.toJson(message);
        out.println(messageAsJSON);



        //TODO finish

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
