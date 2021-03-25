package mediator;

import com.google.gson.Gson;
import external.Log;
import javafx.application.Platform;
import model.Model;
import model.UserName;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClientHandler implements Runnable, PropertyChangeListener {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Boolean running;
    private Gson gson;

    private Model model;

    public ChatClientHandler(Socket socket, Model model) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.gson = new Gson();

        this.model = model;
        this.model.addListener("Message", this);
    }

    @Override
    public void run() {

        running = true;
        while (running) {
            try {
                String requestJson = in.readLine();

                Message requestMessage = gson.fromJson(requestJson, Message.class);
                Message replyMessage = null;

                if (requestMessage.getType().equalsIgnoreCase("Login")) {
                    try {
                        UserName userName =  new UserName(requestMessage.getUsername());
                        model.addUser(userName);
                        replyMessage = new Message("Login", "Success", userName.getName());
                    }
                    catch (Exception e) {
                        replyMessage = new Message("Error", e.getMessage(), null);
                    }
                }
                else {
                    String user = requestMessage.getUsername() + " (" + socket.getInetAddress().getHostAddress() + ")";
                    model.addMessage(requestMessage.getMessage(), user);
                    replyMessage = requestMessage;
                }

                out.println(gson.toJson(replyMessage));

            } catch (Exception e) {
                e.printStackTrace();
                Log.getLog().addLog(
                        "Client (" + socket.getInetAddress().getHostAddress() + ") error");
                close();
            }
        }
        close();
    }

    public void close()
    {
        running = false;
        try {
            in.close();
            out.close();
            socket.close();
        }
        catch (IOException e) {
            //
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> out.println(
                gson.toJson(new Message("Message", (String)evt.getNewValue(), (String)evt.getOldValue()))
        ));

    }
}
