package networkEcho_III;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * classe para instanciação da matriz e envio ao servidor
 */

public class ClientStart
   {

   public static void main(String[] args) throws Exception
      {
      System.out.println("M�dulo cliente iniciado");
      System.out.println();
      
      try (Socket clientSocket = new Socket("localhost", Info.listeningPort))
         {
         System.out.println("Local TCP port " + clientSocket.getLocalPort());
         System.out.println();

         //Stream para envio do objeto
         ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
         //Stream para recebimento do objeto
         ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());

         int i=0;
         while ( i == 0)
            {
        	 	//Cria um novo objeto de matriz
                Matriz matrizEnvio = new Matriz(3,2);
                //Imprime a matriz criada na tela
                System.out.println("Matriz original");
                matrizEnvio.imprimeMatriz(matrizEnvio.getMatriz());
                //Envia o objeto matriz para o servidor
                outputStream.writeObject(matrizEnvio);
                
                System.out.println();
                
                //Aguarda o retorno do servidor (objeto de mesmo tipo)
                Matriz matrizRetorno = (Matriz)inputStream.readObject();
                //Imprime a matriz de retorno
                System.out.println("Matriz transposta pelo servidor");
                matrizRetorno.imprimeMatriz(matrizRetorno.getMatriz());
                
            System.out.println();
            
            //Aguarda 3s
            Thread.sleep(3000);
            
            //i++;
            }
         //Encerra o socket de comunica��o
         clientSocket.close();
         }
      
      catch (IOException | InterruptedException exception)
         {
            System.out.println("Ocorreu uma exceção: " + exception.getMessage());
            System.exit(1);
         }

        System.out.println();
        System.out.println("Módulo cliente encerrado");
      }

   }


