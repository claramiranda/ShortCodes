package networkEcho_III;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * classe de manipulação do servidor
 */
class MultitaskServer
   {
   private boolean      isRunning;
   private int          threadsCount;

   MultitaskServer()
      {
      super();
      this.isRunning = false;
      this.threadsCount = 0;
      }

   void startServer()
      {
      this.isRunning = true;

      try (ServerSocket serverSocket = new ServerSocket(Info.listeningPort))
         {

         while (isRunning)
            {
        	 System.out.println("Servidor iniciado");
            Socket newClientSocket = serverSocket.accept();
            
            Thread clientHandler = new ClientHandler(this, newClientSocket, threadsCount++);
            clientHandler.start();
            }
         }
      catch (IOException exception)
         {
         System.out.println("Ocorreu uma exceção: " + exception.getMessage());
         System.exit(1);
         }

      }

   void threadClosed(int threadId)
      {
      this.threadsCount--;

      if (threadsCount == 0)
         {
         this.isRunning = false;
         System.exit(0);
         }
      }

   }
