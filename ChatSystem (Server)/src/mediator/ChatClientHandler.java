package mediator;

import com.google.gson.Gson;
import external.Log;
import javafx.application.Platform;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClientHandler implements Runnable, PropertyChangeListener
{
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private Boolean running;
  private Gson gson;

  private Model model;

  public ChatClientHandler(Socket socket, Model model) throws IOException
  {
    this.socket = socket;
    this.in = new BufferedReader(
        new InputStreamReader(socket.getInputStream()));
    this.out = new PrintWriter(socket.getOutputStream(), true);
    this.gson = new Gson();

    this.model = model;
    //this.model.addListener(this);
  }

  @Override public void run()
  {
    //joining user and assigning username

    running = true;
    while (running)
    {
      try
      {
        String requestJson = in.readLine();

        Message message = gson.fromJson(requestJson, Message.class);

        String reply = message.getMessage();
        // --->

        Log.getLog().addLog(
            " (" + socket.getInetAddress().getHostAddress() + ") : " + message
                .getMessage());


        // need to somehow show the username in this message
        String replyJson = null; // gson.toJson(new Message(null, "type", reply)); // no user, no type

        // need to broadcast it to everyone instead of only to the client
        out.println(replyJson);

      }
      catch (Exception e)
      {
        e.printStackTrace();
        Log.getLog().addLog(
            "Client (" + socket.getInetAddress().getHostAddress() + ") error");
        close();
      }
    }
    close();

    // <---
  }

  public void close()   //method to close connection
  {
    running = false;
    try
    {
      in.close();
      out.close();
      socket.close();
    }
    catch (IOException e)
    {
      //
    }
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> out.println(
        //gson.toJson(new Message(null,"Message", (String) evt.getNewValue()))
    ));

  }
}
