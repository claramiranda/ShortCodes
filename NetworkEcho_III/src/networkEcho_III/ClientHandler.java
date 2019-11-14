package networkEcho_III;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * classe de manipulação dos objetos recebidos do cliente
 */
class ClientHandler extends Thread
   {
   private final MultitaskServer father;
   private final Socket          clientSocket;
   private final int             threadId;

   ClientHandler(MultitaskServer father, Socket myClientSocket, int myThreadId)
      {
      super();
      this.father = father;
      this.clientSocket = myClientSocket;
      this.threadId = myThreadId;
      }

   @Override
   public void run()
      {
      boolean isRunning = true;
      
      ObjectInputStream objectInputStream;
      
      try
         {
    	  objectInputStream=new ObjectInputStream(clientSocket.getInputStream());
    	  ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());

         int msgRcvd = 0;

         while (isRunning)
            {
        	 try
        	 {
                    //Recebe o objeto do cliente e faz um cast para matriz
                    Matriz matrizRecebida = (Matriz) objectInputStream.readObject();

                    //Imprime a matriz original
                    System.out.println("Matriz original recebida do cliente");
                    matrizRecebida.imprimeMatriz(matrizRecebida.getMatriz());

                    //Define a matriz transposta
                    matrizRecebida.setMatriz(matrizRecebida.matrizTansposta(matrizRecebida.getMatriz()));
             
                    //Imprime a matriz transposta
                    System.out.println("Matriz transposta para envio ao cliente");
                    matrizRecebida.imprimeMatriz(matrizRecebida.getMatriz());
                    outputStream.writeObject(matrizRecebida);
        	 }
        	 catch(IOException e)
        	 {
        	 
        	 }
            }

         outputStream.close();
         objectInputStream.close();
         clientSocket.close();

         father.threadClosed(this.threadId);
         }
      catch (IOException exceptionLaunched)
         {
         exceptionLaunched.printStackTrace();
         } catch (Exception e) {
		e.printStackTrace();
	}
      }


   }
