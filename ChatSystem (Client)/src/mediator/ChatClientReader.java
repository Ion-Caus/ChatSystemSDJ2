package mediator;

import java.io.BufferedReader;
import java.io.IOException;

public class ChatClientReader implements Runnable {
    private BufferedReader in;
    private boolean running;
    private ChatClient client;

    public ChatClientReader(ChatClient client, BufferedReader in) {
        this.client = client;
        this.in = in;
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            try {
                String requestJson =  in.readLine();
                client.receive(requestJson);
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

