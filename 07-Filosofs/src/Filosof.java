public class Filosof extends Thread {
  //atributs
  private Forquilla forquillaDreta;
  private Forquilla forquillaEsquerra;
  private int gana;   // está bé fins a 3, com a máxim máxim 4 (però millor que no)

  // constructor
  public Filosof(String nom,Forquilla forquillaDreta, Forquilla forquillaEsquerra, int gana){
    super(nom);
    this.forquillaDreta = forquillaDreta;
    this.forquillaEsquerra = forquillaEsquerra;
    this.gana = gana;
  }

  // métode pensar
  public void menjar(){
    System.out.printf("Filòsof: %s menja\n", getName());
  }
  
  // métode menjar
  public void pensar(){
    System.out.printf("Filòsof: %s pensant\n", getName());
  }


  // métode run
  public void run(){
    //intentar comer
    if (!forquillaDreta.isEnUs()){          // si no s'está utilitzant
      forquillaDreta.setEnUs(true);   // passa a estar en ús
    } else {
      //sleep());
      //run();                              // recursiva per tornar a provar?
    }

    // comer

    // dejar la forquilla

    // pensar
  }
}
