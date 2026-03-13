public class Barber extends Thread{
  private String nom;

  // constructor
  public Barber(String nom){
    this.nom = nom;
  }

  public void run(){
    while(true){
      if (hi ha clients){
        //TODO: esperar fisn tenir la classe barberia
      } else {
        System.out.printf("Barber %s dormint\n", nom);
      }
    }
  }
}
