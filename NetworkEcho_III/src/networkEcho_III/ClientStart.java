package networkEcho_III;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

public class ClientStart
   {
   private static final String module    = "Client";
   private static boolean      isRunning = true;

   public static void main(String[] args) throws Exception
      {
      System.out.println(Info.getUniformTitle());
      System.out.println(module + " running.");
      System.out.println();
      
      

      try (Socket clientSocket = new Socket("localhost", Info.listeningPort))
         {
         System.out.println("Local TCP port " + clientSocket.getLocalPort());
         System.out.println("Sending bytes to TCP port " + clientSocket.getPort());
         System.out.println();

         ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
         ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());

         while (isRunning)
            {
                //envia objeto
        	/*ObjetoMensagem objetoParaEnvio = new ObjetoMensagem("Pega essa mano"); 
        	System.out.println(objetoParaEnvio.texto);
        	outputStream.writeObject(objetoParaEnvio);*/
                Matriz matrizEnvio = new Matriz(3,2);
                matrizEnvio.imprimeMatriz(matrizEnvio.getMatriz());
                outputStream.writeObject(matrizEnvio);
                
                //recebe objeto
                /*ObjetoMensagem returnMessage = (ObjetoMensagem)inputStream.readObject();
                System.out.println(returnMessage.texto);*/
                Matriz matrizRetorno = (Matriz)inputStream.readObject();
                matrizRetorno.imprimeMatriz(matrizRetorno.getMatriz());
                
            System.out.println();
            Thread.sleep(Info.loopDelay);
            }
         clientSocket.close();
         }
      catch (IOException | InterruptedException exception)
         {
         System.out.println("Exception launched: " + exception.getMessage());
         System.exit(1);
         }

      System.out.println();
      System.out.println(Info.getUniformTitle());
      System.out.println(module + " stopped.");
      }

   }


