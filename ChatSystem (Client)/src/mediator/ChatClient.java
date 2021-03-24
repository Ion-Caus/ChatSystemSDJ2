package mediator;

import com.google.gson.Gson;
import model.Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.function.ToDoubleBiFunction;

public class ChatClient implements Model {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Gson gson;


    private PropertyChangeSupport propertyChangeSupport;
    private Message message;
    private Model model;

    public ChatClient(Model model, String host, int port, Message message) throws IOException {
        this.socket = new Socket(host, port);
        this.model = model;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.message=message;

        propertyChangeSupport= new PropertyChangeSupport(this);
        ChatClientReader chatClientReader = new ChatClientReader(socket);


        Thread chatClientReaderThread= new Thread(chatClientReader);
        chatClientReaderThread.setDaemon(true);
        chatClientReaderThread.start();


    }


    public void disconnect() throws IOException {
            socket.close();
            in.close();
            out.close();
        }

    public void receiveMessage(String messageAsJSON){

            Message message = gson.fromJson(messageAsJSON, Message.class);
            notify();

        // TODO finish

    }

    public void sendMessage(Message message){
        String messageAsJSON = gson.toJson(message);
        out.println(messageAsJSON);



        //TODO finish

    }


    @Override
    public void addListener(String nameProperty, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(nameProperty, listener);

    }

    @Override
    public void removeListener(String nameProperty, PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(nameProperty, listener);
    }
}
