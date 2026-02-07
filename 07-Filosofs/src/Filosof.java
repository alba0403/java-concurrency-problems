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

  // métodes auxiliars
  public void menjar(){
    System.out.printf("Filòsof: %s menja\n", getName());
    gana--;
    esperar(1,2);
  }
  
  public void pensar(){
    System.out.printf("Filòsof: %s pensant\n", getName());
    esperar(1,2);
  }

  public void esperar(double min, double max){
    try{
      double numeroAleatori = Math.random() * (max - min) + min;    //genera un numero aletori entre 0 y 1, sumem la diferència del min i max perque el rndm estigui entre els dos valors que volem
      long tempsAleatoriEspera = (long) (numeroAleatori*1000);      //double per precisió, passa a long per passar a milisegons i poder utilitzar sleep
      sleep(tempsAleatoriEspera);
    } catch (InterruptedException e){
      System.err.println(e);
    }
  }

  // métode run
  public void run(){
    while(gana < 4){
      pensar();

      //intentar menjar
      if (forquillaEsquerra.isEnUs()){                                                            // si s'está utilitzant s'espera
        esperar(0.5,1);
        gana++;
      } else {
        forquillaEsquerra.setEnUs(true);                                                     // passa a estar en ús
        System.out.printf("Filòsof: %s agafa la forquilla esquerra\n", getName());

        if(forquillaDreta.isEnUs()){
          forquillaEsquerra.setEnUs(false);                                                  //deixem lliure la que haviem agafat
          System.out.printf("Filòsof: %s deixa la forquilla esquerra (%d) i espera (dreta ocupada)\n", 
                            getName(), 
                            forquillaEsquerra.getNumero());
          esperar(0.5,1);
          gana++;
        } else {
          forquillaDreta.setEnUs(true);
          System.out.printf("Filòsof: %s agafa la forquilla dreta\n", getName());

          menjar();
          // deixar la forquilla
          forquillaDreta.setEnUs(false);
          forquillaEsquerra.setEnUs(false);
          
          System.out.printf("Filòsof: %s ha acabat de menjar\n", getName());
        }
      }
    }
  }
}
