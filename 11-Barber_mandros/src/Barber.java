public class Barber extends Thread{
  private String nom;

  // constructor
  public Barber(String nom){
    this.nom = nom;
  }

  public void run(){
    while(true){
      Client client = Barberia.instancia.seguentClient();
      if (client != null) {
        try {
          int tempsEspera = 900 + (int)(Math.random() * 100);
          Thread.sleep(tempsEspera);
        } catch (InterruptedException e) {
          System.err.println(e);
        }
        client.tallarseElCabell();
      } else {
        try {
          synchronized(Barberia.instancia.condBarber){
            System.out.printf("Ningú en espera \nBarber %s dormint\n", nom);
            Barberia.instancia.condBarber.wait();
          }
        } catch (InterruptedException e) {
          System.err.println(e);
        }
      }
    }
  }
}
