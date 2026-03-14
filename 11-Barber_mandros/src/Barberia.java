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
        System.out.printf("No queden cadires, client %s se'n va", client.getNom());
      } else {
        salaEspera.add(client);
        System.out.printf("Client %s en espera", client.getNom());
        condBarber.notify();
      }
    }
  }

  public void run(){
    for(int i = 0; i < 10; i++){

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
