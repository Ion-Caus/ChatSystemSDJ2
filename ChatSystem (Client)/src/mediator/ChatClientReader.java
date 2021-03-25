package mediator;

import java.io.BufferedReader;
import java.io.IOException;

public class ChatClientReader implements Runnable{
    private BufferedReader in;
    private ChatClient chatClient;
    private boolean running;



    public ChatClientReader(BufferedReader in, ChatClient chatClient) throws IOException {
      this.in = in;
      this.chatClient = chatClient;
    }

    @Override
    public void run() {
      running = true;
      while(running){

        try {
          String messageAsJSON = in.readLine();
          chatClient.receiveMessage(messageAsJSON);

        } catch (IOException e) {
          e.printStackTrace();

        }
       }
    }

    public void close() throws IOException {
      running = false;
      in.close();
    }
}
