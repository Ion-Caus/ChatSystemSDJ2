package mediator;

import java.io.BufferedReader;
import java.io.IOException;

public class ChatClientReader implements Runnable{
    private BufferedReader in;
    private ChatClient chatClient;
    private boolean running;

    public ChatClientReader(ChatClient client, BufferedReader in) throws IOException {
        this.chatClient = client;
        this.in = in;
    }

    @Override
    public void run() {
        running = true;
       while(running){
               try {
                   String requestJson = in.readLine();
                   chatClient.receive(requestJson);

               }
               catch (IOException e) {
                   e.printStackTrace();

               }
       }
    }


    public void close() throws IOException {
        running = false;
        in.close();
    }
}
