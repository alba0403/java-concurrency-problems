import java.util.LinkedList;
import java.util.Queue;

public class Barberia {
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

  public void entrarClient(){
    if
  }
}
