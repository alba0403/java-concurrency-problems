import java.util.LinkedList;
import java.util.Queue;

public class Barberia extends Thread{
  // atributs
  private Queue<Client> salaEspera;
  private int numMaximCadires;
  public Object condBarber;
  public static Barberia instancia; // creem una instancia estatica

  // constructor
  public Barberia(int numMaximCadires){
    salaEspera = new LinkedList<>();
    this.numMaximCadires = numMaximCadires;
    condBarber = new Object();
    instancia = this;
  }

  // Mètodes
  public Client seguentClient(){
    Client client = salaEspera.poll();
    return client;      //poll() asigna client a null si no hi han clients
  }

  public void entrarClient(Client client){
    synchronized(condBarber){
      if(salaEspera.size() == numMaximCadires){
        System.out.printf("No queden cadires, client %s se'n va\n", client.getNom());
      } else {
        salaEspera.add(client);
        System.out.printf("Client %s en espera\n", client.getNom());
        condBarber.notify();
      }
    }
  }

  public void run(){
    int nClient = 1;
    for(int i = 0; i < 2; i++){
      for(int j = 0; j < 10; j++){
        try {
          Client nouClient = new Client(nClient++);
          entrarClient(nouClient);
          // interval de 0,5s
          Thread.sleep(500);
        } catch (InterruptedException e) {
          System.err.println(e);
        }
      }
      try {
        //després de fer entrar 10 clients, esperar 10 segons
        Thread.sleep(10000);
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    }

  }

  public static void main(String[] args) {
    Barberia barberia = new Barberia(3);
    Barber barber = new Barber("pepe");

    //iniciem els fils
    barberia.start();
    barber.start();
  }
}
