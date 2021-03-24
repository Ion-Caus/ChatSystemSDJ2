package mediator;

import model.Model;

import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient implements Model {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private PropertyChangeSupport propertyChangeSupport;

    private Model model;

    public ChatClient(Model model, String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.model = model;
        propertyChangeSupport= new PropertyChangeSupport(this);
        ChatClientReader chatClientReader = new ChatClientReader();

        Thread chatClientReaderThread= new Thread(chatClientReader);
        chatClientReaderThread.setDaemon(true);
        chatClientReaderThread.start();


    }


    public void disconnect() throws IOException {
        socket.close();
        out.close();
        in.close();

    }




}
