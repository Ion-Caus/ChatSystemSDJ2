package mediator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClientReader implements Runnable{
    private BufferedReader in;
    private ChatClient chatClient;



    public ChatClientReader(Socket socket) throws IOException {



    }

    @Override
    public void run() {
       while(true){

               try {
                   String messageAsJSON = in.readLine();
                   chatClient.receiveMessage(messageAsJSON);

               } catch (IOException e) {
                   e.printStackTrace();

               }

       }

    }


    public void close() throws IOException {
        in.close();
    }
}
