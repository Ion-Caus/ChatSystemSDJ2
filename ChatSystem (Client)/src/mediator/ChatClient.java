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
import java.util.Map;

public class ChatClient implements Model {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Gson gson;

    private Message message;
    private Model model;
    private PropertyChangeSupport property;


    public ChatClient(Model model, String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.gson = new Gson();
        this.model = model;

        property= new PropertyChangeSupport(this);

        ChatClientReader chatClientReader = new ChatClientReader(in,this);
        Thread chatClientReaderThread= new Thread(chatClientReader);
        chatClientReaderThread.setDaemon(true);
        chatClientReaderThread.start();
    }

    public void disconnect() throws IOException {
        socket.close();
        in.close();
        out.close();
        }

    public void receiveMessage(String replyJson){

        //if (gson.fromJson(replyJson, Map.class).get("type").equals("Message"))

        // TODO finish

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
