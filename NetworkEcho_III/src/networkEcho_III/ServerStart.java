package networkEcho_III;

public class ServerStart
   {

 /**
 *
 * classe de instanciação e inicialização do servidor
 */
   public static void main(String[] args)
      {
      MultitaskServer server = new MultitaskServer();
      server.startServer();
      }
   }
