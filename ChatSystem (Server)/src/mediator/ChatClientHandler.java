package mediator;

import com.google.gson.Gson;
import model.Model;

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
          this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          this.out = new PrintWriter(socket.getOutputStream(), true);
          this.gson = new Gson();

          this.model = model;
          //this.model.addListener(this);
        }

  @Override public void run() {

  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {

  }
}
