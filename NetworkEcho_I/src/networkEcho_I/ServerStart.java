package preprojetomsn;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerStart
   {
   private static final String module = "Server";

   public static void main(String[] args)
      {
      boolean isRunning = true;

      System.out.println(Info.getUniformTitle());
      System.out.println(module + " running.");
      System.out.println();

      try (ServerSocket serverSocket = new ServerSocket(Info.listeningPort)) //inicia a conexão
         {
         System.out.println("Server at " + InetAddress.getLocalHost() + ", listening TCP port " + serverSocket.getLocalPort()); //print pra testar se tá safe
         System.out.println();
         
         int msgRead = 0;
         Socket newClientSocket = serverSocket.accept();

         while (isRunning)
            {
            InputStream inputStream = newClientSocket.getInputStream();
            byte[] buffer = new byte[Info.maxPackageSise];
            int bytesRead = inputStream.read(buffer, 0, buffer.length);

            if (bytesRead >= 0)
               {
               String plainText = (new String(buffer, 0, bytesRead));  //Lendo o que tá no buffer e jogando pra var
               //chama funcao de manipular o vetor
               System.out.println(module + " read " + (++msgRead) + ": " + plainText + " (" + bytesRead + " bytes)");
               isRunning = !plainText.startsWith(Info.shutDownCmd);
               }
            }
         serverSocket.close();
         }
      catch (IOException exception)
         {
         System.out.println("Exception launched: " + exception.getMessage());
         System.exit(1);
         }

      System.out.println();
      System.out.println(Info.getUniformTitle());
      System.out.println(module + " stopped.");
      }
   
      /*NOTAS DO PROJETO*/
   /*
    Minha parte: Lidar com a matriz... montar um objeto matriz com os vetores lidos pelo buffer
   criar função de trasnposição dos elementos
   
   
        TODO
         Limitar a qtd de mensagens
         enviar outros tipos de objetos
         Bônus: Servidor devolve matriz transposta
   
   */
   
      
   }
