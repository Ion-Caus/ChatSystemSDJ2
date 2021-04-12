//package mediator;
//
//import external.Log;
//import model.Model;
//
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//public class ChatServer implements Runnable
//{
//  private final int PORT = 6789;
//  private boolean running;
//  private ServerSocket welcomeSocket;
//
//  private Model model;
//
//  public ChatServer(Model model)
//  {
//    this.model = model;
//  }
//
//  @Override public void run()
//  {
//    try
//    {
//
//      Log.getLog().addLog("Starting Server...");
//      welcomeSocket = new ServerSocket(PORT);
//
//      running = true;
//      while (running)
//      {
//        Log.getLog().addLog("Waiting for a client...");
//        Socket socket = welcomeSocket.accept();
//        Log.getLog().addLog("Client (" + socket.getInetAddress().getHostName() + ") connected.");
//
//        ChatClientHandler clientHandler = new ChatClientHandler(socket, model);
//        Thread clientThread = new Thread(clientHandler);
//        clientThread.setDaemon(true);
//        clientThread.start();
//      }
//    }
//    catch (IOException e)
//    {
//      Log.getLog().addLog("Error: " + e.getMessage());
//    }
//  }
//
//  public void close()
//  {
//    running = false;
//    try
//    {
//      welcomeSocket.close();
//    }
//    catch (Exception e)
//    {
//      //
//    }
//  }
//
//}