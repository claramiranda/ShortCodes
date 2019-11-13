package networkEcho_III;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

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

         System.out.println("Handler " + threadId + " starting.");

         int msgRcvd = 0;

         while (isRunning)
            {
            String localTag = "Client Handler #" + threadId;
           
        	 //ObjetoMensagem objetoMensagemRecebido = (ObjetoMensagem) objectInputStream.readObject();
        	 Matriz matrizRecebida = (Matriz) objectInputStream.readObject();
        	 
                 System.out.println(localTag + " lendo matriz original .... " + (++msgRcvd) + ": \n" );
                 matrizRecebida.imprimeMatriz(matrizRecebida.getMatriz());
        	
        	 //objetoMensagemRecebido.texto="Caraio borracha";
        	 matrizRecebida.setMatriz(matrizRecebida.matrizTansposta(matrizRecebida.getMatriz()));
                 System.out.println("\n imprime matriz transposta: \n");
                 matrizRecebida.imprimeMatriz(matrizRecebida.getMatriz());
                 outputStream.writeObject(matrizRecebida);
                 
                 //outputStream.writeObject(objetoMensagemRecebido);
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
