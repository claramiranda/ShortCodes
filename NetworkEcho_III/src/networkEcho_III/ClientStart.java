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
   private static int[][] matriz = criaMatriz(2,3);

   public static void main(String[] args) throws Exception
      {
      System.out.println(Info.getUniformTitle());
      System.out.println(module + " running.");
      System.out.println();
      imprimeMatriz(matriz);
      matriz = matrizTansposta(matriz);
      imprimeMatriz(matriz);
      
      

      try (Socket clientSocket = new Socket("localhost", Info.listeningPort))
         {
         System.out.println("Local TCP port " + clientSocket.getLocalPort());
         System.out.println("Sending bytes to TCP port " + clientSocket.getPort());
         System.out.println();

         ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
         ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());

         while (isRunning)
            {
            
        	 ObjetoMensagem objetoParaEnvio = new ObjetoMensagem("Pega essa mano");
        	 
        	 System.out.println(objetoParaEnvio.texto);
        	 
            outputStream.writeObject(objetoParaEnvio);
            
            ObjetoMensagem returnMessage = (ObjetoMensagem)inputStream.readObject();

            
            System.out.println(returnMessage.texto);
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
   
       static int[][] criaMatriz(int N, int M){
        int [][] matriz = new int[N][M];
        Random gerador = new Random();
        for(int i = 0; i < matriz.length; i++){
            for (int j = 0; j < matriz[0].length; j++){
                matriz[i][j] = gerador.nextInt(10);
            }
        }
        return matriz;
    }
    
    public static void imprimeMatriz(int[][] matriz){
        for(int i = 0; i < matriz.length; i++){
            for (int j = 0; j < matriz[0].length; j++){
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println("\n");
        }
    }
    
    public static int[][] matrizTansposta(int[][] matriz) {
        int[][] retorno = new int[matriz[0].length][matriz.length];//invertendo a linha com a coluna
        for (int lin = 0; lin < retorno.length; lin++) {
            for (int col = 0; col < retorno[lin].length; col++) {
                retorno[lin][col] = matriz[col][lin];
            }
        }
        return retorno;
    }

   }


