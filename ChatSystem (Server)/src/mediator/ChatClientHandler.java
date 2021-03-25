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

                MessagePackage requestPackage = gson.fromJson(requestJson, MessagePackage.class);
                MessagePackage replyPackage;

                if (requestPackage.getType().equalsIgnoreCase("Login")) {
                    try {
                        UserName userName =  new UserName(requestPackage.getSource());
                        model.addUser(userName);
                        replyPackage = new MessagePackage("Login", userName.getName());
                    }
                    catch (Exception e) {
                        replyPackage = new MessagePackage("Error", e.getMessage());
                    }
                }
                else {
                    model.addMessage(
                            requestPackage.getMessage().addUserIp(socket.getInetAddress().getHostAddress())
                    );
                    replyPackage = requestPackage;
                }

                out.println(gson.toJson(replyPackage));

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
                gson.toJson(
                        new MessagePackage(
                                "Message",
                                ( (Message)evt.getNewValue()).removeUserIp()
                        )
                )
        ));
    }
}
